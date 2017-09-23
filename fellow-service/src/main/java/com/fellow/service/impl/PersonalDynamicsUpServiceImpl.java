/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.service.impl;

import com.fellow.dao.PersonalDynamicsMapper;
import com.fellow.dao.PersonalDynamicsUpMapper;
import com.fellow.domain.enums.AttitudeStatusEnum;
import com.fellow.domain.model.PersonalDynamics;
import com.fellow.domain.model.PersonalDynamicsUp;
import com.fellow.domain.query.PersonalDynamicsUpQuery;
import com.fellow.service.PersonalDynamicsUpService;
import com.fellow.service.base.ServiceAbstract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonalDynamicsUpServiceImpl extends ServiceAbstract<PersonalDynamicsUpMapper> implements PersonalDynamicsUpService {

    @Resource
    private PersonalDynamicsMapper personalDynamicsMapper;

    @Transactional(readOnly = true)
    @Override
    public PersonalDynamicsUp selectCommentByAccount(PersonalDynamicsUpQuery upQuery) {
        return repository.selectCommentByAccount(upQuery);
    }

    @Transactional
    @Override
    public void thumbsDynamicsComment(PersonalDynamicsUp dynamicsUpDto) {
        PersonalDynamicsUpQuery dynamicsUpQuery = new PersonalDynamicsUpQuery();
        dynamicsUpQuery.setPersonalId(dynamicsUpDto.getPersonalId());
        dynamicsUpQuery.setAccount(dynamicsUpDto.getAccount());
        PersonalDynamicsUp dynamicsUpDb = this.selectCommentByAccount(dynamicsUpQuery);
        int rows = 0;
        int upNum = 0;
        int loveNum = 0;
        if (dynamicsUpDto.getThumbsType().intValue() == AttitudeStatusEnum.UP.getKey()) {
            upNum = 1;
        }
        if (dynamicsUpDto.getThumbsType().intValue() == AttitudeStatusEnum.LOVE.getKey()) {
            loveNum = 1;
        }
        if (null == dynamicsUpDb) {
            if (dynamicsUpDto.getThumbsType().intValue() == AttitudeStatusEnum.CANCEL.getKey()) {
//                throw new BusinessException("请选择点赞或者送爱心！");
                return;
            }
            repository.insertSelective(dynamicsUpDto);
        } else {
            //重复操作
            if (dynamicsUpDto.getThumbsType().intValue() == dynamicsUpDb.getThumbsType().intValue()) {
//                throw new BusinessException("您已经" + AttitudeStatusEnum.getValueByKey(dynamicsUpDto.getThumbsType()) + "过了！");
                return;
            }
            if (dynamicsUpDb.getThumbsType().intValue() == AttitudeStatusEnum.UP.getKey()) {
                upNum = -1;
            }
            if (dynamicsUpDb.getThumbsType().intValue() == AttitudeStatusEnum.LOVE.getKey()) {
                loveNum = -1;
            }
            //修改
            PersonalDynamicsUp updateDynamicsUp = new PersonalDynamicsUp();
            updateDynamicsUp.setId(dynamicsUpDb.getId());
            updateDynamicsUp.setThumbsType(dynamicsUpDto.getThumbsType());
            repository.updateThumbsTypeById(updateDynamicsUp);
        }
        PersonalDynamics updateDynamics = new PersonalDynamics();
        updateDynamics.setId(dynamicsUpDto.getPersonalId());
        updateDynamics.setUpNum(upNum);
        updateDynamics.setLoveNum(loveNum);
        personalDynamicsMapper.updateUpAndLove(updateDynamics);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonalDynamicsUp> selectUpByPersonalId(PersonalDynamicsUpQuery dynamicsUpQuery) {
        return repository.selectUpByPersonalId(dynamicsUpQuery);
    }
}


























