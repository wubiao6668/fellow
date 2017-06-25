/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.service;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.PersonalDynamicsComment;
import com.fellow.domain.query.PersonalDynamicsCommentQuery;
import com.fellow.domain.vo.PersonalDynamicsCommentVo;

import java.util.List;

public interface PersonalDynamicsCommentService extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

    List<PersonalDynamicsComment> selectPerDynCommentByPersonalId(PersonalDynamicsCommentQuery commentQuery);

    int commentDynReply(PersonalDynamicsComment personalDynamicsComment);

    List<PersonalDynamicsCommentVo> selectPerDynCommentAndSetHadRead(PersonalDynamicsCommentQuery commentQuery);

    int deleteDynCommentById(PersonalDynamicsComment personalDynamicsComment);

}
