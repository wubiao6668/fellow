/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
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

public class PostCount extends BaseDomain implements java.io.Serializable{
    private static final long serialVersionUID = 5454155825314635342L;

    //date formats
    public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_TS = DATE_TIME_FORMAT;
    
    //columns START
    /**id       db_column: id*/
    @Max(9223372036854775807L)
    private Long id;
    /**帖子类型       db_column: post_type*/
    @NotNull @Max(32767)
    private Integer postType;
    /**顶数量       db_column: up_num*/
    @NotNull @Max(99999999999L)
    private Integer upNum;
    /**踩数量       db_column: down_num*/
    @NotNull @Max(99999999999L)
    private Integer downNum;
    /**浏览数       db_column: view_num*/
    @NotNull @Max(99999999999L)
    private Integer viewNum;
    /**回复数       db_column: reply_num*/
    @NotNull @Max(99999999999L)
    private Integer replyNum;
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

	public PostCount(){
	}

	public PostCount(Long id){
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
     * 设置帖子类型
     */
    public void setPostType(Integer value) {
        this.postType = value;
    }
    
    /**
     * 获得帖子类型
     */
    public Integer getPostType() {
        return this.postType;
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
     * 设置浏览数
     */
    public void setViewNum(Integer value) {
        this.viewNum = value;
    }
    
    /**
     * 获得浏览数
     */
    public Integer getViewNum() {
        return this.viewNum;
    }
    /**
     * 设置回复数
     */
    public void setReplyNum(Integer value) {
        this.replyNum = value;
    }
    
    /**
     * 获得回复数
     */
    public Integer getReplyNum() {
        return this.replyNum;
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
            .append("PostType",getPostType())
            .append("UpNum",getUpNum())
            .append("DownNum",getDownNum())
            .append("ViewNum",getViewNum())
            .append("ReplyNum",getReplyNum())
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
        if(obj instanceof PostCount == false) return false;
        if(this == obj) return true;
        PostCount other = (PostCount)obj;
        return new EqualsBuilder()
            .append(getId(),other.getId())
            .isEquals();
    }
}

