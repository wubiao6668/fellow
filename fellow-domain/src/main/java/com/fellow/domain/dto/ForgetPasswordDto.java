package com.fellow.domain.dto;

import com.fellow.domain.model.ForgetPassword;

/**
 * Created by wubiao on 2017/2/16.
 */
public class ForgetPasswordDto extends ForgetPassword {

    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
