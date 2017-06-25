package com.fellow.domain.enums;

/**
 * Created by wubiao on 2017/2/25.
 */
public enum RestrictDynamicsEnum {
    ALLOW(100,"让ta看我的动态"),
    REFUSE(200,"不让ta看我的动态"),
    ;

    public int key;
    public String value;

    RestrictDynamicsEnum(int key, String value) {
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

    public static final RestrictDynamicsEnum getDefaultShowDynEnum(){
        return RestrictDynamicsEnum.ALLOW;
    }

}
