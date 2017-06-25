package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/9/1.
 */
public enum LostPosGoodTypeEnum {
    //    100-证书证件 200-钱包 300-包包 400-钥匙 500-卡 600-电子产品 700-动物 800-装饰品 900-衣服 1000-交通工具
    CERTIFICATE(200, "证书证件"),
    WALLET(300, "钱包"),
    BAG(400, "包包"),
    KEY(500, "钥匙"),
    CARD(600, "卡"),
    ELECTRONIC(700, "电子产品"),
    ANIMAL(800, "动物"),
    ORNAMENT(900, "装饰品"),
    CLOTHES(1000, "衣服"),
    BICYCLE(1100, "自行车"),
    ELECTROMBILE(1200, "电动车"),
    MOTORBIKE(1300, "摩托车"),
    VEHICLE(1400, "其它交通工具"),
    OTHER(100, "其它物品"),
    ;

    public int key;
    public String value;

    LostPosGoodTypeEnum(int key, String value) {
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
            for (LostPosGoodTypeEnum lostPostEnumTemp : LostPosGoodTypeEnum.values()) {
                if (lostPostEnumTemp.getKey() == key.intValue()) {
                    return lostPostEnumTemp.getValue();
                }
            }
        }
        return null;
    }

}
