/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service;

import com.fellow.common.db.able.*;
import com.fellow.common.db.able.post.PostAble;
import com.fellow.domain.model.LostPost;
import com.fellow.domain.model.Post;
import com.fellow.domain.query.LostPostQuery;
import com.fellow.domain.query.PostQuery;

import java.util.List;
import java.util.Map;

public interface LostPostService extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble, IncrementAble, DecrementAble, PostAble {


    long selectLostPostCount(LostPostQuery lostPostQuery);

    //    List<LostPost> findPubishList(LostPostQuery lostPostQuery);
    List<LostPost> selectMyPost(LostPostQuery query);

    Map<Long,LostPost> selectByIds(LostPostQuery query);

}
