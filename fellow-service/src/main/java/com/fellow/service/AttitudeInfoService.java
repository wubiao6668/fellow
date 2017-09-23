/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.service;
import com.fellow.domain.vo.AttitudeInfoVo;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.fellow.common.db.able.*;
import com.fellow.domain.model.AttitudeInfo;
import com.fellow.domain.query.AttitudeInfoQuery;

import java.util.List;

public interface AttitudeInfoService extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

    List<AttitudeInfoVo> selectByToAccount(AttitudeInfoQuery attitudeInfoQuery);

    List<AttitudeInfoVo> selectByFromAccount(AttitudeInfoQuery attitudeInfoQuery);

}
