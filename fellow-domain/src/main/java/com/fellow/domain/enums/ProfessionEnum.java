package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/10/26.
 */
public enum ProfessionEnum {
    STUDENT(100, "在校学生"),
    COMPUTER_INTERNET_COMMUNICATION(200, "计算机/互联网/通信"),
    CIVIL_SERVANT_INSTITUTION(300, "公务员/事业单位"),
    DOCTOR(400, "医生"),
    NURSE(500, "护士"),
    AIRHOSTESS(600, "空乘人员"),
    PRODUCTION_PROCESS_MANUFACTURING(700, "生产/工艺/制造"),
    BUSINESS_SERVICE_INDIVIDUAL(800, "商业/服务业/个体经营"),
    FINANCE_BANKING_INVESTMENT_INSURANCE(900, "金融/银行/投资/保险"),
    CULTURE_ADVERTISING_MEDIA(1000, "文化/广告/传媒"),
    ENTERTAINMENT_ART_PERFORMANCE(1100, "娱乐/艺术/表演"),
    LAWYER_LEGAL(1200, "律师/法务"),
    EDUCATION_TRAINING_MANAGEMENT_CONSULTING(1300, "教育/培训/管理咨询"),
    CONSTRUCTION_REAL_ESTATE_PROPERTY(1400, "建筑/房地产/物业"),
    CONSUMER_RETAIL_TRADE_TRANSPORTATION_LOGISTICS(1500, "消费零售/贸易/交通物流"),
    HOTEL_TRAVEL(1600, "酒店旅游"),
    MODERN_AGRICULTURE(1700, "现代农业"),
    ORTHER(1800, "其它"),
    ;
    public int key;
    public String value;

    ProfessionEnum(int key, String value) {
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
            for (ProfessionEnum lostPostEnumTemp : ProfessionEnum.values()) {
                if (lostPostEnumTemp.getKey() == key.intValue()) {
                    return lostPostEnumTemp.getValue();
                }
            }
        }
        return ORTHER.getValue();
    }
}
