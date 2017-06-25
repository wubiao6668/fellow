/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service;

import com.fellow.common.db.able.*;
import com.fellow.domain.base.BaseDomain;
import com.fellow.domain.model.PersonalDynamics;
import com.fellow.domain.mybatis.PageListImpl;
import com.fellow.domain.query.PersonalDynamicsQuery;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;

public interface PersonalDynamicsService extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble{


    void publishDynamics(PersonalDynamics personalDynamics);

    PageListImpl<BaseDomain> loadPersonalDynamic(PersonalDynamicsQuery personalDynamicsQuery, PageBounds pageBounds);

    List<PersonalDynamics> selectDynamics(PersonalDynamicsQuery dynamicsQuery);

    long selectDynamicsCount(PersonalDynamicsQuery dynamicsQuery);

}
