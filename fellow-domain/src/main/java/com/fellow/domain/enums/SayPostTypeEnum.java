package com.fellow.domain.enums;

/**
 * Created by wubiao on 2017/1/18.
 */
public enum SayPostTypeEnum {
    SUGGEST(200, "建议"),
    COMPLAINT(200, "投诉"),
    INSIDER(200, "内幕"),
    REMIND(300, "提醒"),
    THANKS(400, "感谢"),
    HELP(500, "求助"),
    AFFAIRS_APPLY(600, "求证"),
    PROOF(600, "事务办理"),
    ANGER(700, "愤怒"),
    SADNESS(800, "伤感"),
    FEAR(900, "恐惧"),
    HAPPY(1000, "开心"),
    LOVE(1100, "有爱"),
    EXPECT(1400, "愿望"),
    WARN(1500, "警告"),
    LIAR(1800, "骗子"),
    RECALL(1900, "回忆"),
    COMPUNCTION(2000, "内疚"),
    BENEFIT(2100, "实惠"),
    CONNECTS(2200, "邂逅"),
    MISSING_PERSONS(2300, "寻人"),
    LOST(2400, "寻物",LostPosGoodTypeEnum.values()),
    PICKUP(2500, "招领",LostPosGoodTypeEnum.values()),
    LARGESS(2500, "赠送"),
    INTERCHANGE(2500, "互换"),
    OTHER(100, "其它"),
    ;
    public int key;
    public String value;
    private Object seconde;

    SayPostTypeEnum(int key, String value, Object seconde) {
        this.key = key;
        this.value = value;
        this.seconde = seconde;
    }

    public Object getSeconde() {
        return seconde;
    }

    public void setSeconde(Object seconde) {
        this.seconde = seconde;
    }

    SayPostTypeEnum(int key, String value) {
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

    public static final void main(String[] arg){
        System.out.println(SayPostTypeEnum.LOST.seconde);
        System.out.println(SayPostTypeEnum.COMPUNCTION.seconde);
    }
}
