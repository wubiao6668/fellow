/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service.impl;

import com.fellow.common.constant.SystemConstant;
import com.fellow.common.emotion.EmoticonUtil;
import com.fellow.common.util.VelocityUtil;
import com.fellow.dao.DynamicsImgMapper;
import com.fellow.dao.PersonalDynamicsCommentMapper;
import com.fellow.dao.PersonalDynamicsMapper;
import com.fellow.dao.PersonalDynamicsUpMapper;
import com.fellow.domain.base.BaseDomain;
import com.fellow.domain.enums.DynamicsPropertyEnum;
import com.fellow.domain.enums.PostImgTypeEnum;
import com.fellow.domain.model.DynamicsImg;
import com.fellow.domain.model.PersonalDynamics;
import com.fellow.domain.model.PersonalDynamicsComment;
import com.fellow.domain.model.PersonalDynamicsUp;
import com.fellow.domain.mybatis.PageListImpl;
import com.fellow.domain.query.PersonalDynamicsCommentQuery;
import com.fellow.domain.query.PersonalDynamicsQuery;
import com.fellow.domain.query.PersonalDynamicsUpQuery;
import com.fellow.service.PersonalDynamicsService;
import com.fellow.service.base.ServiceAbstract;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PersonalDynamicsServiceImpl extends ServiceAbstract<PersonalDynamicsMapper> implements PersonalDynamicsService {

    @Resource
    private DynamicsImgMapper dynamicsImgMapper;
    @Resource
    private PersonalDynamicsCommentMapper personalDynamicsCommentMapper;
    @Resource
    private PersonalDynamicsUpMapper personalDynamicsUpMapper;

    @Override
    public void publishDynamics(PersonalDynamics personalDynamics) {
        repository.insertSelective(personalDynamics);
        if (CollectionUtils.isNotEmpty(personalDynamics.getDynamicsImgList())) {
            for (DynamicsImg imgTemp : personalDynamics.getDynamicsImgList()) {
                imgTemp.setPersonalId(personalDynamics.getId());
                dynamicsImgMapper.insertSelective(imgTemp);
            }
        }
    }

    @Transactional(readOnly = true)
    @Override
    public PageListImpl<BaseDomain> loadPersonalDynamic(PersonalDynamicsQuery personalDynamicsQuery, PageBounds pageBounds) {
        PageListImpl<BaseDomain> dynamicsPageList = findList(personalDynamicsQuery, pageBounds);
        if (CollectionUtils.isNotEmpty(dynamicsPageList)) {
            VelocityEngine velocityEngine = null;
            Writer postTextWriter = null;
            Set<Long> idList = new HashSet<Long>();
            for (BaseDomain baseDomainTemp : dynamicsPageList) {
                PersonalDynamics personalDynamicsTemp = (PersonalDynamics) baseDomainTemp;
                idList.add(personalDynamicsTemp.getId());
                try {
                    velocityEngine = new VelocityEngine();
                    postTextWriter = new StringWriter();
                    velocityEngine.evaluate(EmoticonUtil.context, postTextWriter, "", personalDynamicsTemp.getContent());
                    personalDynamicsTemp.setContent(postTextWriter.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                personalDynamicsTemp.setDynamicsImgList(new ArrayList<DynamicsImg>());
            }
            if (CollectionUtils.isNotEmpty(idList)) {
                List<DynamicsImg> dynamicsImgList = dynamicsImgMapper.findListByPersonalIds(idList, PostImgTypeEnum.POST.getKey());
                if (CollectionUtils.isNotEmpty(dynamicsImgList)) {
                    for (DynamicsImg dynamicsImgTemp : dynamicsImgList) {
                        for (BaseDomain baseDomainTemp : dynamicsPageList) {
                            PersonalDynamics personalDynamicsTemp = (PersonalDynamics) baseDomainTemp;
                            if (personalDynamicsTemp.getId().longValue() == dynamicsImgTemp.getPersonalId().longValue()) {
                                personalDynamicsTemp.getDynamicsImgList().add(dynamicsImgTemp);
                                break;
                            }
                        }
                    }
                }
            }
        }

        return dynamicsPageList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonalDynamics> selectDynamics(PersonalDynamicsQuery dynamicsQuery) {
        List<PersonalDynamics> personalDynamicsList = null;
        if (DynamicsPropertyEnum.FRIEND.getKey() == dynamicsQuery.getDynamicsProperty()) {
            personalDynamicsList = repository.selectDynamics(dynamicsQuery);
        } else {
            personalDynamicsList = repository.selectPersonalDynamics(dynamicsQuery);
        }

        if (CollectionUtils.isEmpty(personalDynamicsList)) {
            return personalDynamicsList;
        }
        Set<Long> idList = new HashSet<Long>();
        PersonalDynamicsCommentQuery commentQuery = new PersonalDynamicsCommentQuery();
        commentQuery.setPageSize(SystemConstant.COMMENT_GROUP_NUM);
        VelocityEngine velocityEngine = new VelocityEngine();
        //本人点赞查询
        PersonalDynamicsUpQuery personalDynamicsUpQuery = new PersonalDynamicsUpQuery();
        personalDynamicsUpQuery.setAccount(dynamicsQuery.getAccount());
        personalDynamicsUpQuery.setPersonalIdList(new ArrayList<Long>());
        for (PersonalDynamics dynamicsTemp : personalDynamicsList) {
            if (dynamicsTemp.getImgNum() > 0) {
                idList.add(dynamicsTemp.getId());
            }
            //查询评论
            if (dynamicsTemp.getReplyNum() > 0) {
                commentQuery.setPersonalId(dynamicsTemp.getId());
                commentQuery.setSortColumns(" id desc");
                commentQuery.initMysqlLimit();
                List<PersonalDynamicsComment> commentList = personalDynamicsCommentMapper.selectPerDynCommentByPersonalId(commentQuery);
                if (CollectionUtils.isNotEmpty(commentList)) {
                    for (PersonalDynamicsComment commentTemp : commentList) {
                        commentTemp.setContent(VelocityUtil.mergeEmoticon(velocityEngine, commentTemp.getContent()));
                    }
                }
                dynamicsTemp.setPersonalDynamicsCommentList(commentList);
            }
            dynamicsTemp.setContent(VelocityUtil.mergeEmoticon(dynamicsTemp.getContent()));
            if (dynamicsTemp.getUpNum().intValue() > 0 || dynamicsTemp.getLoveNum().intValue() > 0) {
                personalDynamicsUpQuery.getPersonalIdList().add(dynamicsTemp.getId());
            }
        }
        //图片
        fillImage(personalDynamicsList, idList);
        //点赞
        fillThumbs(personalDynamicsList, personalDynamicsUpQuery);
        return personalDynamicsList;
    }

    private void fillImage(List<PersonalDynamics> personalDynamicsList, Set<Long> idList) {
        if (CollectionUtils.isNotEmpty(idList)) {
            List<DynamicsImg> dynamicsImgList = dynamicsImgMapper.findListByPersonalIds(idList, PostImgTypeEnum.POST.getKey());
            if (CollectionUtils.isNotEmpty(dynamicsImgList)) {
                for (DynamicsImg dynamicsImgTemp : dynamicsImgList) {
                    for (BaseDomain baseDomainTemp : personalDynamicsList) {
                        PersonalDynamics personalDynamicsTemp = (PersonalDynamics) baseDomainTemp;
                        if (personalDynamicsTemp.getId().longValue() == dynamicsImgTemp.getPersonalId().longValue()) {
                            personalDynamicsTemp.getDynamicsImgList().add(dynamicsImgTemp);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void fillThumbs(List<PersonalDynamics> personalDynamicsList, PersonalDynamicsUpQuery personalDynamicsUpQuery) {
        if (CollectionUtils.isEmpty(personalDynamicsUpQuery.getPersonalIdList())) {
            return;
        }
        List<PersonalDynamicsUp> personalDynamicsUpList = personalDynamicsUpMapper.selectCommentByPidAndAccount(personalDynamicsUpQuery);
        if (CollectionUtils.isEmpty(personalDynamicsUpList)) {
            return;
        }
        for (PersonalDynamics dynamicsTemp : personalDynamicsList) {
            for (PersonalDynamicsUp dynamicsUpTemp : personalDynamicsUpList) {
                if (dynamicsTemp.getId().longValue() == dynamicsUpTemp.getPersonalId().longValue()) {
                    dynamicsTemp.setPersonalDynamicsUp(dynamicsUpTemp);
                }
            }
        }
    }

    @Transactional(readOnly = true)
    @Override
    public long selectDynamicsCount(PersonalDynamicsQuery dynamicsQuery) {
        return repository.selectDynamicsCount(dynamicsQuery);
    }
}



















