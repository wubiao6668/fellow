package com.fellow.domain.enums;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wubiao on 2017/3/9.
 */
public enum ThumbsTypeEnum {
    CANCEL(100, "点取消"),
    UP(200, "点赞"),
    LOVE(300, "送爱心"),
    DOWN(400, "点踩"), ;

    public int key;
    public String value;

    ThumbsTypeEnum(int key, String value) {
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
            for (ThumbsTypeEnum enumTemp : ThumbsTypeEnum.values()) {
                if (key.intValue() == enumTemp.getKey()) {
                    return enumTemp.getValue();
                }
            }
        }
        return "";
    }

    public static Map getMap() {
        Map<String, ThumbsTypeEnum> thumbsTypeEnumMap = new LinkedHashMap();
        for (ThumbsTypeEnum enumTemp : ThumbsTypeEnum.values()) {
            thumbsTypeEnumMap.put(enumTemp.name(), enumTemp);
        }
        return thumbsTypeEnumMap;
    }
}
