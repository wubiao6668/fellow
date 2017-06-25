package com.fellow.common.db.able.post;

import com.fellow.domain.base.QueryDomain;
import com.fellow.domain.model.post.PostDomain;

import java.util.List;

/**
 * Created by wubiao on 2016/12/30.
 */
public interface PostAble {

    void publish(PostDomain postDomain);

    List<PostDomain> selectPost(QueryDomain q);

    long selectPostCount(QueryDomain q);
}
