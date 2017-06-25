/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service.impl;

import com.fellow.dao.MessageImgMapper;
import com.fellow.domain.model.MessageImg;
import com.fellow.service.MessageImgService;
import com.fellow.service.base.ServiceAbstract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageImgServiceImpl extends ServiceAbstract< MessageImgMapper> implements MessageImgService {

    @Transactional(readOnly = true)
    @Override
    public List<MessageImg> selectImgByDetailIds(List<Long> detailIdList) {
        return repository.selectImgByDetailIds(detailIdList);
    }
}
