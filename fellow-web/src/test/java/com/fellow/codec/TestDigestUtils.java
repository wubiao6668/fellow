package com.fellow.codec;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.junit.Test;

import java.security.MessageDigest;


/**
 * Created by wubiao on 2016/8/30.
 */
public class TestDigestUtils {

    @Test
    public void testDigest(){

        MessageDigest messageDigest = DigestUtils.getDigest(MessageDigestAlgorithms.MD5);
//        messageDigest.

    }


}
