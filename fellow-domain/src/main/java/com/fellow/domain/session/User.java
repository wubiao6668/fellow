/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.domain.session;

import java.io.Serializable;
import java.util.Date;

/**
 * 会话用户
 */
public interface User extends Serializable {

    /**
     * 获取当前用户ID
     *
     * @return 当前用户ID
     */
    long getId();

    /**
     * 获取代码
     *
     * @return 用户代码
     */
    String getAccount();

    /**
     * 获取名称
     *
     * @return 名称
     */
    String getName();

    /**
     * 获取角色
     *
     * @return 角色
     */
    long[] getRoles();

    /**
     * 是否登录
     * @return
     */
    boolean isLogin();

    /**
     * 刷新时间
     * @return
     */
    Date getFreshTime();

    void setFreshTime(Date freshTime);

}
