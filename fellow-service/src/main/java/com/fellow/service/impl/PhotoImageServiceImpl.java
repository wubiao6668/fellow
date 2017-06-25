/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service.impl;

import com.fellow.common.exception.BusinessException;
import com.fellow.dao.PhotoImageMapper;
import com.fellow.domain.model.PhotoImage;
import com.fellow.service.PhotoImageService;
import com.fellow.service.base.ServiceAbstract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhotoImageServiceImpl extends ServiceAbstract<PhotoImageMapper> implements PhotoImageService{

    @Transactional(readOnly = true)
    @Override
    public List<PhotoImage> selectPersonalImages(String account) {
        return repository.selectPersonalImages(account);
    }

    @Transactional
    @Override
    public int deletePersonalImage(PhotoImage photoImage) {
        int row = repository.deletePersonalImage(photoImage);
        if (1 != row){
            throw new BusinessException("删除个人形象片失败！");
        }
        return row;
    }
}
