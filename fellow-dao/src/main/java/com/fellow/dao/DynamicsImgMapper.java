/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.DynamicsImg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface  DynamicsImgMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

    /**
     * 根据id集合查询
     * @param pidList
     * @return
     */
    List<DynamicsImg> findListByPersonalIds(@Param("pidList") Set<Long> pidList, @Param("imgType") Integer imgType);
}