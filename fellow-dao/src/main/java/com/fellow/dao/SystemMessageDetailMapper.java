/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.SystemMessageDetail;
import com.fellow.domain.query.SystemMessageDetailQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemMessageDetailMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

    List<SystemMessageDetail> selectMySytemMessage(SystemMessageDetailQuery systemMessageDetailQuery);

    int deleteDetailBySendAccount(SystemMessageDetail systemMessageDetail);

    int deleteDetailByReceiveAccount(SystemMessageDetail systemMessageDetail);

}