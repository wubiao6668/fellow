package com.fellow.common.db.able.post;

import com.fellow.domain.model.post.PostDetailDomain;
import com.fellow.domain.model.post.PostDomain;

/**
 * Created by wubiao on 2017/1/12.
 */
public interface PostDetailAble {

    PostDetailDomain selectDetailByPostId(Long id);

}
