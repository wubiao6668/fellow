package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/9/8.
 */
public enum PostImgTypeEnum {
    EMOTICON(100,"表情"),
     POST(200,"帖子上传"),
     REPLY(300,"回复上传"),
    ;
    public int key;
    public String value;

    PostImgTypeEnum(int key, String value) {
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
            for (PostImgTypeEnum postImgType : this.values()) {
                if (postImgType.getKey() == key.intValue()) {
                    return postImgType.getValue();
                }
            }
        }
        return null;
    }
}
