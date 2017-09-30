/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.web.controller.login;

import com.fellow.domain.constant.SystemConstant;
import com.fellow.common.security.CookiesUtil;
import com.fellow.common.security.PasswordUtil;
import com.fellow.common.util.captcha.XVcode.generator.Generator;
import com.fellow.common.util.captcha.XVcode.generator.PngVCGenerator;
import com.fellow.domain.model.User;
import com.fellow.domain.query.UserQuery;
import com.fellow.domain.session.UserInfo;
import com.fellow.domain.web.Response;
import com.fellow.service.UserService;
import com.fellow.web.base.WebAbstract;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by wubiao on 2016/8/9.
 */
@Controller
@RequestMapping("login")
public class LoginController extends WebAbstract<UserService> {
    private Log log = LogFactory.getLog(this.getClass());
    @Resource
    private UserService userService;

    @RequestMapping("/index ")
    public String index(Model model, String redirectUrl) {
        model.addAttribute("redirectUrl", redirectUrl);
        return "/login/index";
    }

    @RequestMapping("/showLogin ")
    public String showLogin() {
        return "/login/content";
    }

    @RequestMapping("/signUp ")
    public String signUp() {
        return "/login/login";
    }

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest servletRequest, HttpServletResponse response) {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        ServletOutputStream outputStream = null;
        try {
            HttpSession httpSession = servletRequest.getSession();
            outputStream = response.getOutputStream();
            Integer height = 30;
            Integer width = 120;
            Integer count = 5;
            Generator generator = new PngVCGenerator(width, height, count);
            generator.write2out(outputStream);
            String captcha = generator.text();
//            System.out.println(generator.text());
            httpSession .setAttribute(SystemConstant.CAPTCHA_NAME, captcha);
            response.setContentType("image/png");
        } catch (Exception e) {
            log.error("captcha--验证码异常--e=", e);
        }finally {
            if (null != outputStream){
                try {
                    outputStream.close();
                } catch (Exception e) {
                    log.error("captcha--生成验证码关闭流异常--e=",e);
                }
            }
        }
    }


    /**
     * 登录
     *
     * @param userQuery
     * @return
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Response login(UserQuery userQuery, HttpServletRequest request, HttpServletResponse servletResponse) {
        log.info("--【method】login--登录--account=" + userQuery.getAccount());
        HttpSession session =request.getSession();
        Response response = Response.newResponse();
        if (StringUtils.isBlank(userQuery.getAccount())) {
            response.setMessage("请输入账号");
        } else if (StringUtils.isBlank(userQuery.getPassword())) {
            response.setMessage("请输入密码");
        } else if (StringUtils.isBlank(userQuery.getCaptcha()) || !userQuery.getCaptcha().equals(session.getAttribute(SystemConstant.CAPTCHA_NAME))) {
            response.setMessage("请输入正确的验证码");
        } else {
            User user = userService.queryByAccount(userQuery);
            try {
                if (null != user && PasswordUtil.passwordEncrypt(userQuery.getPassword(), user.getRandomSalt()).equals(user.getPassword())) {
                    UserInfo loginUser = new UserInfo();
                    loginUser.setAccount(user.getAccount());
                    loginUser.setName(user.getUsername());
                    loginUser.setLogin(true);
                    loginUser.setFreshTime(new Date());
                    CookiesUtil.userInfoToCookies(servletResponse, loginUser);
                    response.setSuccess(true);
                } else {
                    response.setMessage("账户或密码不对");
                }
            } catch (Exception e) {
                response.setMessage("系统繁忙请稍后再试");
                log.error("--【method】login--密码加密异常--password=" + userQuery.getPassword() + "--salt=" + user.getRandomSalt() + "--e=", e);
            }
        }
        session.setAttribute(SystemConstant.CAPTCHA_NAME, RandomStringUtils.random(4));
        return response;
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws Exception {
        CookiesUtil.logout(servletResponse);
        servletResponse.sendRedirect("/");
    }

    @RequestMapping("/selectSchool")
    public String selectSchool() {
        return "/common/selectSchool";
    }

    @RequestMapping("/selectMajor")
    public String selectMajor() {
        return "/common/selectMajor";
    }


}
