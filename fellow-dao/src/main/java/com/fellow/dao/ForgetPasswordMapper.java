/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.ForgetPassword;
import com.fellow.domain.query.ForgetPasswordQuery;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgetPasswordMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

    int deleteByEmail(ForgetPassword forgetPassword);

    ForgetPassword selectByEmailAndToken(ForgetPasswordQuery forgetPasswordQuery);
}