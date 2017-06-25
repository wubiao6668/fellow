package com.fellow.service.base;

import com.fellow.common.constant.SystemConstant;
import com.fellow.common.db.able.DecrementAble;
import com.fellow.common.db.able.IncrementAble;
import com.fellow.common.db.able.InsertSelectiveAble;
import com.fellow.common.db.able.post.*;
import com.fellow.common.exception.BusinessException;
import com.fellow.common.util.VelocityUtil;
import com.fellow.dao.PostCommentReplyMapper;
import com.fellow.domain.base.QueryDomain;
import com.fellow.domain.enums.PostEnum;
import com.fellow.domain.enums.PostImgTypeEnum;
import com.fellow.domain.model.LostImg;
import com.fellow.domain.model.PostCommentReply;
import com.fellow.domain.model.post.ImgDomain;
import com.fellow.domain.model.post.PostDetailDomain;
import com.fellow.domain.model.post.PostDomain;
import com.fellow.domain.model.post.PostReplyDomain;
import com.fellow.domain.query.PostCommentReplyQuery;
import com.fellow.domain.query.post.PostReplyQuery;
import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wubiao on 2017/1/13.
 */
public abstract class PostServiceAbstract<R, PostR, DetailR, ReplyR, ImgR> extends ServiceAbstract<R> {

    @Autowired
    protected PostR postRepository;
    @Autowired
    protected DetailR detailRepository;
    @Autowired
    protected ReplyR replyRepository;
    @Autowired
    protected ImgR imgRepository;
    @Resource
    protected PostCommentReplyMapper postCommentReplyMapper;


    @Transactional
    public void reply(PostReplyDomain lostPostReply) {
        ((IncrementAble) postRepository).replyIncrement(lostPostReply.getPostId());
        ((InsertSelectiveAble) repository).insertSelective(lostPostReply);
        if (CollectionUtils.isNotEmpty(lostPostReply.getImgList())) {
            for (ImgDomain imgTemp : lostPostReply.getImgList()) {
                imgTemp.setReplyId(lostPostReply.getId());
                ((InsertSelectiveAble) imgRepository).insertSelective((LostImg) imgTemp);
            }
        }
    }


    @Transactional(readOnly = true)
    public List<PostDomain> selectPost(QueryDomain q) {
        List<PostDomain> postDomainList = ((PostAble) repository).selectPost(q);
        if (CollectionUtils.isNotEmpty(postDomainList)) {
            for (PostDomain postDomain : postDomainList) {
                if (null != postDomain.getImgNum() && 0 < postDomain.getImgNum()) {
                    List<ImgDomain> imgDomainList = ((PostImgAble) imgRepository).selectImgByPostId(postDomain.getId(), PostImgTypeEnum.POST.getKey());
                    postDomain.setImgList(imgDomainList);
                }
            }
        }
        return postDomainList;
    }

    @Transactional(readOnly = true)
    public long selectPostCount(QueryDomain q) {
        return ((PostAble) repository).selectPostCount(q);
    }

    public PostDetailDomain selectDetailByPostId(Long postId) {
        return ((PostDetailAble) repository).selectDetailByPostId(postId);
    }

    public List<ImgDomain> selectImgByPostId(Long postId, Integer imgType) {
        return ((PostImgAble) repository).selectImgByPostId(postId, imgType);
    }

    public List<PostReplyDomain> queryReply(PostReplyQuery queryDomain) {
        List<PostReplyDomain> postReplyList = ((PostReplyAble) repository).queryReply(queryDomain);
        if (CollectionUtils.isNotEmpty(postReplyList)) {
            Set<Long> replyImgIdList = new HashSet<Long>();
            List<Long> commonIdList = new ArrayList<Long>();
            VelocityEngine velocityEngine = null;
            Writer postTextWriter = null;
            for (PostReplyDomain replyTemp : postReplyList) {
                try {
                    //表情
                    replyTemp.setContent(VelocityUtil.mergeEmoticon(velocityEngine, replyTemp.getContent()));
                } catch (Exception e) {
                    log.error("queryReply--回复表情异常--e", e);
                }
                replyImgIdList.add(replyTemp.getId());
                commonIdList.add(replyTemp.getId());
                if (replyTemp.getReplyNum().intValue() > 0) {
                    //查询详细
                    PostCommentReplyQuery replyQuery = new PostCommentReplyQuery();
                    replyQuery.setPostId(replyTemp.getPostId());
                    replyQuery.setReplyId(replyTemp.getId());
                    replyQuery.setPostType(PostEnum.LOST_POST.getKey());
                    replyQuery.setPageSize(SystemConstant.COMMENT_GROUP_NUM);
                    replyQuery.setSortColumns(" id desc");
                    replyQuery.initMysqlLimit();
                    //回复评论查找
                    List<PostCommentReply> commentReplyList = postCommentReplyMapper.selectByPostIdAndReplyId(replyQuery);
                    if (CollectionUtils.isNotEmpty(commentReplyList)) {
                        for (PostCommentReply commentReplyTemp : commentReplyList) {
                            velocityEngine = new VelocityEngine();
                            try {
                                //表情
                                commentReplyTemp.setContent(VelocityUtil.mergeEmoticon(velocityEngine, commentReplyTemp.getContent()));
                            } catch (Exception e) {
                                log.error("queryReply--评论回复表情异常--e", e);
                            }
                        }
                        replyTemp.setPostCommentReplyList(commentReplyList);
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(replyImgIdList)) {
                List<ImgDomain> imgList = ((PostImgAble) imgRepository).selectImgByPostIdAndReplyIds(queryDomain.getPostId(), replyImgIdList, PostImgTypeEnum.REPLY.getKey());
                if (CollectionUtils.isNotEmpty(imgList)) {
                    for (ImgDomain imgTemp : imgList) {
                        for (PostReplyDomain replyTemp : postReplyList) {
                            if (imgTemp.getReplyId().longValue() == replyTemp.getId().longValue()) {
                                if (CollectionUtils.isEmpty(replyTemp.getImgList())) {
                                    replyTemp.setImgList(new ArrayList<ImgDomain>());
                                }
                                replyTemp.getImgList().add(imgTemp);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return postReplyList;
    }

    @Transactional(readOnly = true)
    public long queryReplyCount(PostReplyQuery postReplyQuery) {
        return ((PostReplyAble) repository).queryReplyCount(postReplyQuery);
    }

    public List<ImgDomain> selectImgByPostIdAndReplyIds(Long postId, Set<Long> replyIdList, Integer imgType) {
        return ((PostImgAble) imgRepository).selectImgByPostIdAndReplyIds(postId,replyIdList,imgType);
    }

    public int deleteReplyByAccount(PostReplyDomain postReplyDomain) {
        int rows = ((PostReplyAble) repository).deleteReplyByAccount(postReplyDomain);
        if (1 != rows){
            throw new BusinessException("删除失败！请刷新页面后重试！");
        }
        rows = ((DecrementAble) postRepository).replyDecrement(postReplyDomain.getPostId());
        if (1 != rows){
            throw new BusinessException("删除失败！请刷新页面后重试！");
        }
        postCommentReplyMapper.deleteReplyByReplyId(postReplyDomain.getPostCommentReply());
        return rows;
    }

    public int replyDecrement(Number key) {
        int rows = ((DecrementAble) postRepository).replyDecrement(key);
        return rows;
    }

    public int deleteReplyById(PostCommentReply postCommentReply) {
         int rows =((CommentReplyAble) repository).deleteReplyById(postCommentReply);
        if (1 != rows){
            throw new BusinessException("删除失败！请刷新页面后重试！");
        }
        return rows;
    }

    public int deleteReplyByReplyId(PostCommentReply postCommentReply) {
        return postCommentReplyMapper.deleteReplyByReplyId(postCommentReply);
    }
    @Transactional
    public int replyIncrement(Number key) {
        return ((IncrementAble) repository).replyIncrement(key);
    }

    @Transactional
    public int upIncrement(Number key) {
        return ((IncrementAble) repository).upIncrement(key);
    }

    @Transactional
    public int downIncrement(Number key) {
        return ((IncrementAble) repository).downIncrement(key);
    }

    @Transactional
    public int viewIncrement(Number key) {
        return ((IncrementAble) repository).viewIncrement(key);
    }

    @Transactional
    public int loveIncrement(Number key) {
        return ((IncrementAble) repository).loveIncrement(key);
    }

    @Transactional
    public void publish(PostDomain postDomain) {
        ((InsertSelectiveAble) postRepository).insertSelective(postDomain);
        postDomain.getPostDetailDomain().setPostId(postDomain.getId());
        ((InsertSelectiveAble) detailRepository).insertSelective(postDomain.getPostDetailDomain());
        if (CollectionUtils.isNotEmpty(postDomain.getImgList())) {
            for (ImgDomain imgTemp : postDomain.getImgList()) {
                imgTemp.setPostId(postDomain.getId());
                ((InsertSelectiveAble) imgRepository).insertSelective(imgTemp);
            }
        }
    }


}
