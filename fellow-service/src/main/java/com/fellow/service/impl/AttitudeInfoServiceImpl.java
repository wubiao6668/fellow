/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.service.impl;

import com.fellow.dao.AttitudeInfoMapper;
import com.fellow.domain.enums.AttitudeTypeEnum;
import com.fellow.domain.model.LostPost;
import com.fellow.domain.model.LostPostReply;
import com.fellow.domain.model.PostCommentReply;
import com.fellow.domain.query.AttitudeInfoQuery;
import com.fellow.domain.query.LostPostQuery;
import com.fellow.domain.query.LostPostReplyQuery;
import com.fellow.domain.query.PostCommentReplyQuery;
import com.fellow.domain.vo.AttitudeEntity;
import com.fellow.domain.vo.AttitudeInfoVo;
import com.fellow.service.AttitudeInfoService;
import com.fellow.service.LostPostReplyService;
import com.fellow.service.LostPostService;
import com.fellow.service.PostCommentReplyService;
import com.fellow.service.base.ServiceAbstract;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class AttitudeInfoServiceImpl extends ServiceAbstract<AttitudeInfoMapper> implements AttitudeInfoService {

    @Resource
    private LostPostService lostPostService;
    @Resource
    private LostPostReplyService lostPostReplyService;
    @Resource
    private PostCommentReplyService postCommentReplyService;

    @Override
    public List<AttitudeInfoVo> selectByToAccount(AttitudeInfoQuery attitudeInfoQuery) {
        List<AttitudeInfoVo> attitudeInfoList = repository.selectByToAccount(attitudeInfoQuery);
        return toFillEntity(attitudeInfoList);
    }

    private List<AttitudeInfoVo> toFillEntity(List<AttitudeInfoVo> attitudeInfoList) {
        if (CollectionUtils.isNotEmpty(attitudeInfoList)) {
            //帖子
            Set<Long> postIdSet = new HashSet<Long>();
            //评论
            Set<Long> commentIdSet = new HashSet<Long>();
            //回复
            Set<Long> replyIdSet = new HashSet<Long>();
            for (AttitudeInfoVo attitudeInfoTemp : attitudeInfoList) {
                if (AttitudeTypeEnum.POST.getKey() == attitudeInfoTemp.getType()) {
                    postIdSet.add(attitudeInfoTemp.getBusId());
                } else if (AttitudeTypeEnum.COMMENT.getKey() == attitudeInfoTemp.getType()) {
                    commentIdSet.add(attitudeInfoTemp.getBusId());
                } else if (AttitudeTypeEnum.REPLY.getKey() == attitudeInfoTemp.getType()) {
                    replyIdSet.add(attitudeInfoTemp.getBusId());
                }
            }
            Map<Long, LostPost> lostPostMap = null;
            if (CollectionUtils.isNotEmpty(postIdSet)) {
                LostPostQuery lostPostQuery = new LostPostQuery();
                lostPostQuery.setIdSet(postIdSet);
                lostPostMap = lostPostService.selectByIds(lostPostQuery);
            }
            Map<Long, LostPostReply> lostPostReplyMap = null;
            if (CollectionUtils.isNotEmpty(commentIdSet)) {
                LostPostReplyQuery replyQuery = new LostPostReplyQuery();
                replyQuery.setIdSet(commentIdSet);
                lostPostReplyMap = lostPostReplyService.selectByIds(replyQuery);
            }
            Map<Long, PostCommentReply> postCommentReplyMap = null;
            if (CollectionUtils.isNotEmpty(replyIdSet)) {
                PostCommentReplyQuery postCommentReplyQuery = new PostCommentReplyQuery();
                postCommentReplyQuery.setIdSet(replyIdSet);
                postCommentReplyMap = postCommentReplyService.selectByIds(postCommentReplyQuery);
            }
            AttitudeEntity attitudeEntity = null;
            for (AttitudeInfoVo attitudeInfoTemp : attitudeInfoList) {
                attitudeEntity = new AttitudeEntity();
                attitudeInfoTemp.setAttitudeEntity(attitudeEntity);
                if (AttitudeTypeEnum.POST.getKey() == attitudeInfoTemp.getType()) {
                    LostPost lostPostTemp = null;
                    if (MapUtils.isNotEmpty(lostPostMap) && null != (lostPostTemp = lostPostMap.get(attitudeInfoTemp.getBusId()))) {
                        attitudeEntity.setTitle(lostPostTemp.getTitle());
                        attitudeEntity.setContent(lostPostTemp.getBrief());
                        attitudeEntity.setPostId(lostPostTemp.getId());
                        attitudeEntity.setImgNum(lostPostTemp.getImgNum());
                        attitudeEntity.setImgList(lostPostTemp.getImgList());
                    }
                } else if (AttitudeTypeEnum.COMMENT.getKey() == attitudeInfoTemp.getType()) {
                    LostPostReply lostPostReplyTemp = null;
                    if (MapUtils.isNotEmpty(lostPostReplyMap) && null != (lostPostReplyTemp = lostPostReplyMap.get(attitudeInfoTemp.getBusId()))) {
                        attitudeEntity.setContent(lostPostReplyTemp.getContent());
                        attitudeEntity.setPostId(lostPostReplyTemp.getPostId());
                        attitudeEntity.setReplyId(lostPostReplyTemp.getId());
                        attitudeEntity.setImgNum(lostPostReplyTemp.getImgNum());
                        attitudeEntity.setImgList(lostPostReplyTemp.getImgList());
                    }
                } else if (AttitudeTypeEnum.REPLY.getKey() == attitudeInfoTemp.getType()) {
                    PostCommentReply postCommentReplyTemp = null;
                    if (MapUtils.isNotEmpty(postCommentReplyMap) && null != (postCommentReplyTemp = postCommentReplyMap.get(attitudeInfoTemp.getBusId()))) {
                        attitudeEntity.setContent(postCommentReplyTemp.getContent());
                        attitudeEntity.setPostId(postCommentReplyTemp.getPostId());
                        attitudeEntity.setReplyId(postCommentReplyTemp.getReplyId());
                        attitudeEntity.setCommentId(postCommentReplyTemp.getId());
                    }
                }
            }
        }
        return attitudeInfoList;
    }

    @Override
    public List<AttitudeInfoVo> selectByFromAccount(AttitudeInfoQuery attitudeInfoQuery) {
        List<AttitudeInfoVo> attitudeInfoList = repository.selectByFromAccount(attitudeInfoQuery);
        return toFillEntity(attitudeInfoList);
    }
}
