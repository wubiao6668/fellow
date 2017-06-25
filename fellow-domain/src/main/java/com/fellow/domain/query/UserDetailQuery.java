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

public class UserDetailQuery extends QueryDomain implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;

    //date formats
    public static final String FORMAT_BIRTHDAY = DATE_TIME_FORMAT;
    public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_TS = DATE_TIME_FORMAT;


    /** id */
    private Long id;
    /** user表主键 */
    private Long userId;
    /** 登录账号 */
    private String account;
    /** 用户名 */
    private String username;
    /** 头像 */
    private String avatar;
    /** 电子邮件 */
    private String email;
    /** 100男，200女 */
    private Integer sex;
    /** 职业 */
    private Integer profession;
    /** 现居住 */
    private String domicilePath;
    /** 现居 */
    private String domicileName;
    /** 故乡 */
    private String hometownPath;
    /** 故乡 */
    private String hometownName;
    /** 生日 */
    private Date birthdayBegin;
    private Date birthdayEnd;
    /** 身高 */
    private Integer height;
    /** 情感状态 100-单身 200- */
    private Integer relationshipStatus;
    /** 工作单位 */
    private String workUnit;
    /** 体重 */
    private Integer weight;
    /** 月收入 */
    private Integer monthlyIncome;
    /** 学历 */
    private Integer education;
    /** 主修专业 */
    private Long majorId;
    /** 专业 */
    private String majorPath;
    /** 大专学校 */
    private Long collegeSchoolId;
    /** 学校路径 */
    private String collegeSchoolPath;
    /** 大专毕业时间 */
    private Integer collegeYear;
    /** 生活精度 */
    private Double liveLng;
    /** 生活纬度 */
    private Double liveLat;
    /** 工作精度 */
    private Double workLng;
    /** 工作纬度 */
    private Double workLat;
    /** 学习精度 */
    private Double studyLng;
    /** 学习纬度 */
    private Double studyLat;
    /** aboutMe */
    private String aboutMe;
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
     * 获得user表主键
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * 设置user表主键
     */
    public void setUserId(Long value) {
        this.userId = value;
    }

    /**
     * 获得登录账号
     */
    public String getAccount() {
        return this.account;
    }

    /**
     * 设置登录账号
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
     * 获得头像
     */
    public String getAvatar() {
        return this.avatar;
    }

    /**
     * 设置头像
     */
    public void setAvatar(String value) {
        this.avatar = value;
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
     * 获得100男，200女
     */
    public Integer getSex() {
        return this.sex;
    }

    /**
     * 设置100男，200女
     */
    public void setSex(Integer value) {
        this.sex = value;
    }

    /**
     * 获得职业
     */
    public Integer getProfession() {
        return this.profession;
    }

    /**
     * 设置职业
     */
    public void setProfession(Integer value) {
        this.profession = value;
    }

    /**
     * 获得现居住
     */
    public String getDomicilePath() {
        return this.domicilePath;
    }

    /**
     * 设置现居住
     */
    public void setDomicilePath(String value) {
        this.domicilePath = value;
    }

    /**
     * 获得现居
     */
    public String getDomicileName() {
        return this.domicileName;
    }

    /**
     * 设置现居
     */
    public void setDomicileName(String value) {
        this.domicileName = value;
    }

    /**
     * 获得故乡
     */
    public String getHometownPath() {
        return this.hometownPath;
    }

    /**
     * 设置故乡
     */
    public void setHometownPath(String value) {
        this.hometownPath = value;
    }

    /**
     * 获得故乡
     */
    public String getHometownName() {
        return this.hometownName;
    }

    /**
     * 设置故乡
     */
    public void setHometownName(String value) {
        this.hometownName = value;
    }

    /**
     * 获得生日开始
     */
    public Date getBirthdayBegin() {
        return this.birthdayBegin;
    }

    /**
     * 获得生日开始
     */
    public String getBirthdayBeginString() {
        if (null != getBirthdayBegin()){
            return DateFormatUtils.format(getBirthdayBegin(),FORMAT_BIRTHDAY);
        }
        return null;
    }

    /**
     * 设置生日开始
     */
    public void setBirthdayBegin(Date value) {
        this.birthdayBegin = value;
    }

    /**
     * 设置生日开始
     */
    public void setBirthdayBeginString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setBirthdayBegin(DateUtils.parseDate(value, new String[]{FORMAT_BIRTHDAY}));
        }
    }

    /**
     * 获得生日结束
     */
    public Date getBirthdayEnd() {
        return this.birthdayEnd;
    }

    /**
     * 获得生日结束
     */
    public String getBirthdayEndString() {
        if (null != getBirthdayEnd()){
            return DateFormatUtils.format(getBirthdayEnd(),FORMAT_BIRTHDAY);
        }
        return null;
    }

    /**
     * 设置生日结束
     */
    public void setBirthdayEnd(Date value) {
        this.birthdayEnd = value;
    }

    /**
     * 设置生日结束
     */
    public void setBirthdayEndString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setBirthdayEnd(DateUtils.parseDate(value, new String[]{FORMAT_BIRTHDAY}));
        }
    }

    /**
     * 获得身高
     */
    public Integer getHeight() {
        return this.height;
    }

    /**
     * 设置身高
     */
    public void setHeight(Integer value) {
        this.height = value;
    }

    /**
     * 获得情感状态 100-单身 200-
     */
    public Integer getRelationshipStatus() {
        return this.relationshipStatus;
    }

    /**
     * 设置情感状态 100-单身 200-
     */
    public void setRelationshipStatus(Integer value) {
        this.relationshipStatus = value;
    }

    /**
     * 获得工作单位
     */
    public String getWorkUnit() {
        return this.workUnit;
    }

    /**
     * 设置工作单位
     */
    public void setWorkUnit(String value) {
        this.workUnit = value;
    }

    /**
     * 获得体重
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置体重
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 获得月收入
     */
    public Integer getMonthlyIncome() {
        return this.monthlyIncome;
    }

    /**
     * 设置月收入
     */
    public void setMonthlyIncome(Integer value) {
        this.monthlyIncome = value;
    }

    /**
     * 获得学历
     */
    public Integer getEducation() {
        return this.education;
    }

    /**
     * 设置学历
     */
    public void setEducation(Integer value) {
        this.education = value;
    }

    /**
     * 获得主修专业
     */
    public Long getMajorId() {
        return this.majorId;
    }

    /**
     * 设置主修专业
     */
    public void setMajorId(Long value) {
        this.majorId = value;
    }

    /**
     * 获得专业
     */
    public String getMajorPath() {
        return this.majorPath;
    }

    /**
     * 设置专业
     */
    public void setMajorPath(String value) {
        this.majorPath = value;
    }

    /**
     * 获得大专学校
     */
    public Long getCollegeSchoolId() {
        return this.collegeSchoolId;
    }

    /**
     * 设置大专学校
     */
    public void setCollegeSchoolId(Long value) {
        this.collegeSchoolId = value;
    }

    /**
     * 获得学校路径
     */
    public String getCollegeSchoolPath() {
        return this.collegeSchoolPath;
    }

    /**
     * 设置学校路径
     */
    public void setCollegeSchoolPath(String value) {
        this.collegeSchoolPath = value;
    }

    /**
     * 获得大专毕业时间
     */
    public Integer getCollegeYear() {
        return this.collegeYear;
    }

    /**
     * 设置大专毕业时间
     */
    public void setCollegeYear(Integer value) {
        this.collegeYear = value;
    }

    /**
     * 获得生活精度
     */
    public Double getLiveLng() {
        return this.liveLng;
    }

    /**
     * 设置生活精度
     */
    public void setLiveLng(Double value) {
        this.liveLng = value;
    }

    /**
     * 获得生活纬度
     */
    public Double getLiveLat() {
        return this.liveLat;
    }

    /**
     * 设置生活纬度
     */
    public void setLiveLat(Double value) {
        this.liveLat = value;
    }

    /**
     * 获得工作精度
     */
    public Double getWorkLng() {
        return this.workLng;
    }

    /**
     * 设置工作精度
     */
    public void setWorkLng(Double value) {
        this.workLng = value;
    }

    /**
     * 获得工作纬度
     */
    public Double getWorkLat() {
        return this.workLat;
    }

    /**
     * 设置工作纬度
     */
    public void setWorkLat(Double value) {
        this.workLat = value;
    }

    /**
     * 获得学习精度
     */
    public Double getStudyLng() {
        return this.studyLng;
    }

    /**
     * 设置学习精度
     */
    public void setStudyLng(Double value) {
        this.studyLng = value;
    }

    /**
     * 获得学习纬度
     */
    public Double getStudyLat() {
        return this.studyLat;
    }

    /**
     * 设置学习纬度
     */
    public void setStudyLat(Double value) {
        this.studyLat = value;
    }

    /**
     * 获得aboutMe
     */
    public String getAboutMe() {
        return this.aboutMe;
    }

    /**
     * 设置aboutMe
     */
    public void setAboutMe(String value) {
        this.aboutMe = value;
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

    private Integer heightBegin;
    private Integer heightEnd;

    private Integer skillId;

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public Integer getHeightBegin() {
        return heightBegin;
    }

    public void setHeightBegin(Integer heightBegin) {
        this.heightBegin = heightBegin;
    }

    public Integer getHeightEnd() {
        return heightEnd;
    }

    public void setHeightEnd(Integer heightEnd) {
        this.heightEnd = heightEnd;
    }
}

