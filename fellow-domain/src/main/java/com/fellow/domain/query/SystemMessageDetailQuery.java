/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

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

public class SystemMessageDetailQuery extends QueryDomain implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;

    //date formats
    public static final String FORMAT_SEND_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_READ_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_TS = DATE_TIME_FORMAT;


    /** id */
    private Long id;
    /** system_message 表主键 */
    private Long messageId;
    /** 发送账号 */
    private String sendAccount;
    /** 接收账号 */
    private String receiveAccount;
    /** 发送时间 */
    private Date sendTime;
    /** 接收时间 */
    private Date readTimeBegin;
    private Date readTimeEnd;
    /** 100-系统消息 200-用户消息 */
    private Integer messageType;
    /** 发送消息 */
    private String message;
    /** 100文字 200-图片 */
    private Integer contentType;
    /** 发送者是否删除消息 100-未删除 200-已删除 */
    private Integer sendDelete;
    /** 接收者是否删除消息 100-未删除 200-已删除 */
    private Integer receiveDelete;
    /** 创建人账号 */
    private String createAccount;
    /** 创建人名称 */
    private String createName;
    /** 创建时间 */
    private Date createTimeBegin;
    private Date createTimeEnd;
    /** 修改人账号 */
    private String updateAccount;
    /** 修改人名称 */
    private String updateName;
    /** 修改时间 */
    private Date updateTimeBegin;
    private Date updateTimeEnd;
    /** 版本号 */
    private Integer sysVersion;
    /** 0未删除 1删除 */
    private Integer isDelete;
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
     * 获得system_message 表主键
     */
    public Long getMessageId() {
        return this.messageId;
    }
    
    /**
     * 设置system_message 表主键
     */
    public void setMessageId(Long value) {
        this.messageId = value;
    }
    
    /**
     * 获得发送账号
     */
    public String getSendAccount() {
        return this.sendAccount;
    }
    
    /**
     * 设置发送账号
     */
    public void setSendAccount(String value) {
        this.sendAccount = value;
    }
    
    /**
     * 获得接收账号
     */
    public String getReceiveAccount() {
        return this.receiveAccount;
    }
    
    /**
     * 设置接收账号
     */
    public void setReceiveAccount(String value) {
        this.receiveAccount = value;
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
     * 获得接收时间开始
     */
    public Date getReadTimeBegin() {
        return this.readTimeBegin;
    }

    /**
     * 获得接收时间开始
     */
    public String getReadTimeBeginString() {
        if (null != getReadTimeBegin()){
            return DateFormatUtils.format(getReadTimeBegin(),FORMAT_READ_TIME);
        }
        return null;
    }
    
    /**
     * 设置接收时间开始
     */
    public void setReadTimeBegin(Date value) {
        this.readTimeBegin = value;
    }    

    /**
     * 设置接收时间开始
     */
    public void setReadTimeBeginString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setReadTimeBegin(DateUtils.parseDate(value, new String[]{FORMAT_READ_TIME}));
        }
    }
    
    /**
     * 获得接收时间结束
     */
    public Date getReadTimeEnd() {
        return this.readTimeEnd;
    }

    /**
     * 获得接收时间结束
     */
    public String getReadTimeEndString() {
        if (null != getReadTimeEnd()){
            return DateFormatUtils.format(getReadTimeEnd(),FORMAT_READ_TIME);
        }
        return null;
    }
    
    /**
     * 设置接收时间结束
     */
    public void setReadTimeEnd(Date value) {
        this.readTimeEnd = value;
    }

    /**
     * 设置接收时间结束
     */
    public void setReadTimeEndString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setReadTimeEnd(DateUtils.parseDate(value, new String[]{FORMAT_READ_TIME}));
        }
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
     * 获得100文字 200-图片
     */
    public Integer getContentType() {
        return this.contentType;
    }
    
    /**
     * 设置100文字 200-图片
     */
    public void setContentType(Integer value) {
        this.contentType = value;
    }
    
    /**
     * 获得发送者是否删除消息 100-未删除 200-已删除
     */
    public Integer getSendDelete() {
        return this.sendDelete;
    }
    
    /**
     * 设置发送者是否删除消息 100-未删除 200-已删除
     */
    public void setSendDelete(Integer value) {
        this.sendDelete = value;
    }
    
    /**
     * 获得接收者是否删除消息 100-未删除 200-已删除
     */
    public Integer getReceiveDelete() {
        return this.receiveDelete;
    }
    
    /**
     * 设置接收者是否删除消息 100-未删除 200-已删除
     */
    public void setReceiveDelete(Integer value) {
        this.receiveDelete = value;
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

    private Long maxId;

    public Long getMaxId() {
        return maxId;
    }

    public void setMaxId(Long maxId) {
        this.maxId = maxId;
    }
}

