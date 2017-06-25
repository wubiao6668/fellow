/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.service.impl;

import com.fellow.dao.CommonDistrictMapper;
import com.fellow.domain.model.CommonDistrict;
import com.fellow.domain.query.CommonDistrictQuery;
import com.fellow.service.CommonDistrictService;
import com.fellow.service.base.ServiceAbstract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommonDistrictServiceImpl extends ServiceAbstract<CommonDistrictMapper> implements CommonDistrictService {

    @Transactional(readOnly = true)
    @Override
    public List<CommonDistrict> selectByUpid(CommonDistrictQuery commonDistrictQuery) {
        return repository.selectByUpid(commonDistrictQuery);
    }
}
