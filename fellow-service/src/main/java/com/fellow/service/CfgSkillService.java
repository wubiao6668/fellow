/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.service;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.CfgSkill;
import com.fellow.domain.query.CfgSkillQuery;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public interface CfgSkillService extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

    List<CfgSkill> selectSkillByUpid(CfgSkillQuery cfgSkillQuery);

    int initSkill(Map<String, TreeSet<String>> skillSetMap);
}
