package com.fellow.domain.enums;

/**
 * Created by wubiao on 2017/2/15.
 */
public enum  ForgetPasswordEnum {
SEND_RESET(100,"发送重置"),
SEND_PASSWORD(200,"发送密码"),
;
    public int key;
    public String value;

    ForgetPasswordEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValueByKey(Integer key) {
        if (null != key) {
            for (ForgetPasswordEnum enumTemp : ForgetPasswordEnum.values()) {
                if (enumTemp.getKey() == key.intValue()) {
                    return enumTemp.getValue();
                }
            }
        }
        return null;
    }


}
