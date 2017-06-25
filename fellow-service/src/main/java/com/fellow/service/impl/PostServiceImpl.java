/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service.impl;

import com.fellow.dao.PostMapper;
import com.fellow.domain.model.Post;
import com.fellow.domain.query.PostQuery;
import com.fellow.service.PostService;
import com.fellow.service.base.ServiceAbstract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl extends ServiceAbstract<PostMapper> implements PostService {

    @Transactional(readOnly = true)
    @Override
    public List<Post> selectMyPost(PostQuery query) {
        return repository.selectMyPost(query);
    }



}
