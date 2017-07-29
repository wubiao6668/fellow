package com.fellow.domain.enums;

/**
 * Created by wubiao on 2017/3/24.
 */
public enum FriendTypeEnum {
    GOOD_FRIEND(100, "好友"),
    BLACK_LIST(200, "黑名单"),
    STRANGER(300, "陌生人"),
    ;
    public int key;
    public String value;


    FriendTypeEnum(int key, String value) {
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
}
