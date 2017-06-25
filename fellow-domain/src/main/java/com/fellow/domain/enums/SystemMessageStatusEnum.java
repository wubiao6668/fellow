package com.fellow.domain.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wubiao on 2016/11/11.
 */
public enum SystemMessageStatusEnum {
    INIT(100, "系统"),
    AGREE(200, "同意"),;
    public int key;
    public String value;

    SystemMessageStatusEnum(int key, String value) {
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
            for (SystemMessageStatusEnum enumTemp : SystemMessageStatusEnum.values()) {
                if (enumTemp.getKey() == key.intValue()) {
                    return enumTemp.getValue();
                }
            }
        }
        return null;
    }

    public static Map<String, Object> getEnumMap() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        for (SystemMessageStatusEnum enumTemp : SystemMessageStatusEnum.values()) {
            map.put(enumTemp.name(), enumTemp);
        }
        return map;
    }
}
