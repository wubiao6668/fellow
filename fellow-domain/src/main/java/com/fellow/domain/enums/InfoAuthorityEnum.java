package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/10/9.
 */
public enum InfoAuthorityEnum {

    ONLY_MYSELF(100, "对自己可见"),
    ONLY_FRIEND(200, "对好友可见"),
    OPEN_ALL(300, "对所有人可见"),;
    public int key;
    public String value;

    InfoAuthorityEnum(int key, String value) {
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
            for (InfoAuthorityEnum enumTemp : InfoAuthorityEnum.values()) {
                if (enumTemp.getKey() == key.intValue()) {
                    return enumTemp.getValue();
                }
            }
        }
        return null;
    }

    public static InfoAuthorityEnum getDefault() {
        return InfoAuthorityEnum.ONLY_FRIEND;
    }
}

