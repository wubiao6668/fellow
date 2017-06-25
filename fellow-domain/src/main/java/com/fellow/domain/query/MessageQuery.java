package com.fellow.domain.query;

import com.fellow.domain.base.QueryDomain;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class MessageQuery extends QueryDomain implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;

    //date formats
    public static final String FORMAT_SEND_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_TS = DATE_TIME_FORMAT;


    /**
     * id
     */
    private Long id;
    /**
     * 长账号
     */
    private String longAccount;
    /**
     * 短账号
     */
    private String shortAccount;
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 100-系统消息 200-用户消息
     */
    private Integer messageType;
    /**
     * 发送消息
     */
    private String message;
    /**
     * 100-文字 200-图片
     */
    private Integer contentType;
    /**
     * 长账号是否删除消息 100-未删除 200-已删除
     */
    private Integer longDelete;
    /**
     * 短账号是否删除消息 100-未删除 200-已删除
     */
    private Integer shortDelete;
    /**
     * 长账号未读回复
     */
    private Integer longUnreadNum;
    /**
     * 短账号未读数
     */
    private Integer shortUnreadNum;
    /**
     * 创建人账号
     */
    private String createAccount;
    /**
     * 创建人名称
     */
    private String createName;
    /**
     * 创建时间
     */
    private Date createTimeBegin;
    private Date createTimeEnd;
    /**
     * 修改人账号
     */
    private String updateAccount;
    /**
     * 修改人名称
     */
    private String updateName;
    /**
     * 修改时间
     */
    private Date updateTimeBegin;
    private Date updateTimeEnd;
    /**
     * 版本号
     */
    private Integer sysVersion;
    /**
     * 0未删除 1删除
     */
    private Integer isDelete;
    /**
     * 时间戳
     */
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
     * 获得长账号
     */
    public String getLongAccount() {
        return this.longAccount;
    }

    /**
     * 设置长账号
     */
    public void setLongAccount(String value) {
        this.longAccount = value;
    }

    /**
     * 获得短账号
     */
    public String getShortAccount() {
        return this.shortAccount;
    }

    /**
     * 设置短账号
     */
    public void setShortAccount(String value) {
        this.shortAccount = value;
    }

    /**
     * 获得发送时间
     */
    public Date getSendTime() {
        return this.sendTime;
    }

    /**
     * 设置发送时间
     */
    public void setSendTime(Date value) {
        this.sendTime = value;
    }

    /**
     * 获得100-系统消息 200-用户消息
     */
    public Integer getMessageType() {
        return this.messageType;
    }

    /**
     * 设置100-系统消息 200-用户消息
     */
    public void setMessageType(Integer value) {
        this.messageType = value;
    }

    /**
     * 获得发送消息
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * 设置发送消息
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * 获得100-文字 200-图片
     */
    public Integer getContentType() {
        return this.contentType;
    }

    /**
     * 设置100-文字 200-图片
     */
    public void setContentType(Integer value) {
        this.contentType = value;
    }

    /**
     * 获得长账号是否删除消息 100-未删除 200-已删除
     */
    public Integer getLongDelete() {
        return this.longDelete;
    }

    /**
     * 设置长账号是否删除消息 100-未删除 200-已删除
     */
    public void setLongDelete(Integer value) {
        this.longDelete = value;
    }

    /**
     * 获得短账号是否删除消息 100-未删除 200-已删除
     */
    public Integer getShortDelete() {
        return this.shortDelete;
    }

    /**
     * 设置短账号是否删除消息 100-未删除 200-已删除
     */
    public void setShortDelete(Integer value) {
        this.shortDelete = value;
    }

    /**
     * 获得长账号未读回复
     */
    public Integer getLongUnreadNum() {
        return this.longUnreadNum;
    }

    /**
     * 设置长账号未读回复
     */
    public void setLongUnreadNum(Integer value) {
        this.longUnreadNum = value;
    }

    /**
     * 获得短账号未读数
     */
    public Integer getShortUnreadNum() {
        return this.shortUnreadNum;
    }

    /**
     * 设置短账号未读数
     */
    public void setShortUnreadNum(Integer value) {
        this.shortUnreadNum = value;
    }

    /**
     * 获得创建人账号
     */
    public String getCreateAccount() {
        return this.createAccount;
    }

    /**
     * 设置创建人账号
     */
    public void setCreateAccount(String value) {
        this.createAccount = value;
    }

    /**
     * 获得创建人名称
     */
    public String getCreateName() {
        return this.createName;
    }

    /**
     * 设置创建人名称
     */
    public void setCreateName(String value) {
        this.createName = value;
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
        if (null != getCreateTimeBegin()) {
            return DateFormatUtils.format(getCreateTimeBegin(), FORMAT_CREATE_TIME);
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
    public void setCreateTimeBeginString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
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
        if (null != getCreateTimeEnd()) {
            return DateFormatUtils.format(getCreateTimeEnd(), FORMAT_CREATE_TIME);
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
    public void setCreateTimeEndString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setCreateTimeEnd(DateUtils.parseDate(value, new String[]{FORMAT_CREATE_TIME}));
        }
    }

    /**
     * 获得修改人账号
     */
    public String getUpdateAccount() {
        return this.updateAccount;
    }

    /**
     * 设置修改人账号
     */
    public void setUpdateAccount(String value) {
        this.updateAccount = value;
    }

    /**
     * 获得修改人名称
     */
    public String getUpdateName() {
        return this.updateName;
    }

    /**
     * 设置修改人名称
     */
    public void setUpdateName(String value) {
        this.updateName = value;
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
        if (null != getUpdateTimeBegin()) {
            return DateFormatUtils.format(getUpdateTimeBegin(), FORMAT_UPDATE_TIME);
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
    public void setUpdateTimeBeginString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
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
        if (null != getUpdateTimeEnd()) {
            return DateFormatUtils.format(getUpdateTimeEnd(), FORMAT_UPDATE_TIME);
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
    public void setUpdateTimeEndString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setUpdateTimeEnd(DateUtils.parseDate(value, new String[]{FORMAT_UPDATE_TIME}));
        }
    }

    /**
     * 获得版本号
     */
    public Integer getSysVersion() {
        return this.sysVersion;
    }

    /**
     * 设置版本号
     */
    public void setSysVersion(Integer value) {
        this.sysVersion = value;
    }

    /**
     * 获得0未删除 1删除
     */
    public Integer getIsDelete() {
        return this.isDelete;
    }

    /**
     * 设置0未删除 1删除
     */
    public void setIsDelete(Integer value) {
        this.isDelete = value;
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
        if (null != getTsBegin()) {
            return DateFormatUtils.format(getTsBegin(), FORMAT_TS);
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
    public void setTsBeginString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
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
        if (null != getTsEnd()) {
            return DateFormatUtils.format(getTsEnd(), FORMAT_TS);
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
    public void setTsEndString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setTsEnd(DateUtils.parseDate(value, new String[]{FORMAT_TS}));
        }
    }


    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}

