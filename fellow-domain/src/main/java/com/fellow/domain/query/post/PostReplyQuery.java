/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.domain.query.post;

import com.fellow.domain.base.QueryDomain;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class PostReplyQuery extends QueryDomain  implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;

    //date formats
    public static final String FORMAT_REPLY_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_TS = DATE_TIME_FORMAT;

    /** post 表主键 */
    private Long postId;
    /** content */
    private String content;
    /** replyTime */
    private Date replyTimeBegin;
    private Date replyTimeEnd;
    /** 回复账号 */
    private String replyAccount;
    /** 顶数量 */
    private Integer upNum;
    /** 踩数量 */
    private Integer downNum;
    /** 回复数 */
    private Integer replyNum;
    /** 爱心数量 */
    private Integer loveNum;
    /** 图片数量 */
    private Integer imgNum;

    private Date createTimeBegin;
    private Date createTimeEnd;

    private Date updateTimeBegin;
    private Date updateTimeEnd;

    /** 时间戳 */
    private Date tsBegin;
    private Date tsEnd;

    /**
     * 获得id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置id
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * 获得post 表主键
     */
    public Long getPostId() {
        return this.postId;
    }

    /**
     * 设置post 表主键
     */
    public void setPostId(Long value) {
        this.postId = value;
    }

    /**
     * 获得content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置content
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * 获得replyTime开始
     */
    public Date getReplyTimeBegin() {
        return this.replyTimeBegin;
    }

    /**
     * 获得replyTime开始
     */
    public String getReplyTimeBeginString() {
        if (null != getReplyTimeBegin()){
            return DateFormatUtils.format(getReplyTimeBegin(),FORMAT_REPLY_TIME);
        }
        return null;
    }

    /**
     * 设置replyTime开始
     */
    public void setReplyTimeBegin(Date value) {
        this.replyTimeBegin = value;
    }

    /**
     * 设置replyTime开始
     */
    public void setReplyTimeBeginString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setReplyTimeBegin(DateUtils.parseDate(value, new String[]{FORMAT_REPLY_TIME}));
        }
    }

    /**
     * 获得replyTime结束
     */
    public Date getReplyTimeEnd() {
        return this.replyTimeEnd;
    }

    /**
     * 获得replyTime结束
     */
    public String getReplyTimeEndString() {
        if (null != getReplyTimeEnd()){
            return DateFormatUtils.format(getReplyTimeEnd(),FORMAT_REPLY_TIME);
        }
        return null;
    }

    /**
     * 设置replyTime结束
     */
    public void setReplyTimeEnd(Date value) {
        this.replyTimeEnd = value;
    }

    /**
     * 设置replyTime结束
     */
    public void setReplyTimeEndString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setReplyTimeEnd(DateUtils.parseDate(value, new String[]{FORMAT_REPLY_TIME}));
        }
    }

    /**
     * 获得回复账号
     */
    public String getReplyAccount() {
        return this.replyAccount;
    }

    /**
     * 设置回复账号
     */
    public void setReplyAccount(String value) {
        this.replyAccount = value;
    }

    /**
     * 获得顶数量
     */
    public Integer getUpNum() {
        return this.upNum;
    }

    /**
     * 设置顶数量
     */
    public void setUpNum(Integer value) {
        this.upNum = value;
    }

    /**
     * 获得踩数量
     */
    public Integer getDownNum() {
        return this.downNum;
    }

    /**
     * 设置踩数量
     */
    public void setDownNum(Integer value) {
        this.downNum = value;
    }

    /**
     * 获得回复数
     */
    public Integer getReplyNum() {
        return this.replyNum;
    }

    /**
     * 设置回复数
     */
    public void setReplyNum(Integer value) {
        this.replyNum = value;
    }

    /**
     * 获得爱心数量
     */
    public Integer getLoveNum() {
        return this.loveNum;
    }

    /**
     * 设置爱心数量
     */
    public void setLoveNum(Integer value) {
        this.loveNum = value;
    }

    /**
     * 获得图片数量
     */
    public Integer getImgNum() {
        return this.imgNum;
    }

    /**
     * 设置图片数量
     */
    public void setImgNum(Integer value) {
        this.imgNum = value;
    }



    /**
     * 获得创建时间开始
     */
    public Date getCreateTimeBegin() {
        return this.createTimeBegin;
    }

    /**
     * 获得创建时间开始
     */
    public String getCreateTimeBeginString() {
        if (null != getCreateTimeBegin()){
            return DateFormatUtils.format(getCreateTimeBegin(),FORMAT_CREATE_TIME);
        }
        return null;
    }

    /**
     * 设置创建时间开始
     */
    public void setCreateTimeBegin(Date value) {
        this.createTimeBegin = value;
    }

    /**
     * 设置创建时间开始
     */
    public void setCreateTimeBeginString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setCreateTimeBegin(DateUtils.parseDate(value, new String[]{FORMAT_CREATE_TIME}));
        }
    }

    /**
     * 获得创建时间结束
     */
    public Date getCreateTimeEnd() {
        return this.createTimeEnd;
    }

    /**
     * 获得创建时间结束
     */
    public String getCreateTimeEndString() {
        if (null != getCreateTimeEnd()){
            return DateFormatUtils.format(getCreateTimeEnd(),FORMAT_CREATE_TIME);
        }
        return null;
    }

    /**
     * 设置创建时间结束
     */
    public void setCreateTimeEnd(Date value) {
        this.createTimeEnd = value;
    }

    /**
     * 设置创建时间结束
     */
    public void setCreateTimeEndString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setCreateTimeEnd(DateUtils.parseDate(value, new String[]{FORMAT_CREATE_TIME}));
        }
    }


    /**
     * 获得修改时间开始
     */
    public Date getUpdateTimeBegin() {
        return this.updateTimeBegin;
    }

    /**
     * 获得修改时间开始
     */
    public String getUpdateTimeBeginString() {
        if (null != getUpdateTimeBegin()){
            return DateFormatUtils.format(getUpdateTimeBegin(),FORMAT_UPDATE_TIME);
        }
        return null;
    }

    /**
     * 设置修改时间开始
     */
    public void setUpdateTimeBegin(Date value) {
        this.updateTimeBegin = value;
    }

    /**
     * 设置修改时间开始
     */
    public void setUpdateTimeBeginString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setUpdateTimeBegin(DateUtils.parseDate(value, new String[]{FORMAT_UPDATE_TIME}));
        }
    }

    /**
     * 获得修改时间结束
     */
    public Date getUpdateTimeEnd() {
        return this.updateTimeEnd;
    }

    /**
     * 获得修改时间结束
     */
    public String getUpdateTimeEndString() {
        if (null != getUpdateTimeEnd()){
            return DateFormatUtils.format(getUpdateTimeEnd(),FORMAT_UPDATE_TIME);
        }
        return null;
    }

    /**
     * 设置修改时间结束
     */
    public void setUpdateTimeEnd(Date value) {
        this.updateTimeEnd = value;
    }

    /**
     * 设置修改时间结束
     */
    public void setUpdateTimeEndString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setUpdateTimeEnd(DateUtils.parseDate(value, new String[]{FORMAT_UPDATE_TIME}));
        }
    }


    /**
     * 获得时间戳开始
     */
    public Date getTsBegin() {
        return this.tsBegin;
    }

    /**
     * 获得时间戳开始
     */
    public String getTsBeginString() {
        if (null != getTsBegin()){
            return DateFormatUtils.format(getTsBegin(),FORMAT_TS);
        }
        return null;
    }

    /**
     * 设置时间戳开始
     */
    public void setTsBegin(Date value) {
        this.tsBegin = value;
    }

    /**
     * 设置时间戳开始
     */
    public void setTsBeginString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setTsBegin(DateUtils.parseDate(value, new String[]{FORMAT_TS}));
        }
    }

    /**
     * 获得时间戳结束
     */
    public Date getTsEnd() {
        return this.tsEnd;
    }

    /**
     * 获得时间戳结束
     */
    public String getTsEndString() {
        if (null != getTsEnd()){
            return DateFormatUtils.format(getTsEnd(),FORMAT_TS);
        }
        return null;
    }

    /**
     * 设置时间戳结束
     */
    public void setTsEnd(Date value) {
        this.tsEnd = value;
    }

    /**
     * 设置时间戳结束
     */
    public void setTsEndString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setTsEnd(DateUtils.parseDate(value, new String[]{FORMAT_TS}));
        }
    }


    public String toString() {
        return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
    }

}

