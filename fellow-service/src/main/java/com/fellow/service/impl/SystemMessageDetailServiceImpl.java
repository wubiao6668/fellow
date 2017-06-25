/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service.impl;

import com.fellow.dao.SystemMessageDetailMapper;
import com.fellow.domain.model.SystemMessageDetail;
import com.fellow.domain.query.SystemMessageDetailQuery;
import com.fellow.service.SystemMessageDetailService;
import com.fellow.service.base.ServiceAbstract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemMessageDetailServiceImpl extends ServiceAbstract< SystemMessageDetailMapper> implements SystemMessageDetailService {

    @Transactional(readOnly = true)
    @Override
    public List<SystemMessageDetail> selectMySytemMessage(SystemMessageDetailQuery systemMessageDetailQuery) {
        return repository.selectMySytemMessage(systemMessageDetailQuery);
    }
}
