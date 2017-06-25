package com.fellow.domain.vo;

import java.io.Serializable;

/**
 * Created by wubiao on 2016/11/9.
 */
public class MessageCount implements Serializable {
    private static final long serialVersionUID = 5608261136476078497L;
    private int count;
    private int unReadNum;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUnReadNum() {
        return unReadNum;
    }

    public void setUnReadNum(int unReadNum) {
        this.unReadNum = unReadNum;
    }
}
