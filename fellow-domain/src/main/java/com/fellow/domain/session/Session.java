/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.domain.session;


/**
 * 会话接口
 */
public interface Session {
    /**
     * 用户KEY
     */
    String USER = "user";

    /**
     * 获取当前用户信息
     *
     * @return 当前用户信息
     */
    User getUser();

    /**
     * 获取用户ID
     * @return 用户ID
     */
    long getUserId();

    /**
     * 获取用户pin
     * @return
     */
    String getUserAccount();

    /**
     * 获取用户名
     * @return
     */
    String getUserName();

    /**
     * 设置当前会话用户信息
     *
     * @param user 用户信息
     */
    void setUser(User user);
}
