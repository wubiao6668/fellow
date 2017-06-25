package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/9/24.
 */
public enum PostDeleteEnum {

    LOST_NOT_DELETE(100, "未删除"),
    LOST_DELETE(200, "删除"),;

    public int key;
    public String value;

    PostDeleteEnum(int key, String value) {
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

    public String getValueByKey(Integer key) {
        if (null != key) {
            for (PostDeleteEnum enumTmpe : this.values()) {
                if (enumTmpe.getKey() == key.intValue()) {
                    return enumTmpe.getValue();
                }
            }
        }
        return null;
    }
}

