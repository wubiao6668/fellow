/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.service.impl;

import com.fellow.dao.AttitudeInfoMapper;
import com.fellow.domain.enums.AttitudeStatusEnum;
import com.fellow.domain.enums.AttitudeTypeEnum;
import com.fellow.domain.enums.IsDeleteEnum;
import com.fellow.domain.model.AttitudeInfo;
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

    @Override
    public boolean myAttitude(AttitudeInfo attitudeInfo) {
        AttitudeInfoQuery attitudeInfoQuery = new AttitudeInfoQuery();
        attitudeInfoQuery.setBusId(attitudeInfo.getBusId());
        attitudeInfoQuery.setType(attitudeInfo.getType());
        AttitudeInfo dbAttitudeInfo = repository.selectByFromAccountAndBusIdAndType(attitudeInfoQuery);
        //两种情况不考虑 1、状态跟现在的相同 2、直接取消
        if (null != dbAttitudeInfo
                && (dbAttitudeInfo.getStatus().intValue() == attitudeInfo.getStatus().intValue()
                || (null == dbAttitudeInfo && AttitudeStatusEnum.CANCEL.getKey() == attitudeInfo.getStatus().intValue()))) {
            return false;
        }
        Integer upNum = null;
        Integer downNum = null;
        Integer loveNum = null;
        if (AttitudeStatusEnum.UP.getKey() == attitudeInfo.getStatus().intValue()) {
            upNum = 1;
        } else if (AttitudeStatusEnum.DOWN.getKey() == attitudeInfo.getStatus().intValue()) {
            downNum = 1;
        } else if (AttitudeStatusEnum.LOVE.getKey() == attitudeInfo.getStatus().intValue()) {
            loveNum = 1;
        }
        if (null != dbAttitudeInfo) {
            if (AttitudeStatusEnum.UP.getKey() == dbAttitudeInfo.getStatus().intValue() && attitudeInfo.getStatus().intValue() == AttitudeStatusEnum.CANCEL.getKey()) {
                upNum = -1;
            } else if (AttitudeStatusEnum.DOWN.getKey() == dbAttitudeInfo.getStatus().intValue() && attitudeInfo.getStatus().intValue() == AttitudeStatusEnum.CANCEL.getKey()) {
                downNum = -1;
            } else if (AttitudeStatusEnum.LOVE.getKey() == dbAttitudeInfo.getStatus().intValue() && attitudeInfo.getStatus().intValue() == AttitudeStatusEnum.CANCEL.getKey()) {
                loveNum = -1;
            }
        }
        Integer delete = IsDeleteEnum.NO.getKey();
        if (AttitudeTypeEnum.POST.getKey() == attitudeInfo.getType().intValue()) {
            LostPost lostPostDb = (LostPost) lostPostService.getByKey(attitudeInfo.getBusId());
            if (null == lostPostDb) {
                return false;
            }
            delete = lostPostDb.getIsDelete();
            LostPost lostPost = new LostPost();
            lostPost.setUpNum(upNum);
            lostPost.setDownNum(downNum);
            lostPost.setLoveNum(loveNum);
            lostPost.setId(lostPostDb.getId());
            int row = lostPostService.updateAttitudeInfo(lostPost);
            if (1 != row) {
                return false;
            }
        } else if (AttitudeTypeEnum.COMMENT.getKey() == attitudeInfo.getType().intValue()) {
            LostPostReply dbLostPostReply = (LostPostReply) lostPostReplyService.getByKey(attitudeInfo.getBusId());
            if (null == dbLostPostReply) {
                return false;
            }
            delete = dbLostPostReply.getIsDelete();
            LostPostReply lostPostReply = new LostPostReply();
            lostPostReply.setUpNum(upNum);
            lostPostReply.setDownNum(downNum);
            lostPostReply.setLoveNum(loveNum);
            lostPostReply.setId(dbLostPostReply.getId());
            int row = lostPostReplyService.updateAttitudeInfo(lostPostReply);
            if (1 != row) {
                return false;
            }
        } else if (AttitudeTypeEnum.COMMENT.getKey() == attitudeInfo.getType().intValue()) {
            PostCommentReply dbPostCommentReply = (PostCommentReply) postCommentReplyService.getByKey(attitudeInfo.getBusId());
            if (null == dbPostCommentReply) {
                return false;
            }
            delete = dbPostCommentReply.getIsDelete();
            PostCommentReply postCommentReply = new PostCommentReply();
            postCommentReply.setUpNum(upNum);
            postCommentReply.setDownNum(downNum);
            postCommentReply.setLoveNum(loveNum);
            postCommentReply.setId(dbPostCommentReply.getId());
            int row = postCommentReplyService.updateAttitudeInfo(postCommentReply);
            if (1 != row) {
                return false;
            }
        } else {
            return false;
        }
        if (null != dbAttitudeInfo) {
            attitudeInfo.setId(dbAttitudeInfo.getId());
            attitudeInfo.setIsDelete(delete);
            repository.updateStatusById(attitudeInfo);
        } else {
            repository.insertSelective(attitudeInfo);
        }
        return true;
    }
}





















































