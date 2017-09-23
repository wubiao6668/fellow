/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.common.db.able.post.PostReplyAble;
import com.fellow.domain.model.LostPostReply;
import com.fellow.domain.query.LostPostReplyQuery;
import com.fellow.domain.vo.LostPostReplyVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface LostPostReplyMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble, IncrementAble, DecrementAble, PostReplyAble {

//    List<LostPostReply> queryReply(LostPostReplyQuery );

//    List<MessageReply> selectByPostIdAndReplyId(MessageReplyQuery messageReplyQuery);

    List<LostPostReplyVo> selectReplyAccount(LostPostReplyQuery replyQuery);

    long selectReplyAccountCount(LostPostReplyQuery replyQuery);

    List<LostPostReply> selectContentByIds(@Param("idSet") Set<Long> idSet);

    @MapKey("id")
    Map<Long,LostPostReply> selectByIds(LostPostReplyQuery lostPostReplyQuery);
}