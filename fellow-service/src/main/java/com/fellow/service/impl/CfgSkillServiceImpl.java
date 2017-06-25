/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.service.impl;

import com.fellow.dao.CfgSkillMapper;
import com.fellow.domain.model.CfgSkill;
import com.fellow.domain.query.CfgSkillQuery;
import com.fellow.service.CfgSkillService;
import com.fellow.service.base.ServiceAbstract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@Service
public class CfgSkillServiceImpl extends ServiceAbstract<CfgSkillMapper> implements CfgSkillService {

    @Transactional(readOnly = true)
    @Override
    public List<CfgSkill> selectSkillByUpid(CfgSkillQuery cfgSkillQuery) {
        return repository.selectSkillByUpid(cfgSkillQuery);
    }

    @Transactional
    @Override
    public int initSkill(Map<String, TreeSet<String>> skillSetMap) {
        int rows = 0;
        CfgSkill cfgSkill = null;
        for (String keySet : skillSetMap.keySet()) {
            cfgSkill = new CfgSkill();
            cfgSkill.setName(keySet);
            cfgSkill.setLevel(1);
            cfgSkill.setUpid(0l);
            cfgSkill.setCreateAccount("systemInit");
            cfgSkill.setCreateName("systemInit");
            cfgSkill.setCreateTime(new Date());
            repository.insertSelective(cfgSkill);
            long id = cfgSkill.getId();
            for (String skillName : skillSetMap.get(keySet)) {
                cfgSkill = new CfgSkill();
                cfgSkill.setName(skillName);
                cfgSkill.setLevel(2);
                cfgSkill.setUpid(id);
                cfgSkill.setCreateAccount("systemInit");
                cfgSkill.setCreateName("systemInit");
                cfgSkill.setCreateTime(new Date());
                repository.insertSelective(cfgSkill);
            }
        }
        return rows;
    }
}
