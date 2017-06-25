/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.service;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.PersonalDynamicsUp;
import com.fellow.domain.query.PersonalDynamicsUpQuery;

import java.util.List;

public interface PersonalDynamicsUpService extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

    PersonalDynamicsUp selectCommentByAccount(PersonalDynamicsUpQuery upQuery);

    void thumbsDynamicsComment(PersonalDynamicsUp dynamicsUpDto);

    List<PersonalDynamicsUp> selectUpByPersonalId(PersonalDynamicsUpQuery dynamicsUpQuery);
}
