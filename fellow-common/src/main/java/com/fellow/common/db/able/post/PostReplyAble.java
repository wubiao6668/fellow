package com.fellow.common.db.able.post;

import com.fellow.domain.model.PostCommentReply;
import com.fellow.domain.model.post.PostReplyDomain;
import com.fellow.domain.query.post.PostReplyQuery;

import java.util.List;

/**
 * Created by wubiao on 2016/12/30.
 */
public interface PostReplyAble {

    List<PostReplyDomain> queryReply(PostReplyQuery q);

    long queryReplyCount(PostReplyQuery q);

    void reply(PostReplyDomain postReplyDomain);

    int deleteReplyByAccount(PostReplyDomain postReplyDomain);
}
