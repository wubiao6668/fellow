package com.fellow.common.security;

import com.fellow.common.constant.SystemConstant;
import org.apache.commons.lang.RandomStringUtils;

import java.math.BigInteger;

/**
 * Created by wubiao on 2016/8/31.
 */
public class PasswordUtil {
    //运行后不能再修改
    public final static String saltPre = "abc";

    public static String passwordEncrypt(String passwordText, String salt) throws Exception {
        BigInteger sha = new BigInteger(Coder.encryptSHA((passwordText + saltPre + salt).getBytes()));
        return sha.toString(32);
    }

    public static String getSalt(){
        return RandomStringUtils.randomAlphabetic(5);
    }

    public static String getRegisterCaptcha(String email){
        return SystemConstant.REGISTER_CAPTCHA+email;
    }

    public static final void main(String[] arg) throws Exception {
        System.out.println(PasswordUtil.passwordEncrypt("a564789909", "test"));
    }
}
