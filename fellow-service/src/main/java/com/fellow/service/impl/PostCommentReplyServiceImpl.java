/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service.impl;

import com.fellow.common.exception.BusinessException;
import com.fellow.common.util.VelocityUtil;
import com.fellow.dao.*;
import com.fellow.domain.enums.MessageReadEnum;
import com.fellow.domain.enums.ReplyTypeEnum;
import com.fellow.domain.model.LostPostReply;
import com.fellow.domain.model.PostCommentReply;
import com.fellow.domain.model.post.ImgDomain;
import com.fellow.domain.query.PostCommentReplyQuery;
import com.fellow.domain.session.LocalSession;
import com.fellow.domain.session.Session;
import com.fellow.domain.vo.PostCommentReplyVo;
import com.fellow.service.PostCommentReplyService;
import com.fellow.service.base.PostServiceAbstract;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class PostCommentReplyServiceImpl extends PostServiceAbstract<PostCommentReplyMapper, LostPostMapper, LostPostDetailMapper, LostPostReplyMapper, LostImgMapper> implements PostCommentReplyService {
    @Resource
    private LostPostReplyMapper lostPostReplyMapper;

    @Transactional
    @Override
    public void reply(PostCommentReply postCommentReply) {
        lostPostReplyMapper.replyIncrement(postCommentReply.getReplyId());
        repository.insertSelective(postCommentReply);
    }

    @Transactional
    @Override
    public int updateReadStatus(PostCommentReply postCommentReply) {
        return repository.updateReadStatus(postCommentReply);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostCommentReplyVo> selectReplyMeComment(PostCommentReplyQuery postCommentReplyQuery) {
        return repository.selectReplyMeComment(postCommentReplyQuery);
    }

    @Override
    public List<PostCommentReplyVo> selectReplyMeCommentAndSetHadRead(PostCommentReplyQuery postCommentReplyQuery) {
        Session session = LocalSession.getSession();
        List<PostCommentReplyVo> postCommentReplyList = repository.selectReplyMeComment(postCommentReplyQuery);
        Set<Long> unReadIdSet = new HashSet<Long>();
        Set<Long> postReplyIdSet = new HashSet<Long>();
        Set<Long> commentReplyIdSet = new HashSet<Long>();
        Set<Long> imgReplyIdSet = new HashSet<Long>();
        if (CollectionUtils.isNotEmpty(postCommentReplyList)) {
            for (PostCommentReplyVo replyTemp : postCommentReplyList) {
                if (session.getUserAccount().equals(replyTemp.getToAccount()) && replyTemp.getIsRead().intValue() == MessageReadEnum.UN_READ.getKey()) {
                    unReadIdSet.add(replyTemp.getId());
                }
                if (replyTemp.getReplyType().intValue() == ReplyTypeEnum.POST_REPLY.getKey()) {
                    postReplyIdSet.add(replyTemp.getReplyId());
                } else if (replyTemp.getReplyType().intValue() == ReplyTypeEnum.COMMENT_REPLY.getKey()) {
                    commentReplyIdSet.add(replyTemp.getCommentReplyId());
                }
            }
        }

        Map<Long, LostPostReply> postReplyMap = new HashMap<Long, LostPostReply>();
        if (CollectionUtils.isNotEmpty(postReplyIdSet)) {
            //查找回复
            List<LostPostReply> postReplyList = lostPostReplyMapper.selectContentByIds(postReplyIdSet);
            if (CollectionUtils.isNotEmpty(postReplyList)) {
                for (LostPostReply replyTemp : postReplyList) {
                    postReplyMap.put(replyTemp.getId(), replyTemp);
                    if (replyTemp.getImgNum() > 0) {
                        imgReplyIdSet.add(replyTemp.getId());
                    }
                }
            }
        }
        Map<Long, List<ImgDomain>> imgDomainListMap = new HashMap<Long, List<ImgDomain>>();
        //查找图片
        if (CollectionUtils.isNotEmpty(imgReplyIdSet)) {
            List<ImgDomain> imgDomainList = imgRepository.selectImgByPostIdAndReplyIds(null, imgReplyIdSet, null);
            if (CollectionUtils.isNotEmpty(imgDomainList)) {
                for (ImgDomain imgDomainTemp : imgDomainList) {
                    List<ImgDomain> imgDomainMapList = imgDomainListMap.get(imgDomainTemp.getReplyId());
                    if (CollectionUtils.isEmpty(imgDomainMapList)) {
                        imgDomainMapList = new ArrayList<ImgDomain>();
                    }
                    imgDomainMapList.add(imgDomainTemp);
                    imgDomainListMap.put(imgDomainTemp.getReplyId(), imgDomainMapList);
                }
            }
        }

        Map<Long, PostCommentReply> commentReplyMap = new HashMap<Long, PostCommentReply>();
        if (CollectionUtils.isNotEmpty(commentReplyIdSet)) {
            //查找回复评论
            List<PostCommentReply> commentRepyList = repository.selectContentByIds(commentReplyIdSet);
            if (CollectionUtils.isNotEmpty(commentRepyList)) {
                for (PostCommentReply commentReplyTemp : commentRepyList) {
                    commentReplyMap.put(commentReplyTemp.getId(), commentReplyTemp);
                }
            }
        }
        //设置ti
        if (CollectionUtils.isNotEmpty(postCommentReplyList)) {
            for (PostCommentReplyVo replyTemp : postCommentReplyList) {
                try {
                    if (replyTemp.getReplyType().intValue() == ReplyTypeEnum.POST_REPLY.getKey()) {
                        LostPostReply replyTempMap = postReplyMap.get(replyTemp.getReplyId());
                        if (null != replyTempMap && StringUtils.isNotBlank(replyTempMap.getContent())) {
                            replyTemp.setOriginalContent(VelocityUtil.mergeEmoticon(replyTempMap.getContent()));
                            replyTemp.setOriginalSendDate(replyTempMap.getReplyTime());
                            replyTemp.setImgDomainList(imgDomainListMap.get(replyTempMap.getId()));
                        }
                    } else if (replyTemp.getReplyType().intValue() == ReplyTypeEnum.COMMENT_REPLY.getKey()) {
                        PostCommentReply replyTempMap = commentReplyMap.get(replyTemp.getReplyId());
                        if (null != replyTempMap && StringUtils.isNotBlank(replyTempMap.getContent())) {
                            replyTemp.setOriginalContent(VelocityUtil.mergeEmoticon(replyTempMap.getContent()));
                            replyTemp.setOriginalSendDate(replyTempMap.getReplyTime());
                        }
                    }
                } catch (Exception e) {
                    log.error("selectReplyMeCommentAndSetHadRead--表情异常--e=", e);
                }
            }
        }

        //设置消息为已读
        if (CollectionUtils.isNotEmpty(unReadIdSet)) {
            final PostCommentReply updateCommentToRead = new PostCommentReply();
            updateCommentToRead.setIdSet(unReadIdSet);
            updateCommentToRead.setToAccount(session.getUserAccount());
            updateCommentToRead.setIsRead(MessageReadEnum.HAD_READ.getKey());
//        updateCommentToRead.setReadStatus(MessageReadEnum.UN_READ.getKey());
            updateCommentToRead.setUpdateAccount(session.getUserAccount());
            updateCommentToRead.setUpdateName(session.getUserName());
            new Thread() {
                @Override
                public void run() {
                    repository.updateReadStatus(updateCommentToRead);
                }
            }.start();
        }
        return postCommentReplyList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostCommentReply> selectByPostIdAndReplyIds(PostCommentReplyQuery postCommentReplyQuery) {
        return repository.selectByPostIdAndReplyId(postCommentReplyQuery);
    }

    @Transactional
    @Override
    public int deleteCommentById(PostCommentReply postCommentReply) {
        int rows = 0;
        PostCommentReply postCommentReplyDb = (PostCommentReply) repository.getByKey(postCommentReply.getId());
        if (null == postCommentReplyDb || (!postCommentReplyDb.getFromAccount().equals(postCommentReply.getUpdateAccount())) && !postCommentReplyDb.getToAccount().equals(postCommentReply.getUpdateAccount())) {
            throw new BusinessException("删除失败！");
        }
        rows = repository.deleteById(postCommentReply);
        if (1 != rows) {
            throw new BusinessException("删除失败！请刷新后重试！");
        }
        rows = replyRepository.downIncrement(postCommentReplyDb.getReplyId());
        if (1 != rows) {
            throw new BusinessException("删除失败！请刷新后重试！");
        }
        return rows;
    }


    @Override
    public Map<Long, PostCommentReply> selectByIds(PostCommentReplyQuery postCommentReplyQuery) {
        Map<Long, PostCommentReply> postCommentReplyMap = repository.selectByIds(postCommentReplyQuery);
        if (MapUtils.isNotEmpty(postCommentReplyMap)){
            for (PostCommentReply replyTemp : postCommentReplyMap.values()){
                replyTemp.setContent(VelocityUtil.mergeEmoticon(replyTemp.getContent()));
            }
        }
        return postCommentReplyMap;
    }

    @Override
    public int updateAttitudeInfo(PostCommentReply postCommentReply) {
        return repository.updateAttitudeInfo(postCommentReply);
    }
}













