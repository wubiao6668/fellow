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
import java.util.List;

public class SystemMessage extends BaseDomain implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //date formats
    public static final String FORMAT_SEND_TIME = DATE_TIME_FORMAT;
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
     * detail 表当前的id       db_column: detail_cur_id
     */
    @NotNull
    @Max(9223372036854775807L)
    private Long detailCurId;
    /**
     * 发送账号       db_column: send_account
     */
    @NotBlank
    @Length(max = 50)
    private String sendAccount;
    /**
     * 接收账号       db_column: receive_account
     */
    @NotBlank
    @Length(max = 50)
    private String receiveAccount;
    /**
     * 发送时间       db_column: send_time
     */
    @NotNull
    private Date sendTime;
    /**
     * 接收时间       db_column: read_time
     */
    @NotNull
    private Date readTime;
    /**
     * 100-系统消息 200-好友请求信息       db_column: message_type
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
     * 发送者是否删除消息 100-未删除 200-已删除       db_column: send_delete
     */
    @NotNull
    @Max(32767)
    private Integer sendDelete;
    /**
     * 接收者是否删除消息 100-未删除 200-已删除       db_column: receive_delete
     */
    @NotNull
    @Max(32767)
    private Integer receiveDelete;
    /**
     * 回复数       db_column: detail_count
     */
    @NotNull
    @Max(99999999999L)
    private Integer detailCount;
    /**
     * 接收未读回复       db_column: receive_detail_unread_num
     */
    @NotNull
    @Max(99999999999L)
    private Integer receiveDetailUnreadNum;
    /**
     * 接收未读回复       db_column: send_detail_unread_num
     */
    @NotNull
    @Max(99999999999L)
    private Integer sendDetailUnreadNum;
    /**
     * 100-未处理 200-同意        db_column: status
     */
    @NotNull
    @Max(99999999999L)
    private Integer status;
    /**
     * 最后发送的账号       db_column: last_accout
     */
    @NotBlank
    @Length(max = 50)
    private String lastAccout;
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

    public SystemMessage() {
    }

    public SystemMessage(java.lang.Long id) {
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
     * 设置detail 表当前的id
     */
    public void setDetailCurId(Long value) {
        this.detailCurId = value;
    }

    /**
     * 获得detail 表当前的id
     */
    public Long getDetailCurId() {
        return this.detailCurId;
    }

    /**
     * 设置发送账号
     */
    public void setSendAccount(String value) {
        this.sendAccount = value;
    }

    /**
     * 获得发送账号
     */
    public String getSendAccount() {
        return this.sendAccount;
    }

    /**
     * 设置接收账号
     */
    public void setReceiveAccount(String value) {
        this.receiveAccount = value;
    }

    /**
     * 获得接收账号
     */
    public String getReceiveAccount() {
        return this.receiveAccount;
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
     * 获得接收时间
     */
    public String getReadTimeString() {
        if (null != getReadTime()) {
            return DateFormatUtils.format(getReadTime(), FORMAT_READ_TIME);
        }
        return null;
    }

    /**
     * 设置接收时间
     */
    public void setReadTimeString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setReadTime(DateUtils.parseDate(value, new String[]{FORMAT_READ_TIME}));
        }
    }

    /**
     * 设置接收时间
     */
    public void setReadTime(Date value) {
        this.readTime = value;
    }

    /**
     * 获得接收时间
     */
    public Date getReadTime() {
        return this.readTime;
    }

    /**
     * 设置100-系统消息 200-好友请求信息
     */
    public void setMessageType(Integer value) {
        this.messageType = value;
    }

    /**
     * 获得100-系统消息 200-好友请求信息
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
     * 设置发送者是否删除消息 100-未删除 200-已删除
     */
    public void setSendDelete(Integer value) {
        this.sendDelete = value;
    }

    /**
     * 获得发送者是否删除消息 100-未删除 200-已删除
     */
    public Integer getSendDelete() {
        return this.sendDelete;
    }

    /**
     * 设置接收者是否删除消息 100-未删除 200-已删除
     */
    public void setReceiveDelete(Integer value) {
        this.receiveDelete = value;
    }

    /**
     * 获得接收者是否删除消息 100-未删除 200-已删除
     */
    public Integer getReceiveDelete() {
        return this.receiveDelete;
    }

    /**
     * 设置回复数
     */
    public void setDetailCount(Integer value) {
        this.detailCount = value;
    }

    /**
     * 获得回复数
     */
    public Integer getDetailCount() {
        return this.detailCount;
    }

    /**
     * 设置接收未读回复
     */
    public void setReceiveDetailUnreadNum(Integer value) {
        this.receiveDetailUnreadNum = value;
    }

    /**
     * 获得接收未读回复
     */
    public Integer getReceiveDetailUnreadNum() {
        return this.receiveDetailUnreadNum;
    }

    /**
     * 设置接收未读回复
     */
    public void setSendDetailUnreadNum(Integer value) {
        this.sendDetailUnreadNum = value;
    }

    /**
     * 获得接收未读回复
     */
    public Integer getSendDetailUnreadNum() {
        return this.sendDetailUnreadNum;
    }

    /**
     * 设置100-未处理 200-同意
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    /**
     * 获得100-未处理 200-同意
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置最后发送的账号
     */
    public void setLastAccout(String value) {
        this.lastAccout = value;
    }

    /**
     * 获得最后发送的账号
     */
    public String getLastAccout() {
        return this.lastAccout;
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
                .append("DetailCurId", getDetailCurId())
                .append("SendAccount", getSendAccount())
                .append("ReceiveAccount", getReceiveAccount())
                .append("SendTime", getSendTime())
                .append("ReadTime", getReadTime())
                .append("MessageType", getMessageType())
                .append("Message", getMessage())
                .append("ContentType", getContentType())
                .append("SendDelete", getSendDelete())
                .append("ReceiveDelete", getReceiveDelete())
                .append("DetailCount", getDetailCount())
                .append("ReceiveDetailUnreadNum", getReceiveDetailUnreadNum())
                .append("SendDetailUnreadNum", getSendDetailUnreadNum())
                .append("Status", getStatus())
                .append("LastAccout", getLastAccout())
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
        if (obj instanceof SystemMessage == false) return false;
        if (this == obj) return true;
        SystemMessage other = (SystemMessage) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }

    private String titleTip;
    private User sendUser = new User();
    private User receiveUser = new User();
    private SystemMessageDetail systemMessageDetail;

    public String getTitleTip() {
        return titleTip;
    }

    public void setTitleTip(String titleTip) {
        this.titleTip = titleTip;
    }

    public User getSendUser() {
        return sendUser;
    }

    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }

    public User getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(User receiveUser) {
        this.receiveUser = receiveUser;
    }

    private List<Long> idList;

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public SystemMessageDetail getSystemMessageDetail() {
        return systemMessageDetail;
    }

    public void setSystemMessageDetail(SystemMessageDetail systemMessageDetail) {
        this.systemMessageDetail = systemMessageDetail;
    }
}

