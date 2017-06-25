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

public class Message extends BaseDomain implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //date formats
    public static final String FORMAT_SEND_TIME = DATE_TIME_FORMAT;
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
     * 长账号       db_column: long_account
     */
    @NotBlank
    @Length(max = 50)
    private String longAccount;
    /**
     * 短账号       db_column: short_account
     */
    @NotBlank
    @Length(max = 50)
    private String shortAccount;
    /**
     * 发送时间       db_column: send_time
     */
    @NotNull
    private Date sendTime;
    /**
     * 100-系统消息 200-用户消息       db_column: message_type
     */
    @NotNull
    @Max(32767)
    private Integer messageType;
    /**
     * 发送消息       db_column: message
     */
    @NotBlank
    @Length(max = 500)
    private String message;
    /**
     * 100-文字 200-图片       db_column: content_type
     */
    @NotNull
    @Max(99999999999L)
    private Integer contentType;
    /**
     * 长账号是否删除消息 100-未删除 200-已删除       db_column: long_delete
     */
    @NotNull
    @Max(32767)
    private Integer longDelete;
    /**
     * 短账号是否删除消息 100-未删除 200-已删除       db_column: short_delete
     */
    @NotNull
    @Max(32767)
    private Integer shortDelete;
    /**
     * 长账号未读回复       db_column: long_unread_num
     */
    @NotNull
    @Max(99999999999L)
    private Integer longUnreadNum;
    /**
     * 短账号未读数       db_column: short_unread_num
     */
    @NotNull
    @Max(99999999999L)
    private Integer shortUnreadNum;
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

    public Message() {
    }

    public Message(java.lang.Long id) {
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
     * 设置长账号
     */
    public void setLongAccount(String value) {
        this.longAccount = value;
    }

    /**
     * 获得长账号
     */
    public String getLongAccount() {
        return this.longAccount;
    }

    /**
     * 设置短账号
     */
    public void setShortAccount(String value) {
        this.shortAccount = value;
    }

    /**
     * 获得短账号
     */
    public String getShortAccount() {
        return this.shortAccount;
    }

    /**
     * 获得发送时间
     */
    public String getSendTimeString() {
        if (null != getSendTime()) {
            return DateFormatUtils.format(getSendTime(), FORMAT_SEND_TIME);
        }
        return null;
    }

    /**
     * 设置发送时间
     */
    public void setSendTimeString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setSendTime(DateUtils.parseDate(value, new String[]{FORMAT_SEND_TIME}));
        }
    }

    /**
     * 设置发送时间
     */
    public void setSendTime(Date value) {
        this.sendTime = value;
    }

    /**
     * 获得发送时间
     */
    public Date getSendTime() {
        return this.sendTime;
    }

    /**
     * 设置100-系统消息 200-用户消息
     */
    public void setMessageType(Integer value) {
        this.messageType = value;
    }

    /**
     * 获得100-系统消息 200-用户消息
     */
    public Integer getMessageType() {
        return this.messageType;
    }

    /**
     * 设置发送消息
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * 获得发送消息
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * 设置100-文字 200-图片
     */
    public void setContentType(Integer value) {
        this.contentType = value;
    }

    /**
     * 获得100-文字 200-图片
     */
    public Integer getContentType() {
        return this.contentType;
    }

    /**
     * 设置长账号是否删除消息 100-未删除 200-已删除
     */
    public void setLongDelete(Integer value) {
        this.longDelete = value;
    }

    /**
     * 获得长账号是否删除消息 100-未删除 200-已删除
     */
    public Integer getLongDelete() {
        return this.longDelete;
    }

    /**
     * 设置短账号是否删除消息 100-未删除 200-已删除
     */
    public void setShortDelete(Integer value) {
        this.shortDelete = value;
    }

    /**
     * 获得短账号是否删除消息 100-未删除 200-已删除
     */
    public Integer getShortDelete() {
        return this.shortDelete;
    }

    /**
     * 设置长账号未读回复
     */
    public void setLongUnreadNum(Integer value) {
        this.longUnreadNum = value;
    }

    /**
     * 获得长账号未读回复
     */
    public Integer getLongUnreadNum() {
        return this.longUnreadNum;
    }

    /**
     * 设置短账号未读数
     */
    public void setShortUnreadNum(Integer value) {
        this.shortUnreadNum = value;
    }

    /**
     * 获得短账号未读数
     */
    public Integer getShortUnreadNum() {
        return this.shortUnreadNum;
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

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("LongAccount", getLongAccount())
                .append("ShortAccount", getShortAccount())
                .append("SendTime", getSendTime())
                .append("MessageType", getMessageType())
                .append("Message", getMessage())
                .append("ContentType", getContentType())
                .append("LongDelete", getLongDelete())
                .append("ShortDelete", getShortDelete())
                .append("LongUnreadNum", getLongUnreadNum())
                .append("ShortUnreadNum", getShortUnreadNum())
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
        if (obj instanceof Message == false) return false;
        if (this == obj) return true;
        Message other = (Message) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }

    private User selfUser = new User();
    private User otherUser = new User();
    private MessageImg messageImg;
    private MessageDetail messageDetail;
    private long unreadNum;


    public MessageImg getMessageImg() {
        return messageImg;
    }

    public void setMessageImg(MessageImg messageImg) {
        this.messageImg = messageImg;
    }

    public MessageDetail getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(MessageDetail messageDetail) {
        this.messageDetail = messageDetail;
    }

    public User getSelfUser() {
        return selfUser;
    }

    public void setSelfUser(User selfUser) {
        this.selfUser = selfUser;
    }

    public User getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(User otherUser) {
        this.otherUser = otherUser;
    }

    public long getUnreadNum() {
        return unreadNum;
    }

    public void setUnreadNum(long unreadNum) {
        this.unreadNum = unreadNum;
    }
}

