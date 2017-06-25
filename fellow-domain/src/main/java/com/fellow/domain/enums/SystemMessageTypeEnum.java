package com.fellow.domain.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wubiao on 2016/11/11.
 */
public enum SystemMessageTypeEnum {
    SYSTEM(100, "系统"),
    FRIEND_REQUEST(200, "好友请求"),
    FRIEND_REQUEST_FROM(210, "请求添加您为好友"),
    FRIEND_REQUEST_TO(220, "我请求添加对方为好友"),
    ;
    public int key;
    public String value;

    SystemMessageTypeEnum(int key, String value) {
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
            for (SystemMessageTypeEnum enumTemp : SystemMessageTypeEnum.values()) {
                if (enumTemp.getKey() == key.intValue()) {
                    return enumTemp.getValue();
                }
            }
        }
        return null;
    }

    public static Map<String,Object> getEnumMap(){
        Map<String,Object> map = new LinkedHashMap<String, Object>();
        for (SystemMessageTypeEnum enumTemp : SystemMessageTypeEnum.values()) {
            map.put(enumTemp.name(),enumTemp);
        }
        return map;
    }
}
