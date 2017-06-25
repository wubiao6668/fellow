package com.fellow.domain.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wubiao on 2015/12/7.
 */
public enum EducationEnum {
    OTHER(100, "其他"),
    HIGH_SCHOOL(200, "高中/高中在读"),
    JUNIOR_COLLEGES(300, "专科/专科在读"),
    UNDERGRADUATE(400, "本科/本科在读"),
    POSTGRADUATE(500, "硕士/硕士在读"),
    DOCTOR(600, "博士/博士在读");
    private int key;
    private String value;

    EducationEnum(int key, String value) {
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

    public static EducationEnum getEduByKey(Integer key) {
        if (null == key) {
            return null;
        }
        for (EducationEnum eduTemp : EducationEnum.values()) {
            if (key.intValue() == eduTemp.getKey()) {
                return eduTemp;
            }
        }
        return null;
    }

    public static String getEdeValueByKey(Integer key) {
        if (null == key) {
            return "";
        }
        for (EducationEnum eduTemp : EducationEnum.values()) {
            if (key.intValue() == eduTemp.getKey()) {
                return eduTemp.getValue();
            }
        }
        return "";
    }

    public static int getEdeKeyByValue(String value) {
        for (EducationEnum educationEnumTemp : EducationEnum.values()) {
            if (educationEnumTemp.getValue().equals(value)) {
                return educationEnumTemp.getKey();
            }
        }
        return OTHER.getKey();
    }

    public static Map<String,Object> getEnumMap(){
        Map<String,Object> map = new LinkedHashMap<String, Object>();
        for (EducationEnum enumTemp : EducationEnum.values()) {
            map.put(enumTemp.name(),enumTemp);
        }
        return map;
    }

}
