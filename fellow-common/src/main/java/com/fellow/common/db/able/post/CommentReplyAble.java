package com.fellow.common.db.able.post;

import com.fellow.domain.model.PostCommentReply;

/**
 * Created by wubiao on 2017/1/16.
 */
public interface CommentReplyAble {

    int deleteReplyByReplyId(PostCommentReply postCommentReply);

    int deleteReplyById(PostCommentReply postCommentReply);

}
