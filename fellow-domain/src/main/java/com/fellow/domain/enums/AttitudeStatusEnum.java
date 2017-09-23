package com.fellow.domain.enums;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wubiao on 2017/3/9.
 */
public enum AttitudeStatusEnum {
    CANCEL(100, "取消"),
    UP(200, "点赞"),
    LOVE(300, "爱心"),
    DOWN(400, "踩"), ;

    public int key;
    public String value;

    AttitudeStatusEnum(int key, String value) {
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
            for (AttitudeStatusEnum enumTemp : AttitudeStatusEnum.values()) {
                if (key.intValue() == enumTemp.getKey()) {
                    return enumTemp.getValue();
                }
            }
        }
        return "";
    }

    public static Map getMap() {
        Map<String, AttitudeStatusEnum> thumbsTypeEnumMap = new LinkedHashMap();
        for (AttitudeStatusEnum enumTemp : AttitudeStatusEnum.values()) {
            thumbsTypeEnumMap.put(enumTemp.name(), enumTemp);
        }
        return thumbsTypeEnumMap;
    }
}
