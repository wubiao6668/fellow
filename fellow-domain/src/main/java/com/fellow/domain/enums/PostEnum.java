package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/9/19.
 */
public enum PostEnum {
    LOST_POST(100,"寻物启事"),
    ;
    public int key;
    public String value;

    PostEnum(int key, String value) {
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
            for (PostEnum postImgType : PostEnum.values()) {
                if (postImgType.getKey() == key.intValue()) {
                    return postImgType.getValue();
                }
            }
        }
        return null;
    }
}
