package com.fellow.domain.web.array;

import com.fellow.domain.model.UserSkill;

import java.io.Serializable;

/**
 * Created by wubiao on 2016/11/3.
 */
public class UserSkillArray implements Serializable {

    private static final long serialVersionUID = -7802680945033459967L;
    private UserSkill[] userSkills;

    public UserSkill[] getUserSkills() {
        return userSkills;
    }

    public void setUserSkills(UserSkill[] userSkills) {
        this.userSkills = userSkills;
    }
}
