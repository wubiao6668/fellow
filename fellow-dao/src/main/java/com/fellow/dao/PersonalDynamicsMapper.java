/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.PersonalDynamics;
import com.fellow.domain.query.PersonalDynamicsQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PersonalDynamicsMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble,DecrementAble {


    List<PersonalDynamics> selectDynamics(PersonalDynamicsQuery dynamicsQuery);

    List<PersonalDynamics> selectPersonalDynamics(PersonalDynamicsQuery dynamicsQuery);

    long selectDynamicsCount(PersonalDynamicsQuery dynamicsQuery);

    int replyIncrement(Number key);

    int loveIncrement(Number key);

    int loveDecrement(Number key);

    int upIncrement(Number key);

    int upDecrement(Number key);

    int updateUpAndLove(PersonalDynamics personalDynamics);

    List<PersonalDynamics> selectContentByIds(@Param("idSet") Set<Long> idSet);


}