/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.service.impl;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.fellow.dao.AttitudeInfoMapper;
import com.fellow.domain.model.AttitudeInfo;
import com.fellow.domain.query.AttitudeInfoQuery;
import com.fellow.service.AttitudeInfoService;
import com.fellow.service.base.ServiceAbstract;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttitudeInfoServiceImpl extends ServiceAbstract<AttitudeInfoMapper> implements AttitudeInfoService{


    @Override
    public List<AttitudeInfo> selectByToAccount(AttitudeInfoQuery attitudeInfoQuery) {
        return null;
    }

    @Override
    public List<AttitudeInfo> selectByFromAccount(AttitudeInfoQuery attitudeInfoQuery) {
        return null;
    }
}
