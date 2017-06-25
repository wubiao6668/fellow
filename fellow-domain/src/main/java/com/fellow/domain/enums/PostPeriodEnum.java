package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/9/3.
 */
public enum PostPeriodEnum {
//    '100-凌晨(1：00—5：00),200-早上(5：00—8：00),300-上午(8：00—11：00),400-中午(11：00—13：00),500-下午(13：00—17：00),600-晚上(17：00—19：00),700-半夜(19：00—20：00),800-深夜(20：00—24：00)',
    EARLY_MORNING(100,"凌晨(1：00—5：00)"),
    MORNING(200,"早上(5：00—8：00)"),
    FORENOON(300,"上午(8：00—11：00)"),
    NOON(400,"中午(11：00—13：00)"),
    AFTERNOON(500,"下午(13：00—17：00)"),
    EVENING(600,"晚上(17：00—19：00)"),
    MIDNIGHT(700,"半夜(19：00—20：00)"),
    LATE_NIGHT(800,"深夜(20：00—24：00)"),
    ;
    public int key;
    public String value;

    PostPeriodEnum(int key, String value) {
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

    public String getValueByKey(Integer key) {
        if (null != key) {
            for (PostPeriodEnum periodEnumTemp : this.values()) {
                if (periodEnumTemp.getKey() == key.intValue()) {
                    return periodEnumTemp.getValue();
                }
            }
        }
        return null;
    }
}
