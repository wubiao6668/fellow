package com.fellow.common.interceptor;

import com.fellow.common.annotation.NoNeedLogin;
import com.fellow.domain.session.LocalSession;
import com.fellow.domain.session.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wubiao on 2016/8/30.
 */
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
    @Value("${loginUrl}")
    public String loginUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            NoNeedLogin noNeedLogin = ((HandlerMethod) handler).getMethod().getAnnotation(NoNeedLogin.class);
            if (null != noNeedLogin) {
                return true;
            } else {
                User user = LocalSession.getUserInfo();
                if (user == null || !user.isLogin()) {
                    String preUrl = request.getRequestURI();
                    response.sendRedirect(loginUrl +"?redirectUrl=" + preUrl);
                }
            }
        }
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
