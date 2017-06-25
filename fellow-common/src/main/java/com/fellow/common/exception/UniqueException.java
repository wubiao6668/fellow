/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.common.exception;

public class UniqueException extends BusinessException {
    public UniqueException(String msg) {
        super(msg);
    }

    public UniqueException(Throwable cause) {
        super(cause);
    }

    public UniqueException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
