/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.UserDetail;
import com.fellow.domain.query.UserDetailQuery;

import java.util.List;

public interface UserDetailService extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble{

    int updateBaseInfo(UserDetail userDetail);

    int updateAboutMe(UserDetail userDetail);

    UserDetail selectAboutMe(UserDetailQuery userDetailQuery);

    List<UserDetail> findUser(UserDetailQuery userDetailQuery);

}
