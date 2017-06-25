package com.fellow.domain.enums;

/**
 * Created by wubiao on 2017/2/25.
 */
public enum ShowDynEnum {
    ONLY_FRIEND(100,"只看好友"),
    ALL_USER(200,"所有用户"),
    ;

    public int key;
    public String value;

    ShowDynEnum(int key, String value) {
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

    public static final ShowDynEnum getDefaultShowDynEnum(){
        return ShowDynEnum.ALL_USER;
    }

}
