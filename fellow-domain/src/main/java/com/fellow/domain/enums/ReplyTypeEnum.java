package com.fellow.domain.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wubiao on 2017/4/5.
 */
public enum ReplyTypeEnum {
    POST_REPLY(100, "帖子评论"),
    COMMENT_REPLY(200, "回复评论"),;

    public int key;
    public String value;

    ReplyTypeEnum(int key, String value) {
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

    public static Map<String, ReplyTypeEnum> getEnumMap() {
        Map<String, ReplyTypeEnum> map = new LinkedHashMap<String, ReplyTypeEnum>();
        for (ReplyTypeEnum enumTemp : ReplyTypeEnum.values()) {
            map.put(enumTemp.name(), enumTemp);
        }
        return map;
    }
}
