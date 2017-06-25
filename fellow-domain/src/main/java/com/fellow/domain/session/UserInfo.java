/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.domain.session;

import java.util.Date;

/**
 * 默认用户信息
 */
public class UserInfo implements User {

    // ID
    protected long id;
    // 代码
    protected String account;
    // 名称
    protected String name;
    // 角色
    protected long[] roles;
    //是否登录
    protected boolean isLogin = false;
    //刷新时间
    protected Date freshTime = new Date();

    public UserInfo() {
    }

    public UserInfo(long id, String account, String name, long[] roles) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.roles = roles;
    }

    @Override
    public long getId() {
        return 0;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long[] getRoles() {
        return this.roles;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoles(long[] roles) {
        this.roles = roles;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    @Override
    public boolean isLogin() {
        return isLogin;
    }

    public Date getFreshTime() {
        return freshTime;
    }

    public void setFreshTime(Date freshTime) {
        this.freshTime = freshTime;
    }
}
