/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.common.db.able.post.CommentReplyAble;
import com.fellow.domain.model.PostCommentReply;
import com.fellow.domain.query.PostCommentReplyQuery;
import com.fellow.domain.vo.PostCommentReplyVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface PostCommentReplyMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble, IncrementAble,CommentReplyAble {

    List<PostCommentReply> selectByPostIdAndReplyId(PostCommentReplyQuery postCommentReplyQuery);

    int updateReadStatus(PostCommentReply postCommentReply);

    List<PostCommentReplyVo> selectReplyMeComment(PostCommentReplyQuery postCommentReplyQuery);

    List<PostCommentReply> selectContentByIds(@Param("idSet")Set<Long> idSet);

    @MapKey("id")
    Map<Long,PostCommentReply> selectByIds(PostCommentReplyQuery postCommentReplyQuery);

    int deleteById(PostCommentReply postCommentReply);

    int updateAttitudeInfo(PostCommentReply postCommentReply);


}