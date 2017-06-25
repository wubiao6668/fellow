package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/10/9.
 */
public enum StrangerMessageEnum {

    RECEIVE(100, "接收"),
    REFUSE(200, "拒收"),;
    public int key;
    public String value;

    StrangerMessageEnum(int key, String value) {
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
            for (StrangerMessageEnum enumTemp : StrangerMessageEnum.values()) {
                if (enumTemp.getKey() == key.intValue()) {
                    return enumTemp.getValue();
                }
            }
        }
        return null;
    }

    public static StrangerMessageEnum getDefault() {
        return StrangerMessageEnum.RECEIVE;
    }
}

