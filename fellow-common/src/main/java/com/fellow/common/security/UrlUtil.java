package com.fellow.common.security;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by wubiao on 2017/2/16.
 */
public class UrlUtil {

    public static String encodeUrl(String url) throws Exception {
        return URLEncoder.encode(DESCoder.encryptBASE64(url.getBytes()), "UTF-8");
    }

    public static String decodeUrl(String url) throws Exception {
        return new String(DESCoder.decryptBASE64(URLDecoder.decode(url, "utf-8")));
    }


}
