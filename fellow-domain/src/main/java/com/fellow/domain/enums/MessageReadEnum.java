package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/11/11.
 */
public enum MessageReadEnum {

    UN_READ(100, "未读"),
    HAD_READ(200, "已读"),;
    public int key;
    public String value;

    MessageReadEnum(int key, String value) {
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
            for (MessageReadEnum enumTemp : MessageReadEnum.values()) {
                if (enumTemp.getKey() == key.intValue()) {
                    return enumTemp.getValue();
                }
            }
        }
        return null;
    }
}
