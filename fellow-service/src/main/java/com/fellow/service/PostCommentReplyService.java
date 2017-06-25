/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service;

import com.fellow.common.db.able.*;
import com.fellow.common.db.able.post.CommentReplyAble;
import com.fellow.domain.model.PostCommentReply;
import com.fellow.domain.query.PostCommentReplyQuery;
import com.fellow.domain.vo.PostCommentReplyVo;

import java.util.List;

public interface PostCommentReplyService extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble, IncrementAble,CommentReplyAble {

    void reply(PostCommentReply postCommentReply);

    int updateReadStatus(PostCommentReply postCommentReply);

    List<PostCommentReplyVo> selectReplyMeComment(PostCommentReplyQuery postCommentReplyQuery);

    List<PostCommentReplyVo> selectReplyMeCommentAndSetHadRead(PostCommentReplyQuery postCommentReplyQuery);

    List<PostCommentReply> selectByPostIdAndReplyIds(PostCommentReplyQuery postCommentReplyQuery);

     int deleteCommentById(PostCommentReply postCommentReply);
}
