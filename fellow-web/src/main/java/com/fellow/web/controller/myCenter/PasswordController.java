package com.fellow.web.controller.myCenter;

import com.fellow.common.security.CookiesUtil;
import com.fellow.common.security.PasswordUtil;
import com.fellow.domain.model.User;
import com.fellow.domain.query.UserQuery;
import com.fellow.domain.session.UserInfo;
import com.fellow.domain.web.Response;
import com.fellow.service.UserService;
import com.fellow.web.base.WebAbstract;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by wubiao on 2016/11/24.
 */
@Controller
@RequestMapping(PasswordController.VIEW_PATH)
public class PasswordController extends WebAbstract<UserService> {
    public static final String VIEW_PATH = "/myCenter/passwordSet";


    @RequestMapping("/index")
    public String index(Model model) {
        return VIEW_PATH + "/index";
    }


    @RequestMapping(value = "/updatePassword", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Response updatePassword(String oldPw, String newPw, String confirmPw, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        Response response = Response.newResponse();
        if (StringUtils.isBlank(oldPw)) {
            response.setStatus(1);
            response.setMessage("请输入原始密码！");
        } else if (StringUtils.isBlank(newPw)) {
            response.setStatus(2);
            response.setMessage("请输入新密码！");
        } else if (StringUtils.isBlank(confirmPw)) {
            response.setStatus(3);
            response.setMessage("请输入确认密码！");
        } else if (!newPw.equals(confirmPw)) {
            response.setStatus(3);
            response.setMessage("确认密码与新密码不一致！");
        } else {
            UserQuery userQuery = new UserQuery();
            userQuery.setAccount(super.getAccount());
            User user = service.queryByAccount(userQuery);
            try {
                if (null != user && PasswordUtil.passwordEncrypt(oldPw, user.getRandomSalt()).equals(user.getPassword())) {
                    UserInfo loginUser = new UserInfo();
                    loginUser.setAccount(user.getAccount());
                    loginUser.setName(user.getUsername());
                    loginUser.setLogin(true);
                    loginUser.setFreshTime(new Date());
                    CookiesUtil.userInfoToCookies(servletResponse, loginUser);
                    response.setSuccess(true);
                } else {
                    response.setStatus(1);
                    response.setMessage("请输入正确的原始密码！");
                }
            } catch (Exception e) {
                response.setMessage("系统繁忙请稍后再试");
                log.error("--【method】login--密码加密异常--password=" + userQuery.getPassword() + "--salt=" + user.getRandomSalt() + "--e=", e);
            }
        }
        return response;
    }
}
