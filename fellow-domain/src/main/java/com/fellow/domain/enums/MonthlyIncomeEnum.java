package com.fellow.domain.enums;

/**
 * Created by wubiao on 2016/10/26.
 */
public enum MonthlyIncomeEnum {
    BELOW_TWO_THOUSAND(100, "2000元以下"),
    BETWEEN_TWO_FOUR_THOUSAND(200, "2000-4000元"),
    BETWEEN_FOUR_SIX_THOUSAND(300, "4000-6000元"),
    BETWEEN_SIX_TEN_THOUSAND(400, "6000-10000元"),
    BETWEEN_TEN_FIFTEEN_THOUSAND(500, "10000-15000元"),
    BETWEEN_FIFTEEN_TWENTY_THOUSAND(600, "15000-20000元"),
    BETWEEN_FIFTEEN_FIFTY_THOUSAND(700, "20000-50000元"),
    ABOVE_FIFTY_THOUSAND(700, "50000元以上"),;

    public int key;
    public String value;

    MonthlyIncomeEnum(int key, String value) {
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
            for (MonthlyIncomeEnum enumTemp : MonthlyIncomeEnum.values()) {
                if (enumTemp.getKey() == key.intValue()) {
                    return enumTemp.getValue();
                }
            }
        }
        return null;
    }

}
