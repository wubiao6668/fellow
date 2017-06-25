/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.service.impl;

import com.fellow.common.exception.BusinessException;
import com.fellow.dao.PersonalDynamicsCommentMapper;
import com.fellow.dao.PersonalDynamicsMapper;
import com.fellow.domain.enums.DynCommentTypeEnum;
import com.fellow.domain.enums.MessageReadEnum;
import com.fellow.domain.model.PersonalDynamics;
import com.fellow.domain.model.PersonalDynamicsComment;
import com.fellow.domain.query.PersonalDynamicsCommentQuery;
import com.fellow.domain.vo.PersonalDynamicsCommentVo;
import com.fellow.service.PersonalDynamicsCommentService;
import com.fellow.service.base.ServiceAbstract;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class PersonalDynamicsCommentServiceImpl extends ServiceAbstract<PersonalDynamicsCommentMapper> implements PersonalDynamicsCommentService {

    @Resource
    private PersonalDynamicsMapper personalDynamicsMapper;

    @Transactional(readOnly = true)
    @Override
    public List<PersonalDynamicsComment> selectPerDynCommentByPersonalId(PersonalDynamicsCommentQuery commentQuery) {
        return repository.selectPerDynCommentByPersonalId(commentQuery);
    }

    @Override
    public int commentDynReply(PersonalDynamicsComment personalDynamicsComment) {
        int row = personalDynamicsMapper.replyIncrement(personalDynamicsComment.getPersonalId());
        if (1 != row) {
            throw new BusinessException("回复失败！");
        }
        row = this.insertSelective(personalDynamicsComment);
        return row;
    }

    @Override
    public List<PersonalDynamicsCommentVo> selectPerDynCommentAndSetHadRead(PersonalDynamicsCommentQuery commentQuery) {
        List<PersonalDynamicsCommentVo> commentVoList = repository.selectPerDynComment(commentQuery);
        if (CollectionUtils.isNotEmpty(commentVoList)) {
            Set<Long> unReadIdSet = new HashSet<Long>();
            Set<Long> dynIdSet = new HashSet<Long>();
            Set<Long> commentIdSet = new HashSet<Long>();
            for (PersonalDynamicsCommentVo commentVoTemp : commentVoList) {
                if (session.getUserAccount().equals(commentVoTemp.getToAccount()) && commentVoTemp.getIsRead().intValue() == MessageReadEnum.UN_READ.getKey()) {
                    unReadIdSet.add(commentVoTemp.getId());
                }
                if (commentVoTemp.getCommentType().intValue() == DynCommentTypeEnum.DYNAMICS_REPLY.getKey()) {
                    dynIdSet.add(commentVoTemp.getPersonalId());
                } else if (commentVoTemp.getCommentType().intValue() == DynCommentTypeEnum.COMMENT_REPLY.getKey()) {
                    commentIdSet.add(commentVoTemp.getId());
                }
            }
            //查找动态
            Map<Long, PersonalDynamics> dynamicsMap = new HashMap<Long, PersonalDynamics>();
            if (CollectionUtils.isNotEmpty(dynIdSet)) {
                List<PersonalDynamics> dynamicsList = personalDynamicsMapper.selectContentByIds(dynIdSet);
                if (CollectionUtils.isNotEmpty(dynamicsList)) {
                    for (PersonalDynamics dynamicsTemp : dynamicsList) {
                        dynamicsMap.put(dynamicsTemp.getId(), dynamicsTemp);
                    }
                }
            }

            //查找评论
            Map<Long, PersonalDynamicsComment> commentMap = new HashMap<Long, PersonalDynamicsComment>();
            if (CollectionUtils.isNotEmpty(commentIdSet)) {
                List<PersonalDynamicsComment> commentList = repository.selectContentByIds(commentIdSet);
                if (CollectionUtils.isNotEmpty(commentList)) {
                    for (PersonalDynamicsComment commentTemp : commentList) {
                        commentMap.put(commentTemp.getId(), commentTemp);
                    }
                }
            }
            for (PersonalDynamicsCommentVo commentVoTemp : commentVoList) {
                if (commentVoTemp.getCommentType().intValue() == DynCommentTypeEnum.DYNAMICS_REPLY.getKey()) {
                    PersonalDynamics dynamics = dynamicsMap.get(commentVoTemp.getPersonalId());
                    if (null != dynamics) {
                        commentVoTemp.setOriginalContent(dynamics.getContent());
                    }
                } else if (commentVoTemp.getCommentType().intValue() == DynCommentTypeEnum.COMMENT_REPLY.getKey()) {
                    PersonalDynamicsComment dynamicsComment = commentMap.get(commentVoTemp.getId());
                    if (null != dynamicsComment) {
                        commentVoTemp.setOriginalContent(dynamicsComment.getContent());
                    }
                }
            }

            if (CollectionUtils.isNotEmpty(unReadIdSet)) {
                final PersonalDynamicsComment dynamicsComment = new PersonalDynamicsComment();
                dynamicsComment.setIdSet(unReadIdSet);
                dynamicsComment.setToAccount(session.getUserAccount());
                dynamicsComment.setIsRead(MessageReadEnum.HAD_READ.getKey());
                dynamicsComment.setUpdateAccount(session.getUserAccount());
                dynamicsComment.setUpdateName(session.getUserName());
                new Thread() {
                    @Override
                    public void run() {
                        repository.updateReadStatus(dynamicsComment);

                    }
                }.start();
            }
        }
        return commentVoList;
    }

    @Transactional
    @Override
    public int deleteDynCommentById(PersonalDynamicsComment personalDynamicsComment) {
        PersonalDynamicsComment commentDb = (PersonalDynamicsComment) repository.getByKey(personalDynamicsComment.getId());
        if (null == commentDb || (!session.getUserAccount().equals(commentDb.getFromAccount()) && !session.getUserAccount().equals(commentDb.getToAccount()))){
            throw new BusinessException("删除失败！");
        }
        int rows = repository.deleteById(personalDynamicsComment);
        if (1 != rows){
            throw new BusinessException("删除失败！请刷新后重试！");
        }
        rows =  personalDynamicsMapper.replyDecrement(commentDb.getPersonalId());
        if (1 != rows){
            throw new BusinessException("删除失败！请刷新后重试！");
        }
        return rows;
    }
}
