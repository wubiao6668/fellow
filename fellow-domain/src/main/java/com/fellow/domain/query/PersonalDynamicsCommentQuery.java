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

public class PersonalDynamicsCommentQuery extends QueryDomain implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;

    //date formats
    public static final String FORMAT_REPLY_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_READ_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_TS = DATE_TIME_FORMAT;


    /**
     * id
     */
    private Long id;
    /**
     * personal 表主键
     */
    private Long personalId;
    /**
     * 发送人账号
     */
    private String fromAccount;
    /**
     * toAccount
     */
    private String toAccount;
    /**
     * 回复内容
     */
    private String content;
    /**
     * 回复时间
     */
    private Date replyTimeBegin;
    private Date replyTimeEnd;
    /**
     * 回复人删除回复 100-未删除  200-已经删除
     */
    private Integer fromDelete;
    /**
     * 接收人是否删除回复 100-未删除 200-删除
     */
    private Integer toDelete;
    /**
     * 100-未读   200-已读
     */
    private Integer isRead;
    /**
     * 读取时间
     */
    private Date readTimeBegin;
    private Date readTimeEnd;
    /**
     * 顶数量
     */
    private Integer upNum;
    /**
     * 踩数量
     */
    private Integer downNum;
    /**
     * 爱心数量
     */
    private Integer loveNum;
    private Integer commentType;
    private Long commentId;
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
     * 获得personal 表主键
     */
    public Long getPersonalId() {
        return this.personalId;
    }

    /**
     * 设置personal 表主键
     */
    public void setPersonalId(Long value) {
        this.personalId = value;
    }

    /**
     * 获得发送人账号
     */
    public String getFromAccount() {
        return this.fromAccount;
    }

    /**
     * 设置发送人账号
     */
    public void setFromAccount(String value) {
        this.fromAccount = value;
    }

    /**
     * 获得toAccount
     */
    public String getToAccount() {
        return this.toAccount;
    }

    /**
     * 设置toAccount
     */
    public void setToAccount(String value) {
        this.toAccount = value;
    }

    /**
     * 获得回复内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置回复内容
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * 获得回复时间开始
     */
    public Date getReplyTimeBegin() {
        return this.replyTimeBegin;
    }

    /**
     * 获得回复时间开始
     */
    public String getReplyTimeBeginString() {
        if (null != getReplyTimeBegin()) {
            return DateFormatUtils.format(getReplyTimeBegin(), FORMAT_REPLY_TIME);
        }
        return null;
    }

    /**
     * 设置回复时间开始
     */
    public void setReplyTimeBegin(Date value) {
        this.replyTimeBegin = value;
    }

    /**
     * 设置回复时间开始
     */
    public void setReplyTimeBeginString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setReplyTimeBegin(DateUtils.parseDate(value, new String[]{FORMAT_REPLY_TIME}));
        }
    }

    /**
     * 获得回复时间结束
     */
    public Date getReplyTimeEnd() {
        return this.replyTimeEnd;
    }

    /**
     * 获得回复时间结束
     */
    public String getReplyTimeEndString() {
        if (null != getReplyTimeEnd()) {
            return DateFormatUtils.format(getReplyTimeEnd(), FORMAT_REPLY_TIME);
        }
        return null;
    }

    /**
     * 设置回复时间结束
     */
    public void setReplyTimeEnd(Date value) {
        this.replyTimeEnd = value;
    }

    /**
     * 设置回复时间结束
     */
    public void setReplyTimeEndString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setReplyTimeEnd(DateUtils.parseDate(value, new String[]{FORMAT_REPLY_TIME}));
        }
    }

    /**
     * 获得回复人删除回复 100-未删除  200-已经删除
     */
    public Integer getFromDelete() {
        return this.fromDelete;
    }

    /**
     * 设置回复人删除回复 100-未删除  200-已经删除
     */
    public void setFromDelete(Integer value) {
        this.fromDelete = value;
    }

    /**
     * 获得接收人是否删除回复 100-未删除 200-删除
     */
    public Integer getToDelete() {
        return this.toDelete;
    }

    /**
     * 设置接收人是否删除回复 100-未删除 200-删除
     */
    public void setToDelete(Integer value) {
        this.toDelete = value;
    }

    /**
     * 获得100-未读   200-已读
     */
    public Integer getIsRead() {
        return this.isRead;
    }

    /**
     * 设置100-未读   200-已读
     */
    public void setIsRead(Integer value) {
        this.isRead = value;
    }

    /**
     * 获得读取时间开始
     */
    public Date getReadTimeBegin() {
        return this.readTimeBegin;
    }

    /**
     * 获得读取时间开始
     */
    public String getReadTimeBeginString() {
        if (null != getReadTimeBegin()) {
            return DateFormatUtils.format(getReadTimeBegin(), FORMAT_READ_TIME);
        }
        return null;
    }

    /**
     * 设置读取时间开始
     */
    public void setReadTimeBegin(Date value) {
        this.readTimeBegin = value;
    }

    /**
     * 设置读取时间开始
     */
    public void setReadTimeBeginString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setReadTimeBegin(DateUtils.parseDate(value, new String[]{FORMAT_READ_TIME}));
        }
    }

    /**
     * 获得读取时间结束
     */
    public Date getReadTimeEnd() {
        return this.readTimeEnd;
    }

    /**
     * 获得读取时间结束
     */
    public String getReadTimeEndString() {
        if (null != getReadTimeEnd()) {
            return DateFormatUtils.format(getReadTimeEnd(), FORMAT_READ_TIME);
        }
        return null;
    }

    /**
     * 设置读取时间结束
     */
    public void setReadTimeEnd(Date value) {
        this.readTimeEnd = value;
    }

    /**
     * 设置读取时间结束
     */
    public void setReadTimeEndString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setReadTimeEnd(DateUtils.parseDate(value, new String[]{FORMAT_READ_TIME}));
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

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
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

}

