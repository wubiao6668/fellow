/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.service;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.ForgetPassword;
import com.fellow.domain.query.ForgetPasswordQuery;

public interface ForgetPasswordService extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

    void  sendEmail(ForgetPassword forgetPassword);

    int  deleteByEmail(ForgetPassword forgetPassword);

    ForgetPassword selectByEmailAndToken(ForgetPasswordQuery forgetPasswordQuery);

}
