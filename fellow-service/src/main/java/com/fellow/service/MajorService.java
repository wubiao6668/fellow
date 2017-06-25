/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.service;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.Major;
import com.fellow.domain.query.MajorQuery;

import java.util.List;
import java.util.Map;

public interface MajorService extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

        List<Major> selectMajorByUpid(MajorQuery majorQuery);

        void initMajor( Map<String, String> marjorMap );
}
