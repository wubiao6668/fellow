/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
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

public class ForgetPasswordQuery extends QueryDomain implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;

    //date formats
    public static final String FORMAT_SEND_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_TS = DATE_TIME_FORMAT;


    /** 主键 */
    private Long id;
    /** 邮件 */
    private String email;
    /** 令牌key */
    private String tokenKey;
    /** 令牌value */
    private String tokenValue;
    /** 发送时间 */
    private Date sendTime;
    /** 100-发送验证邮件  200-发送密码 */
    private Integer status;
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
     * 获得主键
     */
    public Long getId() {
        return this.id;
    }
    
    /**
     * 设置主键
     */
    public void setId(Long value) {
        this.id = value;
    }
    
    /**
     * 获得邮件
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * 设置邮件
     */
    public void setEmail(String value) {
        this.email = value;
    }
    
    /**
     * 获得令牌key
     */
    public String getTokenKey() {
        return this.tokenKey;
    }
    
    /**
     * 设置令牌key
     */
    public void setTokenKey(String value) {
        this.tokenKey = value;
    }
    
    /**
     * 获得令牌value
     */
    public String getTokenValue() {
        return this.tokenValue;
    }
    
    /**
     * 设置令牌value
     */
    public void setTokenValue(String value) {
        this.tokenValue = value;
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
     * 获得100-发送验证邮件  200-发送密码
     */
    public Integer getStatus() {
        return this.status;
    }
    
    /**
     * 设置100-发送验证邮件  200-发送密码
     */
    public void setStatus(Integer value) {
        this.status = value;
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
    
}

