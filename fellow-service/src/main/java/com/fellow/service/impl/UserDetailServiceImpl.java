/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service.impl;

import com.fellow.common.exception.BusinessException;
import com.fellow.dao.UserDetailMapper;
import com.fellow.domain.model.UserDetail;
import com.fellow.domain.query.UserDetailQuery;
import com.fellow.service.UserDetailService;
import com.fellow.service.base.ServiceAbstract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailServiceImpl extends ServiceAbstract<UserDetailMapper> implements UserDetailService {

    @Transactional
    @Override
    public int updateBaseInfo(UserDetail userDetail) {
        int row = repository.updateBaseInfo(userDetail);
        if (1 != row) {
            throw new BusinessException("修改个人信息失败！");
        }
        return row;
    }

    @Transactional
    @Override
    public int updateAboutMe(UserDetail userDetail) {
        int row = repository.updateAboutMe(userDetail);
        if (1 != row) {
            throw new BusinessException("关于我修改失败！");
        }
        return row;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetail selectAboutMe(UserDetailQuery userDetailQuery) {
        return repository.selectAboutMe(userDetailQuery);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDetail> findUser(UserDetailQuery userDetailQuery) {
        return repository.findUser(userDetailQuery);
    }
}
