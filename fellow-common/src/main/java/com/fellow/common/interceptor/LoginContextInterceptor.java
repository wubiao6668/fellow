package com.fellow.common.interceptor;

import com.fellow.domain.constant.SystemConstant;
import com.fellow.common.security.CookiesUtil;
import com.fellow.domain.session.LocalSession;
import com.fellow.domain.session.Session;
import com.fellow.domain.session.User;
import com.fellow.domain.session.UserInfo;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by wubiao on 2016/8/30.
 */
public class LoginContextInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = CookiesUtil.cookiesToUserInfo(request);
        if (null == user) {
            user = new UserInfo();
        } else {
            Date freshTime = user.getFreshTime();
            if (null == freshTime || System.currentTimeMillis() - freshTime.getTime() >= SystemConstant.COOKIES_EXPIRY) {
                user = new UserInfo();
//                return true;
            }
        }
        user.setFreshTime(new Date());
        Session session = new LocalSession();
        session.setUser(user);
        CookiesUtil.userInfoToCookies(response, user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
