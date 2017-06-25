/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.common.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
