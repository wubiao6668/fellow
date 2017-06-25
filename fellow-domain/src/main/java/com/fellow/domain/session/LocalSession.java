/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.domain.session;


/**
 * 本地线程会话
 */
public class LocalSession implements Session {

    private static final ThreadLocal<User> local = new ThreadLocal<User>();

    private static final Session session = new LocalSession();

    @Override
    public User getUser() {
        User user = local.get();
        return null == user ? new UserInfo() :  user;
    }

    public static User getUserInfo() {
        User user = local.get();
        return null == user ? new UserInfo() :  user;
    }
    @Override
    public long getUserId() {
        User user = local.get();
        return user == null ? 0 : user.getId();
    }

    /**
     * 获取用户pin
     *
     * @return
     */
    @Override
    public String getUserAccount() {
        User user = local.get();
        return user == null ? null : user.getAccount();
    }

    /**
     * 获取用户名
     *
     * @return
     */
    @Override
    public String getUserName() {
        User user = local.get();
        return user == null ? null : user.getName();
    }

    @Override
    public void setUser(User user) {
        local.set(user);
    }

    /**
     * 单例函数
     *
     * @return 会话
     */
    public static Session getSession() {
        return session;
    }

}
