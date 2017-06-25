package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/9/1.
 */
public enum LostPostTypeEnum {
    LOST(100,"丢失"),
    PICK_UP(200,"拾到"),
    ;
    public int key;
    public String value;

    LostPostTypeEnum(int key, String value) {
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
            for (LostPostTypeEnum lostPostEnumTemp : LostPostTypeEnum.values()) {
                if (lostPostEnumTemp.getKey() == key.intValue()) {
                    return lostPostEnumTemp.getValue();
                }
            }
        }
        return null;
    }
}
