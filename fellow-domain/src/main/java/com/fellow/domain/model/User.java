/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.domain.model;

import com.fellow.domain.base.BaseDomain;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;

public class User extends BaseDomain implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //date formats
    public static final String FORMAT_LAST_TIME = DATE_TIME_FORMAT;
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
     * 账号       db_column: account
     */
    @NotBlank
    @Length(max = 50)
    private String account;
    /**
     * 用户名       db_column: username
     */
    @NotBlank
    @Length(max = 50)
    private String username;
    /**
     * 状态       db_column: status
     */
    @NotNull
    @Max(99999999999L)
    private Integer status;
    /**
     * 密码       db_column: password
     */
    @NotBlank
    @Length(max = 50)
    private String password;
    /**
     * 电子邮件       db_column: email
     */
    @NotBlank
    @Email
    @Length(max = 50)
    private String email;
    /**
     * 随机盐       db_column: random_salt
     */
    @NotBlank
    @Length(max = 12)
    private String randomSalt;
    /**
     * 用户头像       db_column: avatar
     */
    @NotBlank
    @Length(max = 200)
    private String avatar;
    /**
     * lastIp       db_column: last_ip
     */
    @NotNull
    @Max(99999999999L)
    private Integer lastIp;
    /**
     * 上次登录时间       db_column: last_time
     */
    @NotNull
    private Date lastTime;
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

    public User() {
    }

    public User(Long id) {
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
     * 设置账号
     */
    public void setAccount(String value) {
        this.account = value;
    }

    /**
     * 获得账号
     */
    public String getAccount() {
        return this.account;
    }

    /**
     * 设置用户名
     */
    public void setUsername(String value) {
        this.username = value;
    }

//    /**
//     * 获得用户名
//     */
//    public String getUsername() {
//        return this.username;
//    }

    /**
     * 设置密码
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * 获得密码
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 设置电子邮件
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * 获得电子邮件
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 设置随机盐
     */
    public void setRandomSalt(String value) {
        this.randomSalt = value;
    }

    /**
     * 获得随机盐
     */
    public String getRandomSalt() {
        return this.randomSalt;
    }

    /**
     * 设置用户头像
     */
    public void setAvatar(String value) {
        this.avatar = value;
    }

//    /**
//     * 获得用户头像
//     */
//    public String getAvatar() {
//        return this.avatar;
//    }

    /**
     * 设置lastIp
     */
    public void setLastIp(Integer value) {
        this.lastIp = value;
    }

    /**
     * 获得lastIp
     */
    public Integer getLastIp() {
        return this.lastIp;
    }

    /**
     * 获得上次登录时间
     */
    public String getLastTimeString() {
        if (null != getLastTime()) {
            return DateFormatUtils.format(getLastTime(), FORMAT_LAST_TIME);
        }
        return null;
    }

    /**
     * 设置上次登录时间
     */
    public void setLastTimeString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setLastTime(DateUtils.parseDate(value, new String[]{FORMAT_LAST_TIME}));
        }
    }

    /**
     * 设置上次登录时间
     */
    public void setLastTime(Date value) {
        this.lastTime = value;
    }

    /**
     * 获得上次登录时间
     */
    public Date getLastTime() {
        return this.lastTime;
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
                .append("Account", getAccount())
                .append("Username", getUsername())
                .append("Password", getPassword())
                .append("Email", getEmail())
                .append("RandomSalt", getRandomSalt())
                .append("Avatar", getAvatar())
                .append("LastIp", getLastIp())
                .append("LastTime", getLastTime())
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean equals(Object obj) {
        if (obj instanceof User == false) return false;
        if (this == obj) return true;
        User other = (User) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }

    private UserDetail userDetail;
    private UserSkill[] userSkills;

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public UserSkill[] getUserSkills() {
        return userSkills;
    }

    public void setUserSkills(UserSkill[] userSkills) {
        this.userSkills = userSkills;
    }
    /**
     * 获得用户头像
     */
    public String getAvatar() {
        avatar = "/static/assets/avatars/avatar"+ (1+ RandomUtils.nextInt(5))+".png";
        return this.avatar;
    }

//    public String getAccount(){
//        return  RandomStringUtils.randomAlphabetic(5);
//    }

    public String getUsername(){
        return  RandomStringUtils.randomAlphabetic(5);
    }

//    public static final void main(String[] arg){
//        System.out.println(RandomStringUtils.randomAscii(15));
//    }
}

