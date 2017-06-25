/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service.impl;

import com.fellow.common.exception.BusinessException;
import com.fellow.dao.UserDetailMapper;
import com.fellow.dao.UserMapper;
import com.fellow.domain.model.User;
import com.fellow.domain.query.UserQuery;
import com.fellow.service.UserService;
import com.fellow.service.base.ServiceAbstract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceAbstract<UserMapper> implements UserService {
    @Resource
    private UserDetailMapper userDetailMapper;

    /**
     * 根据登录账号查询用户信息
     *
     * @param userQuery
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public User queryByAccount(UserQuery userQuery) {
        return repository.queryByAccount(userQuery);
    }

    @Transactional(readOnly = true)
    @Override
    public User validateByEmail(UserQuery userQuery) {
        return repository.validateByEmail(userQuery);
    }

    @Transactional
    @Override
    public int updateBaseInfo(User user) {
        int row = repository.updateBaseInfo(user);
        if (1 != row) {
            throw new BusinessException("修改个人信息失败！");
        }
        row = userDetailMapper.updateBaseInfo(user.getUserDetail());
        if (1 != row) {
            throw new BusinessException("修改个人信息失败！");
        }
        return row;
    }

    @Transactional(readOnly = true)
    @Override
    public User selectBaseInfo(UserQuery userQuery) {
        return repository.selectBaseInfo(userQuery);
    }

    @Transactional
    @Override
    public int updatePassword(User user) {
        int row = repository.updatePassword(user);
        if (1 != row) {
            throw new BusinessException("修改密码失败！系统出错了！");
        }
        return row;
    }

    @Override
    public User checkedAccount(UserQuery userQuery) {
        return repository.checkedAccount(userQuery);
    }

    @Override
    public User checkedEmail(UserQuery userQuery) {
        return repository.checkedEmail(userQuery);
    }

    @Transactional
    @Override
    public void registerUser(User user) {
        repository.insertSelective(user);
        userDetailMapper.insertSelective(user.getUserDetail());
    }
}
