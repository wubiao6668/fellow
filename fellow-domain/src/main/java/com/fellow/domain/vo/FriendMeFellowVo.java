package com.fellow.domain.vo;

import com.fellow.domain.model.Friend;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;

/**
 * Created by wubiao on 2017/4/11.
 */
public class FriendMeFellowVo extends Friend {

    private String username;
    private String email;
    private String avatar;
    private Integer sex;
    private Date birthday;
    private String domicilePath;
    private String domicileText;
    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDomicilePath() {
        return domicilePath;
    }

    public void setDomicilePath(String domicilePath) {
        this.domicilePath = domicilePath;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDomicileText() {
        domicileText="沈阳,浑南" + RandomStringUtils.randomNumeric(1);
        return domicileText;
    }

    public void setDomicileText(String domicileText) {
        this.domicileText = domicileText;
    }
}
