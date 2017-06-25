package com.fellow.web.controller.login;

import com.alibaba.fastjson.JSON;
import com.fellow.common.constant.SystemConstant;
import com.fellow.common.exception.BusinessException;
import com.fellow.common.security.PasswordUtil;
import com.fellow.common.security.UrlUtil;
import com.fellow.common.util.VelocityUtil;
import com.fellow.domain.dto.ForgetPasswordDto;
import com.fellow.domain.email.EmailInfo;
import com.fellow.domain.enums.ForgetPasswordEnum;
import com.fellow.domain.model.ForgetPassword;
import com.fellow.domain.model.User;
import com.fellow.domain.query.ForgetPasswordQuery;
import com.fellow.domain.query.UserQuery;
import com.fellow.domain.web.Response;
import com.fellow.service.ForgetPasswordService;
import com.fellow.service.UserService;
import com.fellow.web.base.WebAbstract;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by wubiao on 2017/2/15.
 */
@Controller
@RequestMapping(ForgetPasswordController.VIEW_PATH)
public class ForgetPasswordController extends WebAbstract<ForgetPasswordService> {
    public static final String VIEW_PATH = "/login/forget";

    @Resource
    private UserService userService;

    @Value("${rootUrl}")
    private String rootUrl;

    @RequestMapping("/sendEmail")
    @ResponseBody
    public Response sendEmail(ForgetPasswordDto forgetPassword, HttpServletRequest request) {
        Response response = Response.newResponse();
        HttpSession session = request.getSession();
        if (StringUtils.isBlank(forgetPassword.getEmail())) {
            response.setMessage("邮箱不能为空");
            return response;
        }
        //查找邮箱是否存在
        UserQuery userQuery = new UserQuery();
        userQuery.setEmail(forgetPassword.getEmail());
        User user = userService.validateByEmail(userQuery);
        if (null == user || StringUtils.isBlank(user.getEmail())) {
            response.setMessage("邮箱不存在！");
            return response;
        }
        if (StringUtils.isBlank(forgetPassword.getCaptcha()) || !forgetPassword.getCaptcha().equals(session.getAttribute(SystemConstant.CAPTCHA_NAME))) {
            response.setMessage("请输入正确的验证码");
            session.setAttribute(SystemConstant.CAPTCHA_NAME, RandomStringUtils.random(4));
//            return response;
        }
        forgetPassword.setTokenKey(RandomStringUtils.randomAlphabetic(8));
        forgetPassword.setTokenValue(System.nanoTime() + RandomStringUtils.randomAlphabetic(4));
        forgetPassword.setSendTime(new Date());
        forgetPassword.setStatus(ForgetPasswordEnum.SEND_RESET.getKey());
        //邮件
        EmailInfo emailInfo = new EmailInfo();
        emailInfo.setSubject("那人说网找回密码");
        emailInfo.setToAddr(new String[]{forgetPassword.getEmail()});
        Context context = new VelocityContext();
        context.put("username", user.getUsername());
        try {
            ForgetPasswordDto forgetParam = new ForgetPasswordDto();
            forgetParam.setEmail(forgetPassword.getEmail());
            forgetParam.setTokenKey(forgetPassword.getTokenKey());
            forgetParam.setTokenValue(forgetPassword.getTokenValue());
            forgetParam.setSendTime(new Date());
            context.put("url", rootUrl + "/login/forget/showForgetUpdatePd?token=" + UrlUtil.encodeUrl(JSON.toJSONString(forgetParam)));
        } catch (Exception e) {
            log.error("sendEmail--设置找回密码url异常!", e);
        }
        String bodyHtml = VelocityUtil.mergeTemplate(context, velocityConfig.getVelocityEngine(), "/template/email/reset_password.vm");
        emailInfo.setBodyHtml(bodyHtml);
        forgetPassword.setEmailInfo(emailInfo);
        service.sendEmail(forgetPassword);
        response.setMessage("请登录" + forgetPassword.getEmail() + "邮件点击相关连接找回密码！");
        response.setSuccess(true);
        session.setAttribute(SystemConstant.CAPTCHA_NAME, RandomStringUtils.random(4));
        return response;
    }

    @RequestMapping("/showForgetUpdatePd")
    public String forgetUpdatePd(Model model, String token) {
        if (StringUtils.isBlank(token)) {
            model.addAttribute("error", "非法链接！");
            return "/error/default_error";
        }
        String forgetParamJson = null;
        ForgetPasswordDto forgetPasswordDto = null;
        try {
            forgetParamJson = UrlUtil.decodeUrl(token);
            forgetPasswordDto = JSON.parseObject(forgetParamJson, ForgetPasswordDto.class);

        } catch (Exception e) {
            log.error("forgetUpdatePd--解析token异常--e", e);
        }
        if (null == forgetParamJson || StringUtils.isBlank(forgetPasswordDto.getEmail()) || StringUtils.isBlank(forgetPasswordDto.getTokenKey())
                || StringUtils.isBlank(forgetPasswordDto.getTokenValue())
                || (new Date().compareTo(DateUtils.addSeconds(forgetPasswordDto.getSendTime(), SystemConstant.FORGET_PASSWORD_TEIM)) < 0)) {
            model.addAttribute("error", "非法链接！");
            return "/error/default_error";
        }
        model.addAttribute("token",token);
        return VIEW_PATH + "/forgetUpdatePd";
    }

    @RequestMapping("/updateForgetPd")
    @ResponseBody
    public Response updateForgetPd(String token, String newPw, String confirmPw) {
        Response response = Response.newResponse();
        String forgetParamJson = null;
        if (StringUtils.isBlank(newPw)) {
            response.setStatus(1);
            response.setMessage("请输入新密码！");
            return response;
        }
        if (StringUtils.isBlank(confirmPw)) {
            response.setStatus(2);
            response.setMessage("请输入确认密码！");
            return response;
        }
        if (!newPw.equals(confirmPw)) {
            response.setStatus(3);
            response.setMessage("确认密码与新密码不一致！");
            return response;
        }
        try {
            forgetParamJson = UrlUtil.decodeUrl(token);
            ForgetPasswordDto forgetPasswordDto = JSON.parseObject(forgetParamJson, ForgetPasswordDto.class);
            if (null == forgetParamJson || StringUtils.isBlank(forgetPasswordDto.getEmail()) || StringUtils.isBlank(forgetPasswordDto.getTokenKey()) || StringUtils.isBlank(forgetPasswordDto.getTokenValue())) {
                response.setMessage("连接已经失效！");
            } else {
                ForgetPasswordQuery forgetPasswordQuery = new ForgetPasswordQuery();
                forgetPasswordQuery.setEmail(forgetPasswordDto.getEmail());
                forgetPasswordQuery.setTokenKey(forgetPasswordDto.getTokenKey());
                forgetPasswordQuery.setTokenValue(forgetPasswordDto.getTokenValue());
                ForgetPassword forgetPassword = service.selectByEmailAndToken(forgetPasswordQuery);
                if (null == forgetPassword
                        || (new Date().compareTo(DateUtils.addSeconds(forgetPassword.getSendTime(), SystemConstant.FORGET_PASSWORD_TEIM)) < 0)
                        || !forgetPasswordDto.getTokenKey().equals(forgetPassword.getTokenKey())
                        || !forgetPasswordDto.getTokenValue().equals(forgetPassword.getTokenValue())) {
                    response.setMessage("连接已经失效！");
                } else {
                    String salt = PasswordUtil.getSalt();
                    User user = new User();
                    user.setEmail(forgetPassword.getEmail());
                    user.setUpdateAccount("forgetPassword");
                    user.setUpdateName("forgetPassword");
                    user.setRandomSalt(salt);
                    user.setPassword(PasswordUtil.passwordEncrypt(newPw,salt));
                    userService.updatePassword(user);
                    response.setSuccess(true);
                }
            }
        } catch (BusinessException e) {
            response.setMessage(e.getMessage());
            log.error("updateForgetPd--修改密码失败！e=", e);
        } catch (Exception e) {
            response.setMessage("修改密码失败！系统出错啦！");
            log.error("updateForgetPd--修改密码失败！e=", e);
        }
        return response;
    }


}























