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

public class UserQuery extends QueryDomain implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;

    //date formats
    public static final String FORMAT_LAST_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_TS = DATE_TIME_FORMAT;


    /** id */
    private Long id;
    /** 账号 */
    private String account;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 电子邮件 */
    private String email;
    /** 随机盐 */
    private String randomSalt;
    /** 用户头像 */
    private String avatar;
    /** lastIp */
    private Integer lastIp;
    /**状态       db_column: status*/
    private Integer status;
    /** 上次登录时间 */
    private Date lastTimeBegin;
    private Date lastTimeEnd;
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
     * 获得账号
     */
    public String getAccount() {
        return this.account;
    }
    
    /**
     * 设置账号
     */
    public void setAccount(String value) {
        this.account = value;
    }
    
    /**
     * 获得用户名
     */
    public String getUsername() {
        return this.username;
    }
    
    /**
     * 设置用户名
     */
    public void setUsername(String value) {
        this.username = value;
    }
    
    /**
     * 获得密码
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * 设置密码
     */
    public void setPassword(String value) {
        this.password = value;
    }
    
    /**
     * 获得电子邮件
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * 设置电子邮件
     */
    public void setEmail(String value) {
        this.email = value;
    }
    
    /**
     * 获得随机盐
     */
    public String getRandomSalt() {
        return this.randomSalt;
    }
    
    /**
     * 设置随机盐
     */
    public void setRandomSalt(String value) {
        this.randomSalt = value;
    }
    
    /**
     * 获得用户头像
     */
    public String getAvatar() {
        return this.avatar;
    }
    
    /**
     * 设置用户头像
     */
    public void setAvatar(String value) {
        this.avatar = value;
    }
    
    /**
     * 获得lastIp
     */
    public Integer getLastIp() {
        return this.lastIp;
    }
    
    /**
     * 设置lastIp
     */
    public void setLastIp(Integer value) {
        this.lastIp = value;
    }
    
    /**
     * 获得上次登录时间开始
     */
    public Date getLastTimeBegin() {
        return this.lastTimeBegin;
    }

    /**
     * 获得上次登录时间开始
     */
    public String getLastTimeBeginString() {
        if (null != getLastTimeBegin()){
            return DateFormatUtils.format(getLastTimeBegin(),FORMAT_LAST_TIME);
        }
        return null;
    }
    
    /**
     * 设置上次登录时间开始
     */
    public void setLastTimeBegin(Date value) {
        this.lastTimeBegin = value;
    }    

    /**
     * 设置上次登录时间开始
     */
    public void setLastTimeBeginString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setLastTimeBegin(DateUtils.parseDate(value, new String[]{FORMAT_LAST_TIME}));
        }
    }
    
    /**
     * 获得上次登录时间结束
     */
    public Date getLastTimeEnd() {
        return this.lastTimeEnd;
    }

    /**
     * 获得上次登录时间结束
     */
    public String getLastTimeEndString() {
        if (null != getLastTimeEnd()){
            return DateFormatUtils.format(getLastTimeEnd(),FORMAT_LAST_TIME);
        }
        return null;
    }
    
    /**
     * 设置上次登录时间结束
     */
    public void setLastTimeEnd(Date value) {
        this.lastTimeEnd = value;
    }

    /**
     * 设置上次登录时间结束
     */
    public void setLastTimeEndString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setLastTimeEnd(DateUtils.parseDate(value, new String[]{FORMAT_LAST_TIME}));
        }
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
    }

    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}

