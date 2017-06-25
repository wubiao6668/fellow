package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/10/26.
 */
public enum RelationshipStatusEnum {
    UNMARRIED(100, "未婚"),
    DIVORCE(200, "离异"),
    WIDOWED(300, "丧偶"),
    MARRIED(400, "已婚"),;
    public int key;
    public String value;

    RelationshipStatusEnum(int key, String value) {
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
            for (RelationshipStatusEnum enumTemp : RelationshipStatusEnum.values()) {
                if (enumTemp.getKey() == key.intValue()) {
                    return enumTemp.getValue();
                }
            }
        }
        return null;
    }
}
