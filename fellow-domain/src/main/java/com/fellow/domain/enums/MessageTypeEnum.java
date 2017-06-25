package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/11/11.
 */
public enum MessageTypeEnum {
    USER(100, "用户"),
    SYSTEM(200, "系统"),;
    public int key;
    public String value;

    MessageTypeEnum(int key, String value) {
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
            for (MessageTypeEnum enumTemp : MessageTypeEnum.values()) {
                if (enumTemp.getKey() == key.intValue()) {
                    return enumTemp.getValue();
                }
            }
        }
        return null;
    }
}
