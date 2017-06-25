package com.fellow.domain.enums;

import org.apache.commons.lang.StringUtils;

/**
 * Created by wubiao on 2016/11/15.
 */
public enum ShortEnum {
    A(10, "a"),
    B(20, "b"),
    C(30, "c"),
    D(40, "d"),
    E(50, "e"),
    F(60, "f"),
    G(70, "g"),
    H(80, "h"),
    I(90, "i"),
    J(100, "j"),
    K(110, "k"),
    L(120, "l"),
    M(130, "m"),
    N(140, "n"),
    O(150, "o"),
    P(160, "p"),
    Q(170, "q"),
    R(180, "r"),
    S(190, "s"),
    T(200, "t"),
    U(210, "u"),
    V(220, "v"),
    W(230, "w"),
    X(240, "s"),
    Y(250, "y"),
    Z(260, "z"),
    OTHER(0, "za"),
    All(-1, "*"),;

    public int key;
    public String value;

    ShortEnum(int key, String value) {
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
            for (ShortEnum enumTemp : ShortEnum.values()) {
                if (enumTemp.getKey() == key.intValue()) {
                    return enumTemp.getValue();
                }
            }
        }
        return null;
    }

    public static String getKey(String key) {
        String shortKey = ShortEnum.OTHER.getValue();
        if (StringUtils.isNotBlank(key)) {
            for (ShortEnum shortEnum : ShortEnum.values()) {
               if (shortEnum.getValue().equals(key.toLowerCase())){
                   shortKey = shortEnum.getValue();
               }
            }
        }
        return shortKey;
    }

//    public final static void main(String[] arg){
//        ShortEnum.super.
//        System.out.println(ShortEnum.valueOf("A"));
//    }

}
