/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.common.exception;

public class StandardException extends Exception {
    public StandardException(String msg) {
        super(msg);
    }
    public StandardException(Throwable cause) {
        super(cause);
    }
    public StandardException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
