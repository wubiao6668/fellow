package com.fellow.domain.model;

import com.fellow.domain.model.post.PostReplyDomain;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;

public class LostPostReply extends PostReplyDomain implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //date formats
    public static final String FORMAT_REPLY_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_TS = DATE_TIME_FORMAT;



    public LostPostReply() {
    }

    public LostPostReply(java.lang.Long id) {
        this.id = id;
    }



    /**
     * 获得replyTime
     */
    public String getReplyTimeString() {
        if (null != getReplyTime()) {
            return DateFormatUtils.format(getReplyTime(), FORMAT_REPLY_TIME);
        }
        return null;
    }

    /**
     * 设置replyTime
     */
    public void setReplyTimeString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setReplyTime(DateUtils.parseDate(value, new String[]{FORMAT_REPLY_TIME}));
        }
    }



    /**
     * 获得创建时间
     */
    public String getCreateTimeString() {
        if (null != getCreateTime()) {
            return DateFormatUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
        }
        return null;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTimeString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setCreateTime(DateUtils.parseDate(value, new String[]{FORMAT_CREATE_TIME}));
        }
    }



    /**
     * 获得修改时间
     */
    public String getUpdateTimeString() {
        if (null != getUpdateTime()) {
            return DateFormatUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
        }
        return null;
    }

    /**
     * 设置修改时间
     */
    public void setUpdateTimeString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setUpdateTime(DateUtils.parseDate(value, new String[]{FORMAT_UPDATE_TIME}));
        }
    }


}

