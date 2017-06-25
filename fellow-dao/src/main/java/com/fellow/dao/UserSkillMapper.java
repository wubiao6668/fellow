/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.UserSkill;
import com.fellow.domain.query.UserSkillQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSkillMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble{

    List<UserSkill> selectPersonalSkill(UserSkillQuery userSkillQuery);

    int updatePersonalSkill(UserSkill skill);

    int deleteSkillsByAccount(@Param("account") String account,@Param("idList") List<Long> idList);
}