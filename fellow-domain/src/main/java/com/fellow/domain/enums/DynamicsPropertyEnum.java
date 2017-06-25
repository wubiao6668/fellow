package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/10/9.
 */
public enum DynamicsPropertyEnum {
    PERAONAL(100, "自己"),
    FRIEND(200, "好友"),
    OTHER(300, "他人"),;
    public int key;
    public String value;

    DynamicsPropertyEnum(int key, String value) {
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
            for (DynamicsPropertyEnum enumTemp : DynamicsPropertyEnum.values()) {
                if (enumTemp.getKey() == key.intValue()) {
                    return enumTemp.getValue();
                }
            }
        }
        return null;
    }
}

