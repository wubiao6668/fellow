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

public class PersonalDynamicsQuery extends QueryDomain implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;

    //date formats
    public static final String FORMAT_PUBLISH_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_TS = DATE_TIME_FORMAT;


    /** id */
    private Long id;
    /** 账号 */
    private String account;
    /** content */
    private String content;
    /** 发布时间 */
    private Date publishTimeBegin;
    private Date publishTimeEnd;
    /** 动态类型 100-个人动态页  */
    private Integer publishType;
    /** 标题 */
    private String title;
    /** 图片数量 */
    private Integer imgNum;
    /** 回复数 */
    private Integer replyNum;
    /** 爱心数量 */
    private Integer loveNum;
    /** 点赞 */
    private Integer upNum;
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
    //动态属性
    private int dynamicsProperty;

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
     * 获得发布时间开始
     */
    public Date getPublishTimeBegin() {
        return this.publishTimeBegin;
    }

    /**
     * 获得发布时间开始
     */
    public String getPublishTimeBeginString() {
        if (null != getPublishTimeBegin()){
            return DateFormatUtils.format(getPublishTimeBegin(),FORMAT_PUBLISH_TIME);
        }
        return null;
    }

    /**
     * 设置发布时间开始
     */
    public void setPublishTimeBegin(Date value) {
        this.publishTimeBegin = value;
    }

    /**
     * 设置发布时间开始
     */
    public void setPublishTimeBeginString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setPublishTimeBegin(DateUtils.parseDate(value, new String[]{FORMAT_PUBLISH_TIME}));
        }
    }

    /**
     * 获得发布时间结束
     */
    public Date getPublishTimeEnd() {
        return this.publishTimeEnd;
    }

    /**
     * 获得发布时间结束
     */
    public String getPublishTimeEndString() {
        if (null != getPublishTimeEnd()){
            return DateFormatUtils.format(getPublishTimeEnd(),FORMAT_PUBLISH_TIME);
        }
        return null;
    }

    /**
     * 设置发布时间结束
     */
    public void setPublishTimeEnd(Date value) {
        this.publishTimeEnd = value;
    }

    /**
     * 设置发布时间结束
     */
    public void setPublishTimeEndString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setPublishTimeEnd(DateUtils.parseDate(value, new String[]{FORMAT_PUBLISH_TIME}));
        }
    }

    /**
     * 获得动态类型 100-个人动态页
     */
    public Integer getPublishType() {
        return this.publishType;
    }

    /**
     * 设置动态类型 100-个人动态页
     */
    public void setPublishType(Integer value) {
        this.publishType = value;
    }

    /**
     * 获得标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置标题
     */
    public void setTitle(String value) {
        this.title = value;
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
     * 获得点赞
     */
    public Integer getUpNum() {
        return this.upNum;
    }

    /**
     * 设置点赞
     */
    public void setUpNum(Integer value) {
        this.upNum = value;
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

    /**
     * id小于
     */
    private Long idLt;
    /**
     * id大于
     */
    private Long idGt;

    public Long getIdLt() {
        return idLt;
    }

    public void setIdLt(Long idLt) {
        this.idLt = idLt;
    }

    public Long getIdGt() {
        return idGt;
    }

    public void setIdGt(Long idGt) {
        this.idGt = idGt;
    }

    public int getDynamicsProperty() {
        return dynamicsProperty;
    }

    public void setDynamicsProperty(int dynamicsProperty) {
        this.dynamicsProperty = dynamicsProperty;
    }
}

