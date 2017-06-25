package com.fellow.common.db.able;

/**
 * Created by wubiao on 2016/9/24.
 */
public interface IncrementAble {
    int viewIncrement(Number key);

    int replyIncrement(Number key);

    int upIncrement(Number key);

    int downIncrement(Number key);

    int loveIncrement(Number key);
}
