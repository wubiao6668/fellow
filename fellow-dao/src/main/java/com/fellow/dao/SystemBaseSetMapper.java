/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.SystemBaseSet;
import com.fellow.domain.query.SystemBaseSetQuery;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemBaseSetMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

    SystemBaseSet selectByAccount(SystemBaseSetQuery systemBaseSetQuery);

    Integer deleteByAccount(SystemBaseSet systemBaseSet);
}