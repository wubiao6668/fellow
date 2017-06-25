package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/9/1.
 */
public enum PostPlaceTypeEnum {
    //  100-帖子附近 200-公交车 300-地铁 400-出租车 500-火车 600- 飞机 700-私家车 800-其它',
    BUS(100, "公交车"),
    METRO(200, "地铁"),
    TAXI(300, "出租车"),
    train(400, "火车"),
    AIRCRAFT(500, "飞机"),
    PRIVATE_CAR(600, "私家车"),
    NEARBY(700, "街/道/路"),
    SUPER_MARKETS(800, "超市"),
    MEGAMALLS (1000, "商场"),
    SQUARE(1100, "广场"),
    PARKS (1200, "公园"),
    LIFE_AREA(1300, "生活区"),
    WORK_AREA (1300, "工作区"),
    STUDY_AREA(1300, "学习区"),
    OTHER(100, "未知"),
    ;

    public int key;
    public String value;

    PostPlaceTypeEnum(int key, String value) {
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
            for (PostPlaceTypeEnum lostPostPlaceTypeEnum : PostPlaceTypeEnum.values()) {
                if (lostPostPlaceTypeEnum.getKey() == key.intValue()) {
                    return lostPostPlaceTypeEnum.getValue();
                }
            }
        }
        return null;
    }

}
