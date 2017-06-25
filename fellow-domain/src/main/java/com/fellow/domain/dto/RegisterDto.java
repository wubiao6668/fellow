package com.fellow.domain.dto;

import java.io.Serializable;

/**
 * Created by wubiao on 2017/2/20.
 */
public class RegisterDto implements Serializable {
    private static final long serialVersionUID = 7180111133838378870L;


    /**
     * 账号
     */
    private String account;
    /**
     * 昵称
     */
    private String username;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 去人密码
     */
    private String confirmPassword;
    /**
     * 验证码
     */
    private String captcha;

    /**
     * 邮箱验证码
     */
    private String emailCaptcha;
    /**
     * 邮箱验证码令牌
     */
    private String captchaToken;


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailCaptcha() {
        return emailCaptcha;
    }

    public void setEmailCaptcha(String emailCaptcha) {
        this.emailCaptcha = emailCaptcha;
    }

    public String getCaptchaToken() {
        return captchaToken;
    }

    public void setCaptchaToken(String captchaToken) {
        this.captchaToken = captchaToken;
    }
}
