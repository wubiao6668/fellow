package com.fellow.domain.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wubiao on 2016/10/22.
 */
public enum SexEnum {
    MALE(100, "男"),
    FEMALE(200, "女"),
//    GAY_MAN(300, "男同"),
//    LESBIAN(400, "女同"),
//    BISEXUAL(500, "两性"),
    OTHER(600, "其它"),
    ;
    public int key;
    public String value;

    SexEnum(int key, String value) {
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
            for (SexEnum enumTemp : SexEnum.values()) {
                if (enumTemp.getKey() == key.intValue()) {
                    return enumTemp.getValue();
                }
            }
        }
        return OTHER.getValue();
    }

    public static Map<String,Object> getEnumMap(){
        Map<String,Object> map = new LinkedHashMap<String, Object>();
        for (SexEnum enumTemp : SexEnum.values()) {
            map.put(enumTemp.name(),enumTemp);
        }
        return map;
    }
}

