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
import org.hibernate.validator.constraints.Email;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Max;
import java.text.ParseException;
import java.util.Date;

public class AttitudeInfo extends BaseDomain implements java.io.Serializable{
    private static final long serialVersionUID = 5454155825314635342L;

    //date formats
    public static final String FORMAT_ATTITUDE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_TS = DATE_TIME_FORMAT;
    
    //columns START
    /**id       db_column: id*/
    @Max(9223372036854775807L)
    private Long id;
    /**点赞类型 100-帖子 200-帖子评论 300-评论回复 400-评论       db_column: type*/
    @NotNull @Max(99999999999L)
    private Integer type;
    /**业务ID为帖子 帖子评论 评论回复 动态评论       db_column: bus_id*/
    @NotNull @Max(9223372036854775807L)
    private Long busId;
    /**点赞人       db_column: from_account*/
    @NotBlank @Length(max=50)
    private String fromAccount;
    /**toAccount       db_column: to_account*/
    @NotBlank @Length(max=255)
    private String toAccount;
    /**态度状态 0-取消 100-点赞 200-不支持 300-爱心       db_column: status*/
    @NotNull @Max(99999999999L)
    private Integer status;
    /**点赞时间       db_column: attitude_time*/
    @NotNull 
    private Date attitudeTime;
    /**创建人账号       db_column: create_account*/
    @NotBlank @Length(max=50)
    private String createAccount;
    /**创建人名称       db_column: create_name*/
    @NotBlank @Length(max=50)
    private String createName;
    /**创建时间       db_column: create_time*/
    @NotNull 
    private Date createTime;
    /**修改人账号       db_column: update_account*/
    @NotBlank @Length(max=50)
    private String updateAccount;
    /**修改人名称       db_column: update_name*/
    @NotBlank @Length(max=50)
    private String updateName;
    /**修改时间       db_column: update_time*/
    @NotNull 
    private Date updateTime;
    /**版本号       db_column: sys_version*/
    @NotNull @Max(32767)
    private Integer sysVersion;
    /**0未删除 1删除       db_column: is_delete*/
    @NotNull @Max(32767)
    private Integer isDelete;
    /**时间戳       db_column: ts*/
    @NotNull 
    private Date ts;
    //columns END

	public AttitudeInfo(){
	}

	public AttitudeInfo(Long id){
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
     * 设置点赞类型 100-帖子 200-帖子评论 300-评论回复 400-评论
     */
    public void setType(Integer value) {
        this.type = value;
    }
    
    /**
     * 获得点赞类型 100-帖子 200-帖子评论 300-评论回复 400-评论
     */
    public Integer getType() {
        return this.type;
    }
    /**
     * 设置业务ID为帖子 帖子评论 评论回复 动态评论
     */
    public void setBusId(Long value) {
        this.busId = value;
    }
    
    /**
     * 获得业务ID为帖子 帖子评论 评论回复 动态评论
     */
    public Long getBusId() {
        return this.busId;
    }
    /**
     * 设置点赞人
     */
    public void setFromAccount(String value) {
        this.fromAccount = value;
    }
    
    /**
     * 获得点赞人
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
     * 设置态度状态 0-取消 100-点赞 200-不支持 300-爱心
     */
    public void setStatus(Integer value) {
        this.status = value;
    }
    
    /**
     * 获得态度状态 0-取消 100-点赞 200-不支持 300-爱心
     */
    public Integer getStatus() {
        return this.status;
    }
    /**
     * 获得点赞时间
     * */
    public String getAttitudeTimeString() {
        if (null != getAttitudeTime()){
            return DateFormatUtils.format(getAttitudeTime(),FORMAT_ATTITUDE_TIME);
        }
        return null;
    }

    /**
     * 设置点赞时间
     */
    public void setAttitudeTimeString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setAttitudeTime(DateUtils.parseDate(value, new String[]{FORMAT_ATTITUDE_TIME}));
        }
    }
    
    /**
     * 设置点赞时间
     */
    public void setAttitudeTime(Date value) {
        this.attitudeTime = value;
    }
    
    /**
     * 获得点赞时间
     */
    public Date getAttitudeTime() {
        return this.attitudeTime;
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
     * */
    public String getCreateTimeString() {
        if (null != getCreateTime()){
            return DateFormatUtils.format(getCreateTime(),FORMAT_CREATE_TIME);
        }
        return null;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTimeString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
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
     * */
    public String getUpdateTimeString() {
        if (null != getUpdateTime()){
            return DateFormatUtils.format(getUpdateTime(),FORMAT_UPDATE_TIME);
        }
        return null;
    }

    /**
     * 设置修改时间
     */
    public void setUpdateTimeString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
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
     * */
    public String getTsString() {
        if (null != getTs()){
            return DateFormatUtils.format(getTs(),FORMAT_TS);
        }
        return null;
    }

    /**
     * 设置时间戳
     */
    public void setTsString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("Id",getId())
            .append("Type",getType())
            .append("BusId",getBusId())
            .append("FromAccount",getFromAccount())
            .append("ToAccount",getToAccount())
            .append("Status",getStatus())
            .append("AttitudeTime",getAttitudeTime())
            .append("CreateAccount",getCreateAccount())
            .append("CreateName",getCreateName())
            .append("CreateTime",getCreateTime())
            .append("UpdateAccount",getUpdateAccount())
            .append("UpdateName",getUpdateName())
            .append("UpdateTime",getUpdateTime())
            .append("SysVersion",getSysVersion())
            .append("IsDelete",getIsDelete())
            .append("Ts",getTs())
            .toString();
    }
    
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }
    
    public boolean equals(Object obj) {
        if(obj instanceof AttitudeInfo == false) return false;
        if(this == obj) return true;
        AttitudeInfo other = (AttitudeInfo)obj;
        return new EqualsBuilder()
            .append(getId(),other.getId())
            .isEquals();
    }
}

