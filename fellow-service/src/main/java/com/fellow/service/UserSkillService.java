/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.UserSkill;
import com.fellow.domain.query.UserSkillQuery;

import java.util.List;

public interface UserSkillService extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble{

    List<UserSkill> selectPersonalSkill(UserSkillQuery userSkillQuery);

    int updatePersonalSkill(UserSkill skill);

    int updateAfterDeletePersonalSkills(UserSkill[] skills, List<Long> idList);
}
