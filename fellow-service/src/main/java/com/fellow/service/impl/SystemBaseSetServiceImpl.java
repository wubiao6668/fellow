/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.service.impl;

import com.fellow.dao.SystemBaseSetMapper;
import com.fellow.domain.model.SystemBaseSet;
import com.fellow.domain.query.SystemBaseSetQuery;
import com.fellow.service.SystemBaseSetService;
import com.fellow.service.base.ServiceAbstract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemBaseSetServiceImpl extends ServiceAbstract<SystemBaseSetMapper> implements SystemBaseSetService {

    @Transactional(readOnly = true)
    @Override
    public SystemBaseSet selectByAccount(SystemBaseSetQuery systemBaseSetQuery) {
        return repository.selectByAccount(systemBaseSetQuery);
    }

    @Transactional
    @Override
    public int deleteByAccount(SystemBaseSet systemBaseSet) {
        return repository.deleteByAccount(systemBaseSet);
    }

    @Transactional
    @Override
    public int updateBaseInfo(SystemBaseSet systemBaseSet) {
        repository.deleteByAccount(systemBaseSet);
        return repository.insertSelective(systemBaseSet);
    }
}
