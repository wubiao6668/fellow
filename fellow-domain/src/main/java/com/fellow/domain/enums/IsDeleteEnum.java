package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/6/11.
 */
public enum IsDeleteEnum {
    YES(1, "删除"),
    NO(0, "未删除");
    public int key;
    public String value;

    IsDeleteEnum(int key, String value) {
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
            for (IsDeleteEnum enumTemp : IsDeleteEnum.values()) {
                if (enumTemp.getKey() == key.intValue()) {
                    return enumTemp.getValue();
                }
            }
        }
        return null;
    }
}
