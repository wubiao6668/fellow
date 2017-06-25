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
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;

public class UserDetail extends BaseDomain implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //date formats
    public static final String FORMAT_BIRTHDAY = DATE_TIME_FORMAT;
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
     * user表主键       db_column: user_id
     */
    @NotNull
    @Max(9223372036854775807L)
    private Long userId;
    /**
     * 登录账号       db_column: account
     */
    @NotBlank
    @Length(max = 50)
    private String account;
    /**
     * 用户名       db_column: username
     */
    @NotBlank
    @Length(max = 255)
    private String username;
    /**
     * 头像       db_column: avatar
     */
    @NotBlank
    @Length(max = 200)
    private String avatar;
    /**
     * 电子邮件       db_column: email
     */
    @NotBlank
    @Email
    @Length(max = 50)
    private String email;
    /**
     * 100男，200女       db_column: sex
     */
    @NotNull
    @Max(32767)
    private Integer sex;
    /**
     * 职业       db_column: profession
     */
    @NotNull
    @Max(99999999999L)
    private Integer profession;
    /**
     * 现居住       db_column: domicile_path
     */
    @NotBlank
    @Length(max = 30)
    private String domicilePath;
    /**
     * 现居       db_column: domicile_name
     */
    @NotBlank
    @Length(max = 255)
    private String domicileName;
    /**
     * 故乡       db_column: hometown_path
     */
    @NotBlank
    @Length(max = 30)
    private String hometownPath;
    /**
     * 故乡       db_column: hometown_name
     */
    @NotBlank
    @Length(max = 255)
    private String hometownName;
    /**
     * 生日       db_column: birthday
     */
    @NotNull
    private Date birthday;
    /**
     * 身高       db_column: height
     */
    @NotNull
    @Max(99999999999L)
    private Integer height;
    /**
     * 情感状态 100-单身 200-       db_column: relationship_status
     */
    @NotNull
    @Max(32767)
    private Integer relationshipStatus;
    /**
     * 工作单位       db_column: work_unit
     */
    @NotBlank
    @Length(max = 255)
    private String workUnit;
    /**
     * 体重       db_column: weight
     */
    @NotNull
    private Integer weight;
    /**
     * 月收入       db_column: monthly_income
     */
    @NotNull
    @Max(99999999999L)
    private Integer monthlyIncome;
    /**
     * 学历       db_column: education
     */
    @NotNull
    @Max(32767)
    private Integer education;
    /**
     * 主修专业       db_column: major_id
     */
    @NotNull
    @Max(9223372036854775807L)
    private Long majorId;
    /**
     * 专业       db_column: major_path
     */
    @NotBlank
    @Length(max = 30)
    private String majorPath;
    /**
     * 大专学校       db_column: college_school_id
     */
    @NotNull
    @Max(9223372036854775807L)
    private Long collegeSchoolId;
    /**
     * 学校路径       db_column: college_school_path
     */
    @NotBlank
    @Length(max = 255)
    private String collegeSchoolPath;
    /**
     * 大专毕业时间       db_column: college_year
     */
    @NotNull
    @Max(32767)
    private Integer collegeYear;
    /**
     * 生活精度       db_column: live_lng
     */
    @NotNull
    private Double liveLng;
    /**
     * 生活纬度       db_column: live_lat
     */
    @NotNull
    private Double liveLat;
    /**
     * 工作精度       db_column: work_lng
     */
    @NotNull
    private Double workLng;
    /**
     * 工作纬度       db_column: work_lat
     */
    @NotNull
    private Double workLat;
    /**
     * 学习精度       db_column: study_lng
     */
    @NotNull
    private Double studyLng;
    /**
     * 学习纬度       db_column: study_lat
     */
    @NotNull
    private Double studyLat;
    /**
     * aboutMe       db_column: about_me
     */
    @NotBlank
    @Length(max = 1000)
    private String aboutMe;
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

    public UserDetail() {
    }

    public UserDetail(java.lang.Long id) {
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
     * 设置user表主键
     */
    public void setUserId(Long value) {
        this.userId = value;
    }

    /**
     * 获得user表主键
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * 设置登录账号
     */
    public void setAccount(String value) {
        this.account = value;
    }

    /**
     * 获得登录账号
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

    /**
     * 获得用户名
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * 设置头像
     */
    public void setAvatar(String value) {
        this.avatar = value;
    }

    /**
     * 获得头像
     */
    public String getAvatar() {
        return this.avatar;
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
     * 设置100男，200女
     */
    public void setSex(Integer value) {
        this.sex = value;
    }

    /**
     * 获得100男，200女
     */
    public Integer getSex() {
        return this.sex;
    }

    /**
     * 设置职业
     */
    public void setProfession(Integer value) {
        this.profession = value;
    }

    /**
     * 获得职业
     */
    public Integer getProfession() {
        return this.profession;
    }

    /**
     * 设置现居住
     */
    public void setDomicilePath(String value) {
        this.domicilePath = value;
    }

    /**
     * 获得现居住
     */
    public String getDomicilePath() {
        return this.domicilePath;
    }

    /**
     * 设置现居
     */
    public void setDomicileName(String value) {
        this.domicileName = value;
    }

    /**
     * 获得现居
     */
    public String getDomicileName() {
        return this.domicileName;
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
    public String getHometownPath() {
        return this.hometownPath;
    }

    /**
     * 设置故乡
     */
    public void setHometownName(String value) {
        this.hometownName = value;
    }

    /**
     * 获得故乡
     */
    public String getHometownName() {
        return this.hometownName;
    }

    /**
     * 获得生日
     */
    public String getBirthdayString() {
        if (null != getBirthday()) {
            return DateFormatUtils.format(getBirthday(), FORMAT_BIRTHDAY);
        }
        return null;
    }

    /**
     * 设置生日
     */
    public void setBirthdayString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setBirthday(DateUtils.parseDate(value, new String[]{FORMAT_BIRTHDAY}));
        }
    }

    /**
     * 设置生日
     */
    public void setBirthday(Date value) {
        this.birthday = value;
    }

    /**
     * 获得生日
     */
    public Date getBirthday() {
        return this.birthday;
    }

    /**
     * 设置身高
     */
    public void setHeight(Integer value) {
        this.height = value;
    }

    /**
     * 获得身高
     */
    public Integer getHeight() {
        return this.height;
    }

    /**
     * 设置情感状态 100-单身 200-
     */
    public void setRelationshipStatus(Integer value) {
        this.relationshipStatus = value;
    }

    /**
     * 获得情感状态 100-单身 200-
     */
    public Integer getRelationshipStatus() {
        return this.relationshipStatus;
    }

    /**
     * 设置工作单位
     */
    public void setWorkUnit(String value) {
        this.workUnit = value;
    }

    /**
     * 获得工作单位
     */
    public String getWorkUnit() {
        return this.workUnit;
    }

    /**
     * 设置体重
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 获得体重
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置月收入
     */
    public void setMonthlyIncome(Integer value) {
        this.monthlyIncome = value;
    }

    /**
     * 获得月收入
     */
    public Integer getMonthlyIncome() {
        return this.monthlyIncome;
    }

    /**
     * 设置学历
     */
    public void setEducation(Integer value) {
        this.education = value;
    }

    /**
     * 获得学历
     */
    public Integer getEducation() {
        return this.education;
    }

    /**
     * 设置主修专业
     */
    public void setMajorId(Long value) {
        this.majorId = value;
    }

    /**
     * 获得主修专业
     */
    public Long getMajorId() {
        return this.majorId;
    }

    /**
     * 设置专业
     */
    public void setMajorPath(String value) {
        this.majorPath = value;
    }

    /**
     * 获得专业
     */
    public String getMajorPath() {
        return this.majorPath;
    }

    /**
     * 设置大专学校
     */
    public void setCollegeSchoolId(Long value) {
        this.collegeSchoolId = value;
    }

    /**
     * 获得大专学校
     */
    public Long getCollegeSchoolId() {
        return this.collegeSchoolId;
    }

    /**
     * 设置学校路径
     */
    public void setCollegeSchoolPath(String value) {
        this.collegeSchoolPath = value;
    }

    /**
     * 获得学校路径
     */
    public String getCollegeSchoolPath() {
        return this.collegeSchoolPath;
    }

    /**
     * 设置大专毕业时间
     */
    public void setCollegeYear(Integer value) {
        this.collegeYear = value;
    }

    /**
     * 获得大专毕业时间
     */
    public Integer getCollegeYear() {
        return this.collegeYear;
    }

    /**
     * 设置生活精度
     */
    public void setLiveLng(Double value) {
        this.liveLng = value;
    }

    /**
     * 获得生活精度
     */
    public Double getLiveLng() {
        return this.liveLng;
    }

    /**
     * 设置生活纬度
     */
    public void setLiveLat(Double value) {
        this.liveLat = value;
    }

    /**
     * 获得生活纬度
     */
    public Double getLiveLat() {
        return this.liveLat;
    }

    /**
     * 设置工作精度
     */
    public void setWorkLng(Double value) {
        this.workLng = value;
    }

    /**
     * 获得工作精度
     */
    public Double getWorkLng() {
        return this.workLng;
    }

    /**
     * 设置工作纬度
     */
    public void setWorkLat(Double value) {
        this.workLat = value;
    }

    /**
     * 获得工作纬度
     */
    public Double getWorkLat() {
        return this.workLat;
    }

    /**
     * 设置学习精度
     */
    public void setStudyLng(Double value) {
        this.studyLng = value;
    }

    /**
     * 获得学习精度
     */
    public Double getStudyLng() {
        return this.studyLng;
    }

    /**
     * 设置学习纬度
     */
    public void setStudyLat(Double value) {
        this.studyLat = value;
    }

    /**
     * 获得学习纬度
     */
    public Double getStudyLat() {
        return this.studyLat;
    }

    /**
     * 设置aboutMe
     */
    public void setAboutMe(String value) {
        this.aboutMe = value;
    }

    /**
     * 获得aboutMe
     */
    public String getAboutMe() {
        return this.aboutMe;
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
                .append("UserId", getUserId())
                .append("Account", getAccount())
                .append("Username", getUsername())
                .append("Avatar", getAvatar())
                .append("Email", getEmail())
                .append("Sex", getSex())
                .append("Profession", getProfession())
                .append("DomicilePath", getDomicilePath())
                .append("DomicileName", getDomicileName())
                .append("HometownPath", getHometownPath())
                .append("HometownName", getHometownName())
                .append("Birthday", getBirthday())
                .append("Height", getHeight())
                .append("RelationshipStatus", getRelationshipStatus())
                .append("WorkUnit", getWorkUnit())
                .append("Weight", getWeight())
                .append("MonthlyIncome", getMonthlyIncome())
                .append("Education", getEducation())
                .append("MajorId", getMajorId())
                .append("MajorPath", getMajorPath())
                .append("CollegeSchoolId", getCollegeSchoolId())
                .append("CollegeSchoolPath", getCollegeSchoolPath())
                .append("CollegeYear", getCollegeYear())
                .append("LiveLng", getLiveLng())
                .append("LiveLat", getLiveLat())
                .append("WorkLng", getWorkLng())
                .append("WorkLat", getWorkLat())
                .append("StudyLng", getStudyLng())
                .append("StudyLat", getStudyLat())
                .append("AboutMe", getAboutMe())
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
        if (obj instanceof UserDetail == false) return false;
        if (this == obj) return true;
        UserDetail other = (UserDetail) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }

    private String sexText;
    private String educationText;
    private String professionText;
    private String relationshipStatusText;
    private Long domicileFirst;
    private String domicileFirstText;
    private Long domicileSecond;
    private String domicileSecondText;
    private Long domicileThird ;
    private String domicileThirdText;
    private Long domicileFourth;
    private String domicileFourthText;
    private Long hometownFirst;
    private String hometownFirstText;
    private Long hometownSecond;
    private String hometownSecondText;
    private Long hometownThird ;
    private String hometownThirdText;
    private Long hometownFourth;
    private String hometownFourthText;
    private String monthlyIncomeText;
    private String majorText;
    private Long majorFirst;
    private String majorFirstText;
    private String collegeSchoolIdText;
    private Long collegeSchoolFirst;
    private String collegeSchoolFirstText;
    private Long collegeSchoolSecond;
    private String collegeSchoolSecondText;
    private Integer age;

    public String getSexText() {
        return sexText;
    }

    public void setSexText(String sexText) {
        this.sexText = sexText;
    }

    public String getEducationText() {
        return educationText;
    }

    public void setEducationText(String educationText) {
        this.educationText = educationText;
    }

    public String getProfessionText() {
        return professionText;
    }

    public void setProfessionText(String professionText) {
        this.professionText = professionText;
    }

    public String getRelationshipStatusText() {
        return relationshipStatusText;
    }

    public void setRelationshipStatusText(String relationshipStatusText) {
        this.relationshipStatusText = relationshipStatusText;
    }

    public Long getDomicileFirst() {
        return domicileFirst;
    }

    public void setDomicileFirst(Long domicileFirst) {
        this.domicileFirst = domicileFirst;
    }

    public String getDomicileFirstText() {
        return domicileFirstText;
    }

    public void setDomicileFirstText(String domicileFirstText) {
        this.domicileFirstText = domicileFirstText;
    }

    public Long getDomicileSecond() {
        return domicileSecond;
    }

    public void setDomicileSecond(Long domicileSecond) {
        this.domicileSecond = domicileSecond;
    }

    public String getDomicileSecondText() {
        return domicileSecondText;
    }

    public void setDomicileSecondText(String domicileSecondText) {
        this.domicileSecondText = domicileSecondText;
    }

    public Long getHometownFirst() {
        return hometownFirst;
    }

    public void setHometownFirst(Long hometownFirst) {
        this.hometownFirst = hometownFirst;
    }

    public String getHometownFirstText() {
        return hometownFirstText;
    }

    public void setHometownFirstText(String hometownFirstText) {
        this.hometownFirstText = hometownFirstText;
    }

    public Long getHometownSecond() {
        return hometownSecond;
    }

    public void setHometownSecond(Long hometownSecond) {
        this.hometownSecond = hometownSecond;
    }

    public String getHometownSecondText() {
        return hometownSecondText;
    }

    public void setHometownSecondText(String hometownSecondText) {
        this.hometownSecondText = hometownSecondText;
    }

    public String getMonthlyIncomeText() {
        return monthlyIncomeText;
    }

    public void setMonthlyIncomeText(String monthlyIncomeText) {
        this.monthlyIncomeText = monthlyIncomeText;
    }

    public String getMajorText() {
        return majorText;
    }

    public void setMajorText(String majorText) {
        this.majorText = majorText;
    }

    public Long getMajorFirst() {
        return majorFirst;
    }

    public void setMajorFirst(Long majorFirst) {
        this.majorFirst = majorFirst;
    }

    public String getMajorFirstText() {
        return majorFirstText;
    }

    public void setMajorFirstText(String majorFirstText) {
        this.majorFirstText = majorFirstText;
    }

    public String getCollegeSchoolIdText() {
        return collegeSchoolIdText;
    }

    public void setCollegeSchoolIdText(String collegeSchoolIdText) {
        this.collegeSchoolIdText = collegeSchoolIdText;
    }

    public Long getCollegeSchoolFirst() {
        return collegeSchoolFirst;
    }

    public void setCollegeSchoolFirst(Long collegeSchoolFirst) {
        this.collegeSchoolFirst = collegeSchoolFirst;
    }

    public String getCollegeSchoolFirstText() {
        return collegeSchoolFirstText;
    }

    public void setCollegeSchoolFirstText(String collegeSchoolFirstText) {
        this.collegeSchoolFirstText = collegeSchoolFirstText;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getDomicileThird() {
        return domicileThird;
    }

    public void setDomicileThird(Long domicileThird) {
        this.domicileThird = domicileThird;
    }

    public String getDomicileThirdText() {
        return domicileThirdText;
    }

    public void setDomicileThirdText(String domicileThirdText) {
        this.domicileThirdText = domicileThirdText;
    }

    public Long getDomicileFourth() {
        return domicileFourth;
    }

    public void setDomicileFourth(Long domicileFourth) {
        this.domicileFourth = domicileFourth;
    }

    public String getDomicileFourthText() {
        return domicileFourthText;
    }

    public void setDomicileFourthText(String domicileFourthText) {
        this.domicileFourthText = domicileFourthText;
    }

    public Long getHometownThird() {
        return hometownThird;
    }

    public void setHometownThird(Long hometownThird) {
        this.hometownThird = hometownThird;
    }

    public String getHometownThirdText() {
        return hometownThirdText;
    }

    public void setHometownThirdText(String hometownThirdText) {
        this.hometownThirdText = hometownThirdText;
    }

    public Long getHometownFourth() {
        return hometownFourth;
    }

    public void setHometownFourth(Long hometownFourth) {
        this.hometownFourth = hometownFourth;
    }

    public String getHometownFourthText() {
        return hometownFourthText;
    }

    public void setHometownFourthText(String hometownFourthText) {
        this.hometownFourthText = hometownFourthText;
    }

    public Long getCollegeSchoolSecond() {
        return collegeSchoolSecond;
    }

    public void setCollegeSchoolSecond(Long collegeSchoolSecond) {
        this.collegeSchoolSecond = collegeSchoolSecond;
    }

    public String getCollegeSchoolSecondText() {
        return collegeSchoolSecondText;
    }

    public void setCollegeSchoolSecondText(String collegeSchoolSecondText) {
        this.collegeSchoolSecondText = collegeSchoolSecondText;
    }
}

