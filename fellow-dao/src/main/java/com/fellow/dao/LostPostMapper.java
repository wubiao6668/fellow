/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.common.db.able.post.PostAble;
import com.fellow.domain.model.LostPost;
import com.fellow.domain.query.LostPostQuery;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface LostPostMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble, IncrementAble,DecrementAble,PostAble {

    List<LostPost> selectLostPost(LostPostQuery lostPostQuery);

    long selectLostPostCount(LostPostQuery lostPostQuery);

    List<LostPost> selectPostTitleByIds(@Param("idList")Set<Long> idList);

    List<LostPost> selectMyPost(LostPostQuery query);

    @MapKey("id")
    Map<Long,LostPost> selectByIds(LostPostQuery query);

}