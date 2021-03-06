package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/10/9.
 */
public enum DynAuthorityEnum {

    ONLY_MYSELF(100, "对自己可见"),
    ONLY_FRIEND(200, "对好友可见"),
    OPEN_ALL(300, "对所有人可见"),;
    public int key;
    public String value;

    DynAuthorityEnum(int key, String value) {
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
            for (DynAuthorityEnum enumTemp : DynAuthorityEnum.values()) {
                if (enumTemp.getKey() == key.intValue()) {
                    return enumTemp.getValue();
                }
            }
        }
        return null;
    }

    public static DynAuthorityEnum getDefault() {
        return DynAuthorityEnum.ONLY_FRIEND;
    }
}

