package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.PersonalDynamicsComment;
import com.fellow.domain.query.PersonalDynamicsCommentQuery;
import com.fellow.domain.vo.PersonalDynamicsCommentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PersonalDynamicsCommentMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

    List<PersonalDynamicsComment> selectPerDynCommentByPersonalId(PersonalDynamicsCommentQuery commentQuery);

    List<PersonalDynamicsCommentVo> selectPerDynComment(PersonalDynamicsCommentQuery commentQuery);

    List<PersonalDynamicsComment> selectContentByIds(@Param("idSet")Set<Long> idSet);

    int deleteById(PersonalDynamicsComment dynamicsComment);

    int updateReadStatus(PersonalDynamicsComment dynamicsComment);

}