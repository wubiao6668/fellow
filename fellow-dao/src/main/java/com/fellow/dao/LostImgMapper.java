/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.common.db.able.post.PostImgAble;
import org.springframework.stereotype.Repository;

@Repository
public interface LostImgMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble,PostImgAble {

//    List<LostImg> selectByPostIds(@Param("postIdList") List<Long> postIdList, @Param("imgType") Integer imgType);

//    List<LostImg> selectImgByPostId();


}