package com.fellow.common.security;

import com.alibaba.fastjson.JSON;
import com.fellow.domain.constant.SystemConstant;
import com.fellow.domain.session.User;
import com.fellow.domain.session.UserInfo;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by wubiao on 2016/8/31.
 */
public class CookiesUtil {

    public static void userInfoToCookies(HttpServletResponse servletResponse, User user) throws Exception {
        Cookie cookie = new Cookie(SystemConstant.TOKEN, genUserInfoToToken(user));
        cookie.setMaxAge(-1);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        servletResponse.addCookie(cookie);
    }

    public static User cookiesToUserInfo(HttpServletRequest servletRequest) throws Exception {
        Cookie[] cookies = servletRequest.getCookies();
        if (ArrayUtils.isNotEmpty(cookies)) {
            for (Cookie cookieTemp : cookies) {
                if (SystemConstant.TOKEN.equals(cookieTemp.getName()) && StringUtils.isNotBlank(cookieTemp.getValue())) {
                    return parseTokenToUserInfo(cookieTemp.getValue());
                }
            }
        }
        return new UserInfo();
    }

    public static String genUserInfoToToken(User user) throws Exception {
        String token = JSON.toJSONString(user);
        return URLEncoder.encode(DESCoder.encryptBASE64(DESCoder.encrypt(token.getBytes(), SystemConstant.DESC_CODER_KEY)), "UTF-8");
    }

    public static User parseTokenToUserInfo(String token) throws Exception {
        String tokenText = new String(DESCoder.decrypt(DESCoder.decryptBASE64(URLDecoder.decode(token, "utf-8")), SystemConstant.DESC_CODER_KEY));
        return JSON.parseObject(tokenText, UserInfo.class);
    }

    public static void logout(HttpServletResponse servletResponse) throws Exception {
        Cookie cookie = new Cookie(SystemConstant.TOKEN, null);
//        cookie.setValue();
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        servletResponse.addCookie(cookie);
    }

}
