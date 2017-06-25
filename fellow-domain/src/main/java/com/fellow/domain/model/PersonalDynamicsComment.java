/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.domain.model;

import com.fellow.domain.base.BaseDomain;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;

public class PersonalDynamicsComment extends BaseDomain implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //date formats
    public static final String FORMAT_REPLY_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_READ_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_TS = DATE_TIME_FORMAT;

    //columns START
    /**
     * id       db_column: id
     */
    @Max(9223372036854775807L)
    private Long id;
    /**
     * personal 表主键       db_column: personal_id
     */
    @NotNull
    @Max(9223372036854775807L)
    private Long personalId;
    /**
     * 发送人账号       db_column: from_account
     */
    @NotBlank
    @Length(max = 50)
    private String fromAccount;
    /**
     * toAccount       db_column: to_account
     */
    @NotBlank
    @Length(max = 50)
    private String toAccount;
    /**
     * 回复内容       db_column: content
     */
    @NotBlank
    @Length(max = 600)
    private String content;
    /**
     * 回复时间       db_column: reply_time
     */
    @NotNull
    private Date replyTime;
    /**
     * 回复人删除回复 100-未删除  200-已经删除       db_column: from_delete
     */
    @NotNull
    @Max(32767)
    private Integer fromDelete;
    /**
     * 接收人是否删除回复 100-未删除 200-删除       db_column: to_delete
     */
    @NotNull
    @Max(32767)
    private Integer toDelete;
    /**
     * 100-未读   200-已读       db_column: is_read
     */
    @NotNull
    @Max(32767)
    private Integer isRead;
    /**
     * 读取时间       db_column: read_time
     */
    @NotNull
    private Date readTime;
    /**
     * 顶数量       db_column: up_num
     */
    @NotNull
    @Max(99999999999L)
    private Integer upNum;
    /**
     * 踩数量       db_column: down_num
     */
    @NotNull
    @Max(99999999999L)
    private Integer downNum;
    /**
     * 爱心数量       db_column: love_num
     */
    @NotNull
    @Max(99999999999L)
    private Integer loveNum;
    private Integer commentType;
    private Long commentId;
    /**
     * 创建人账号       db_column: create_account
     */
    @NotBlank
    @Length(max = 50)
    private String createAccount;
    /**
     * 创建人名称       db_column: create_name
     */
    @NotBlank
    @Length(max = 50)
    private String createName;
    /**
     * 创建时间       db_column: create_time
     */
    @NotNull
    private Date createTime;
    /**
     * 修改人账号       db_column: update_account
     */
    @NotBlank
    @Length(max = 50)
    private String updateAccount;
    /**
     * 修改人名称       db_column: update_name
     */
    @NotBlank
    @Length(max = 50)
    private String updateName;
    /**
     * 修改时间       db_column: update_time
     */
    @NotNull
    private Date updateTime;
    /**
     * 版本号       db_column: sys_version
     */
    @NotNull
    @Max(32767)
    private Integer sysVersion;
    /**
     * 0未删除 1删除       db_column: is_delete
     */
    @NotNull
    @Max(32767)
    private Integer isDelete;
    /**
     * 时间戳       db_column: ts
     */
    @NotNull
    private Date ts;
    //columns END

    public PersonalDynamicsComment() {
    }

    public PersonalDynamicsComment(java.lang.Long id) {
        this.id = id;
    }

    /**
     * 设置id
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * 获得id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置personal 表主键
     */
    public void setPersonalId(Long value) {
        this.personalId = value;
    }

    /**
     * 获得personal 表主键
     */
    public Long getPersonalId() {
        return this.personalId;
    }

    /**
     * 设置发送人账号
     */
    public void setFromAccount(String value) {
        this.fromAccount = value;
    }

    /**
     * 获得发送人账号
     */
    public String getFromAccount() {
        return this.fromAccount;
    }

    /**
     * 设置toAccount
     */
    public void setToAccount(String value) {
        this.toAccount = value;
    }

    /**
     * 获得toAccount
     */
    public String getToAccount() {
        return this.toAccount;
    }

    /**
     * 设置回复内容
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * 获得回复内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 获得回复时间
     */
    public String getReplyTimeString() {
        if (null != getReplyTime()) {
            return DateFormatUtils.format(getReplyTime(), FORMAT_REPLY_TIME);
        }
        return null;
    }

    /**
     * 设置回复时间
     */
    public void setReplyTimeString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setReplyTime(DateUtils.parseDate(value, new String[]{FORMAT_REPLY_TIME}));
        }
    }

    /**
     * 设置回复时间
     */
    public void setReplyTime(Date value) {
        this.replyTime = value;
    }

    /**
     * 获得回复时间
     */
    public Date getReplyTime() {
        return this.replyTime;
    }

    /**
     * 设置回复人删除回复 100-未删除  200-已经删除
     */
    public void setFromDelete(Integer value) {
        this.fromDelete = value;
    }

    /**
     * 获得回复人删除回复 100-未删除  200-已经删除
     */
    public Integer getFromDelete() {
        return this.fromDelete;
    }

    /**
     * 设置接收人是否删除回复 100-未删除 200-删除
     */
    public void setToDelete(Integer value) {
        this.toDelete = value;
    }

    /**
     * 获得接收人是否删除回复 100-未删除 200-删除
     */
    public Integer getToDelete() {
        return this.toDelete;
    }

    /**
     * 设置100-未读   200-已读
     */
    public void setIsRead(Integer value) {
        this.isRead = value;
    }

    /**
     * 获得100-未读   200-已读
     */
    public Integer getIsRead() {
        return this.isRead;
    }

    /**
     * 获得读取时间
     */
    public String getReadTimeString() {
        if (null != getReadTime()) {
            return DateFormatUtils.format(getReadTime(), FORMAT_READ_TIME);
        }
        return null;
    }

    /**
     * 设置读取时间
     */
    public void setReadTimeString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setReadTime(DateUtils.parseDate(value, new String[]{FORMAT_READ_TIME}));
        }
    }

    /**
     * 设置读取时间
     */
    public void setReadTime(Date value) {
        this.readTime = value;
    }

    /**
     * 获得读取时间
     */
    public Date getReadTime() {
        return this.readTime;
    }

    /**
     * 设置顶数量
     */
    public void setUpNum(Integer value) {
        this.upNum = value;
    }

    /**
     * 获得顶数量
     */
    public Integer getUpNum() {
        return this.upNum;
    }

    /**
     * 设置踩数量
     */
    public void setDownNum(Integer value) {
        this.downNum = value;
    }

    /**
     * 获得踩数量
     */
    public Integer getDownNum() {
        return this.downNum;
    }

    /**
     * 设置爱心数量
     */
    public void setLoveNum(Integer value) {
        this.loveNum = value;
    }

    /**
     * 获得爱心数量
     */
    public Integer getLoveNum() {
        return this.loveNum;
    }

    /**
     * 设置创建人账号
     */
    public void setCreateAccount(String value) {
        this.createAccount = value;
    }

    /**
     * 获得创建人账号
     */
    public String getCreateAccount() {
        return this.createAccount;
    }

    /**
     * 设置创建人名称
     */
    public void setCreateName(String value) {
        this.createName = value;
    }

    /**
     * 获得创建人名称
     */
    public String getCreateName() {
        return this.createName;
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
     * 设置创建时间
     */
    public void setCreateTime(Date value) {
        this.createTime = value;
    }

    /**
     * 获得创建时间
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置修改人账号
     */
    public void setUpdateAccount(String value) {
        this.updateAccount = value;
    }

    /**
     * 获得修改人账号
     */
    public String getUpdateAccount() {
        return this.updateAccount;
    }

    /**
     * 设置修改人名称
     */
    public void setUpdateName(String value) {
        this.updateName = value;
    }

    /**
     * 获得修改人名称
     */
    public String getUpdateName() {
        return this.updateName;
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

    /**
     * 设置修改时间
     */
    public void setUpdateTime(Date value) {
        this.updateTime = value;
    }

    /**
     * 获得修改时间
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置版本号
     */
    public void setSysVersion(Integer value) {
        this.sysVersion = value;
    }

    /**
     * 获得版本号
     */
    public Integer getSysVersion() {
        return this.sysVersion;
    }

    /**
     * 设置0未删除 1删除
     */
    public void setIsDelete(Integer value) {
        this.isDelete = value;
    }

    /**
     * 获得0未删除 1删除
     */
    public Integer getIsDelete() {
        return this.isDelete;
    }

    /**
     * 获得时间戳
     */
    public String getTsString() {
        if (null != getTs()) {
            return DateFormatUtils.format(getTs(), FORMAT_TS);
        }
        return null;
    }

    /**
     * 设置时间戳
     */
    public void setTsString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setTs(DateUtils.parseDate(value, new String[]{FORMAT_TS}));
        }
    }

    /**
     * 设置时间戳
     */
    public void setTs(Date value) {
        this.ts = value;
    }

    /**
     * 获得时间戳
     */
    public Date getTs() {
        return this.ts;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("PersonalId", getPersonalId())
                .append("FromAccount", getFromAccount())
                .append("ToAccount", getToAccount())
                .append("Content", getContent())
                .append("ReplyTime", getReplyTime())
                .append("FromDelete", getFromDelete())
                .append("ToDelete", getToDelete())
                .append("IsRead", getIsRead())
                .append("ReadTime", getReadTime())
                .append("UpNum", getUpNum())
                .append("DownNum", getDownNum())
                .append("LoveNum", getLoveNum())
                .append("CreateAccount", getCreateAccount())
                .append("CreateName", getCreateName())
                .append("CreateTime", getCreateTime())
                .append("UpdateAccount", getUpdateAccount())
                .append("UpdateName", getUpdateName())
                .append("UpdateTime", getUpdateTime())
                .append("SysVersion", getSysVersion())
                .append("IsDelete", getIsDelete())
                .append("Ts", getTs())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof PersonalDynamicsComment == false) return false;
        if (this == obj) return true;
        PersonalDynamicsComment other = (PersonalDynamicsComment) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }

    private User fromUser = new User();
    private User toUser = new User();
    private PersonalDynamicsComment personalDynamicsComment;
    private Set<Long> idSet;

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public PersonalDynamicsComment getPersonalDynamicsComment() {
        return personalDynamicsComment;
    }

    public void setPersonalDynamicsComment(PersonalDynamicsComment personalDynamicsComment) {
        this.personalDynamicsComment = personalDynamicsComment;
    }

    public Set<Long> getIdSet() {
        return idSet;
    }

    public void setIdSet(Set<Long> idSet) {
        this.idSet = idSet;
    }
}

