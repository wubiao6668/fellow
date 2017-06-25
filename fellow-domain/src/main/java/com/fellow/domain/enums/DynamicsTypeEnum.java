package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/10/9.
 */
public enum DynamicsTypeEnum {

    DYNAMICS(100, "动态"),
    POST(200, "帖子上传"),
    REPLY(300, "回复上传"),;
    public int key;
    public String value;

    DynamicsTypeEnum(int key, String value) {
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
            for (DynamicsTypeEnum enumTemp : DynamicsTypeEnum.values()) {
                if (enumTemp.getKey() == key.intValue()) {
                    return enumTemp.getValue();
                }
            }
        }
        return null;
    }
}

