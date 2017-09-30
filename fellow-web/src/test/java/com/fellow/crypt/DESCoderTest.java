package com.fellow.crypt;

import static org.junit.Assert.*;


import com.fellow.domain.constant.SystemConstant;
import com.fellow.common.security.DESCoder;
import org.junit.Test;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class DESCoderTest {

    @Test
    public void testdddd() throws Exception {
        String inputStr = "DESDESDESD原文原文原文原文原文原文原文原文原文原文原文原文原文原文原文原文原文原文原文原文ESDESDESDESDESDESDESDESDESDESDES";
        String key = SystemConstant.DESC_CODER_KEY;
        System.err.println("原文:\t" + inputStr);

        System.err.println("密钥:\t" + key);

        byte[] inputData = inputStr.getBytes();
//        inputData = DESCoder.encrypt(inputData, key);
        String aa = URLEncoder.encode(DESCoder.encryptBASE64(DESCoder.encrypt(inputData, SystemConstant.DESC_CODER_KEY)),"UTF-8");

        System.err.println("加密后:\t" + aa);

//        byte[] outputData = DESCoder.decrypt(DESCoder.decryptBASE64(URLDecoder.decode(aa,"utf-8")), key);
        String outputStr = new String(DESCoder.decrypt(DESCoder.decryptBASE64(URLDecoder.decode(aa,"utf-8")), SystemConstant.DESC_CODER_KEY));

        System.err.println("解密后:\t" + outputStr);

        assertEquals(inputStr, outputStr);
    }
}
