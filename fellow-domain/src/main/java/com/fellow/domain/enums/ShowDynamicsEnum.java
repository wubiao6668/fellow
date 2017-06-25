package com.fellow.domain.enums;

/**
 * Created by wubiao on 2017/2/25.
 */
public enum ShowDynamicsEnum {
    LOOK(100,"展示ta的动态"),
    NOT_LOOK(200,"不展示ta的动态"),
    ;

    public int key;
    public String value;

    ShowDynamicsEnum(int key, String value) {
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

    public static final ShowDynamicsEnum getDefaultShowDynEnum(){
        return ShowDynamicsEnum.LOOK;
    }

}
