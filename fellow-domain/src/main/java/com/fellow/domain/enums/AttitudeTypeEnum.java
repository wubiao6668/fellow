package com.fellow.domain.enums;

/**
 * Created by wubiao on 17/9/2017.
 */
public enum AttitudeTypeEnum {
    POST(100, "帖子"),
    COMMENT(200, "评论"),
    REPLY(300, "回复"),
    ;

    public int key;
    public String value;

    AttitudeTypeEnum(int key, String value) {
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

    public static boolean containsType(Integer type){
        if (null != type){
            for (AttitudeTypeEnum enumTemp : AttitudeTypeEnum.values()) {
                if (type == enumTemp.getKey()){
                    return true;
                }
            }
        }
        return false;
    }

}
