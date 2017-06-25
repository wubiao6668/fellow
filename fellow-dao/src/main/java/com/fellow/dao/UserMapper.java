/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.User;
import com.fellow.domain.query.UserQuery;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {
    /**
     * 根据登录账号查询用户信息
     *
     * @param userQuery
     * @return
     */
    User queryByAccount(UserQuery userQuery);

    int updateBaseInfo(User user);

    User selectBaseInfo(UserQuery userQuery);

    User validateByEmail(UserQuery userQuery);

    int updatePassword(User user);

    User checkedAccount(UserQuery userQuery);

    User checkedEmail(UserQuery userQuery);
}