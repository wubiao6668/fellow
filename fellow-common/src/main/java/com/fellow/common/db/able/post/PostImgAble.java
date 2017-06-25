package com.fellow.common.db.able.post;

import com.fellow.domain.model.LostImg;
import com.fellow.domain.model.post.ImgDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by wubiao on 2017/1/12.
 */
public interface PostImgAble {

    List<ImgDomain> selectImgByPostId(@Param("postId") Long postId, @Param("imgType") Integer imgType);


    List<ImgDomain> selectImgByPostIdAndReplyIds(@Param("postId") Long postId, @Param("replyIdList") Set<Long> replyIdList , @Param("imgType") Integer imgType);
}
