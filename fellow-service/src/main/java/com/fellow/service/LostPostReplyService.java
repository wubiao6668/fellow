/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service;

import com.fellow.common.db.able.*;
import com.fellow.common.db.able.post.PostReplyAble;
import com.fellow.domain.model.LostPostReply;
import com.fellow.domain.query.LostPostReplyQuery;
import com.fellow.domain.vo.LostPostReplyVo;

import java.util.List;

public interface LostPostReplyService extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble, IncrementAble,DecrementAble,PostReplyAble {

//    void reply(LostPostReply lostPostReply);

    List<LostPostReplyVo> selectReplyAccount(LostPostReplyQuery replyQuery);

    long selectReplyAccountCount(LostPostReplyQuery replyQuery);
}
