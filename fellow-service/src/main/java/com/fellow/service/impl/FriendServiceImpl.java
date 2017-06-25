/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service.impl;

import com.fellow.common.exception.BusinessException;
import com.fellow.dao.FollowInfoMapper;
import com.fellow.dao.FriendBlackListMapper;
import com.fellow.dao.FriendMapper;
import com.fellow.domain.enums.FriendTypeEnum;
import com.fellow.domain.model.FollowInfo;
import com.fellow.domain.model.Friend;
import com.fellow.domain.query.FriendQuery;
import com.fellow.domain.vo.FriendMeFellowVo;
import com.fellow.service.FriendService;
import com.fellow.service.base.ServiceAbstract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FriendServiceImpl extends ServiceAbstract<FriendMapper> implements FriendService {

    @Resource
    private FriendBlackListMapper friendBlackListMapper;
    @Resource
    private FollowInfoMapper followInfoMapper;

    @Transactional(readOnly = true)
    @Override
    public List<FriendMeFellowVo> selectPersonalFriend(FriendQuery friendQuery) {
        return repository.selectPersonalFriend(friendQuery);
    }

    @Transactional(readOnly = true)
    @Override
    public long selectPersonalFriendCount(FriendQuery friendQuery) {
        return repository.selectPersonalFriendCount(friendQuery);
    }

    @Transactional
    @Override
    public int updateFriendAlias(Friend friend) {
        int row = repository.updateFriendAlias(friend);
//        if (1 != row) {
//            throw new BusinessException("修改备注失败！");
//        }
        return row;
    }

    @Override
    public int updateRestrictDynamics(Friend friend) {
        int row = repository.updateRestrictDynamics(friend);
//        if (1 != row) {
//            throw new BusinessException("修改失败！");
//        }
        return row;
    }

    @Override
    public int updateShowDynamics(Friend friend) {
        int row = repository.updateShowDynamics(friend);
//        if (1 != row) {
//            throw new BusinessException("修改失败！");
//        }
        return row;
    }

    @Transactional
    @Override
    public int removeToBlackList(Friend friend) {
        int row = repository.updateFriendType(friend);
        if (1 != row) {

        }
        return row;
    }


    @Override
    public int deleteFriendByAccount(Friend friend) {
        return repository.deleteFriendByAccount(friend);
    }

    @Override
    public int cancelMeFollow(Friend friend) {
        int row = repository.deleteFriendByAccount(friend);
        if (row > 0){
            FollowInfo followInfo = new FollowInfo();
            followInfo.setAccount(session.getUserAccount());
            followInfo.setMeFellow(-row);
            followInfo.setUpdateAccount(session.getUserAccount());
            followInfo.setUpdateName(session.getUserName());
            row = followInfoMapper.updateFollowInfo(followInfo);
        }
        return row;
    }
}
