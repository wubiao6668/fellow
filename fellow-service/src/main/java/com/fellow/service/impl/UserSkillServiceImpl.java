/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service.impl;

import com.fellow.common.exception.BusinessException;
import com.fellow.dao.UserSkillMapper;
import com.fellow.domain.model.UserSkill;
import com.fellow.domain.query.UserSkillQuery;
import com.fellow.domain.session.LocalSession;
import com.fellow.domain.session.Session;
import com.fellow.service.UserSkillService;
import com.fellow.service.base.ServiceAbstract;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserSkillServiceImpl extends ServiceAbstract< UserSkillMapper> implements UserSkillService {

    @Transactional(readOnly = true)
    @Override
    public List<UserSkill> selectPersonalSkill(UserSkillQuery userSkillQuery) {
        return repository.selectPersonalSkill(userSkillQuery);
    }

    @Transactional
    @Override
    public int updatePersonalSkill(UserSkill skill) {
        int row = repository.updatePersonalSkill(skill);
        if (1 != row) {
            throw new BusinessException("修改个人技能失败！");
        }
        return row;
    }

    @Transactional
    @Override
    public int updateAfterDeletePersonalSkills(UserSkill[] skills, List<Long> idList) {
        Session session = LocalSession.getSession();
        int rows = 0;
        int row = 0;
        //先删除
        row = repository.deleteSkillsByAccount(session.getUserAccount(),idList);
        rows = rows + row;
        if (ArrayUtils.isNotEmpty(skills)) {
            for (UserSkill userSkillTemp : skills) {
                row = repository.updatePersonalSkill(userSkillTemp);
                rows = rows + row;
                if (1 != row) {
                    throw new BusinessException("修改个人技能失败！");
                }
            }
        }

        return rows;
    }
}
