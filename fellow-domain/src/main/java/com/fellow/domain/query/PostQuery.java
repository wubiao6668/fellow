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

public class PostQuery extends QueryDomain implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;

    //date formats
    public static final String FORMAT_POST_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_TS = DATE_TIME_FORMAT;


    /** id */
    private Long id;
    /** 对应表的id */
    private Long postId;
    /** 帖子类型 */
    private Integer postType;
    /** 标题 */
    private String title;
    /** 发帖人 */
    private String postAccount;
    /** 发帖时间 */
    private Date postTimeBegin;
    private Date postTimeEnd;
    /** 顶数量 */
    private Integer upNum;
    /** 踩数量 */
    private Integer downNum;
    /** 浏览数 */
    private Integer viewNum;
    /** 回复数 */
    private Integer replyNum;
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
     * 获得对应表的id
     */
    public Long getPostId() {
        return this.postId;
    }
    
    /**
     * 设置对应表的id
     */
    public void setPostId(Long value) {
        this.postId = value;
    }
    
    /**
     * 获得帖子类型
     */
    public Integer getPostType() {
        return this.postType;
    }
    
    /**
     * 设置帖子类型
     */
    public void setPostType(Integer value) {
        this.postType = value;
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
     * 获得发帖人
     */
    public String getPostAccount() {
        return this.postAccount;
    }
    
    /**
     * 设置发帖人
     */
    public void setPostAccount(String value) {
        this.postAccount = value;
    }
    
    /**
     * 获得发帖时间开始
     */
    public Date getPostTimeBegin() {
        return this.postTimeBegin;
    }

    /**
     * 获得发帖时间开始
     */
    public String getPostTimeBeginString() {
        if (null != getPostTimeBegin()){
            return DateFormatUtils.format(getPostTimeBegin(),FORMAT_POST_TIME);
        }
        return null;
    }
    
    /**
     * 设置发帖时间开始
     */
    public void setPostTimeBegin(Date value) {
        this.postTimeBegin = value;
    }    

    /**
     * 设置发帖时间开始
     */
    public void setPostTimeBeginString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setPostTimeBegin(DateUtils.parseDate(value, new String[]{FORMAT_POST_TIME}));
        }
    }
    
    /**
     * 获得发帖时间结束
     */
    public Date getPostTimeEnd() {
        return this.postTimeEnd;
    }

    /**
     * 获得发帖时间结束
     */
    public String getPostTimeEndString() {
        if (null != getPostTimeEnd()){
            return DateFormatUtils.format(getPostTimeEnd(),FORMAT_POST_TIME);
        }
        return null;
    }
    
    /**
     * 设置发帖时间结束
     */
    public void setPostTimeEnd(Date value) {
        this.postTimeEnd = value;
    }

    /**
     * 设置发帖时间结束
     */
    public void setPostTimeEndString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setPostTimeEnd(DateUtils.parseDate(value, new String[]{FORMAT_POST_TIME}));
        }
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
     * 获得浏览数
     */
    public Integer getViewNum() {
        return this.viewNum;
    }
    
    /**
     * 设置浏览数
     */
    public void setViewNum(Integer value) {
        this.viewNum = value;
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

