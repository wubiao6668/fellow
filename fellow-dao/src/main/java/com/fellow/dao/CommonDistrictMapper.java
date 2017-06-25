/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.CommonDistrict;
import com.fellow.domain.query.CommonDistrictQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonDistrictMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

    List<CommonDistrict> selectByUpid(CommonDistrictQuery commonDistrictQuery);

}