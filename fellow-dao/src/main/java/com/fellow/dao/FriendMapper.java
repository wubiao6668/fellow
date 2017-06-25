/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.Friend;
import com.fellow.domain.query.FriendQuery;
import com.fellow.domain.vo.FriendMeFellowVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

    List<FriendMeFellowVo> selectPersonalFriend(FriendQuery friendQuery);

    long selectPersonalFriendCount(FriendQuery friendQuery);

    int updateFriendAlias(Friend friend);

    int updateRestrictDynamics(Friend friend);

    int updateShowDynamics(Friend friend);

    int deleteFriendByAccount(Friend friend);

    int updateFriendType(Friend friend);

}