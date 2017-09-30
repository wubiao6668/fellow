/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.web.controller.login;

import com.fellow.domain.constant.SystemConstant;
import com.fellow.common.security.PasswordUtil;
import com.fellow.common.util.VelocityUtil;
import com.fellow.domain.dto.RegisterDto;
import com.fellow.domain.email.EmailInfo;
import com.fellow.domain.model.User;
import com.fellow.domain.model.UserDetail;
import com.fellow.domain.query.UserQuery;
import com.fellow.domain.web.Response;
import com.fellow.service.UserService;
import com.fellow.web.base.WebAbstract;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by wubiao on 2016/8/9.
 */
@Controller
@RequestMapping(RegisterController.VIEW_PATH)
public class RegisterController extends WebAbstract<UserService> {
    public static final String VIEW_PATH = "/login/register";
    private Log log = LogFactory.getLog(this.getClass());

    @RequestMapping("/index ")
    public String index(Model model, String redirectUrl) {
        model.addAttribute("redirectUrl", redirectUrl);
        return VIEW_PATH + "/index";
    }

    @RequestMapping("/checkedAccount")
    @ResponseBody
    public Response checkedAccount(String account) {
        Response response = Response.newResponse();
        if (StringUtils.isBlank(account)) {
            response.setMessage("账号已经存在！");
            return response;
        }
        UserQuery userQuery = new UserQuery();
        userQuery.setAccount(account);
        User user = service.checkedAccount(userQuery);
        if (null != user) {
            response.setMessage("账号已经存在！");
            return response;
        }
        response.setSuccess(true);
        return response;
    }

    @RequestMapping("/checkedEmail")
    @ResponseBody
    public Response checkedEmail(String email) {
        Response response = Response.newResponse();
        if (StringUtils.isBlank(email)) {
            response.setMessage("邮箱已经存在");
            return response;
        }
        UserQuery userQuery = new UserQuery();
        userQuery.setEmail(email);
        User user = service.checkedEmail(userQuery);
        if (null != user) {
            response.setMessage("邮箱已经存在");
            return response;
        }
        response.setSuccess(true);
        return response;
    }

    @RequestMapping("/userRegister")
    @ResponseBody
    public Response userRegister(RegisterDto registerDto, HttpServletRequest servletRequest) {
        Response response = Response.newResponse();
        HttpSession session = servletRequest.getSession();
        try {
            if (StringUtils.isBlank(registerDto.getAccount())) {
                response.setStatus(1);
                response.setMessage("请输入登录账号");
                return response;
            }
            if (StringUtils.isBlank(registerDto.getUsername())) {
                response.setStatus(2);
                response.setMessage("请输入昵称账号");
                return response;
            }
            if (StringUtils.isBlank(registerDto.getEmail())) {
                response.setStatus(3);
                response.setMessage("请输入正确的邮箱");
                return response;
            }
            if (StringUtils.isBlank(registerDto.getPassword())) {
                response.setStatus(4);
                response.setMessage("请输入密码");
                return response;
            }
            if (StringUtils.isBlank(registerDto.getConfirmPassword())) {
                response.setStatus(5);
                response.setMessage("请输入确认正确密码");
                return response;
            }
            if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
                response.setStatus(5);
                response.setMessage("两次输入的密码不一致");
                return response;
            }
            if (StringUtils.isBlank(registerDto.getEmailCaptcha())) {
                response.setStatus(7);
                response.setMessage("邮箱验证码不能为空");
                return response;
            }
            if (StringUtils.isBlank(registerDto.getCaptchaToken())) {
                response.setStatus(7);
                response.setMessage("邮箱验证码错误！");
                return response;
            }
//            String captchaToken = (String) session.getAttribute(PasswordUtil.getRegisterCaptcha(registerDto.getEmail()));
//            if (StringUtils.isBlank(captchaToken)) {
//                response.setStatus(7);
//                response.setMessage("邮箱验证码已过期请重亲获取！");
//                return response;
//            }
//            if (!(registerDto.getCaptchaToken() + registerDto.getEmailCaptcha()).equals(captchaToken)) {
//                response.setStatus(8);
//                response.setMessage("邮箱验证码错误");
//                return response;
//            }
            UserQuery userQuery = new UserQuery();
            userQuery.setAccount(registerDto.getAccount());
            User user = service.checkedAccount(userQuery);
            if (null != user) {
                response.setStatus(1);
                response.setMessage("账号已经存在");
                return response;
            }
            userQuery = new UserQuery();
            userQuery.setEmail(registerDto.getEmail());
            user = service.checkedEmail(userQuery);
            if (null != user) {
                response.setStatus(3);
                response.setMessage("邮箱已经存在");
                return response;
            }

            User registerUser = new User();
            registerUser.setAccount(registerDto.getAccount());
            registerUser.setUsername(registerDto.getUsername());
            registerUser.setEmail(registerDto.getEmail());
            registerUser.setRandomSalt(PasswordUtil.getSalt());
            registerUser.setPassword(PasswordUtil.passwordEncrypt(registerDto.getPassword(), registerUser.getRandomSalt()));
            registerUser.setAvatar(SystemConstant.DEFAULT_USER_AVATAR);
            UserDetail userDetail = new UserDetail();
            userDetail.setAccount(registerDto.getAccount());
            userDetail.setUsername(registerDto.getUsername());
            userDetail.setEmail(registerDto.getEmail());
            userDetail.setAvatar(SystemConstant.DEFAULT_USER_AVATAR);
            registerUser.setUserDetail(userDetail);
            service.registerUser(registerUser);
            response.setSuccess(true);
        } catch (Exception e) {
            response.setMessage("注册失败！系统异常！");
            log.error("userRegister--注册异常--e,", e);
        }
        session.setAttribute(SystemConstant.CAPTCHA_NAME, RandomStringUtils.random(4));
        return response;
    }

    @RequestMapping(value = "getEmailCaptcha")
    @ResponseBody
    public Response getEmailCaptcha(String email, String captcha, HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        Response response = Response.newResponse();
        if (StringUtils.isBlank(email)) {
            response.setStatus(1);
            response.setMessage("邮箱不能为空");
            return response;
        }
        if (StringUtils.isBlank(captcha)) {
            response.setStatus(2);
            response.setMessage("验证码不能为空");
            return response;
        }
        if (!captcha.equals(session.getAttribute(SystemConstant.CAPTCHA_NAME))) {
            response.setStatus(2);
            response.setMessage("验证码错误");
            return response;
        }
        UserQuery userQuery = new UserQuery();
        userQuery.setEmail(email);
        User user = service.checkedEmail(userQuery);
        if (null != user) {
            response.setStatus(1);
            response.setMessage("邮箱已经存在");
            return response;
        }
        String tokenKey = RandomStringUtils.randomAlphabetic(5);
        String tokenValue = RandomStringUtils.randomAlphanumeric(5);
        //邮件
        EmailInfo emailInfo = new EmailInfo();
        emailInfo.setSubject("那人说网注册码");
        emailInfo.setToAddr(new String[]{email});
        Context context = new VelocityContext();
        context.put("tokenValue", tokenValue);
        String bodyHtml = VelocityUtil.mergeTemplate(context, velocityConfig.getVelocityEngine(), "/template/email/register_captcha.vm");
        emailInfo.setBodyHtml(bodyHtml);
        emailService.sendMailByThreadPool(emailInfo);
        session.setAttribute(PasswordUtil.getRegisterCaptcha(email), tokenKey + tokenValue);
        response.setBody(tokenKey);
        response.setMessage("邮箱验证码已发送到您的邮箱你注意查收！");
        response.setSuccess(true);
        return response;

    }

}










































