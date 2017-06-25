/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.service.impl;

import com.fellow.dao.ForgetPasswordMapper;
import com.fellow.domain.model.ForgetPassword;
import com.fellow.domain.query.ForgetPasswordQuery;
import com.fellow.service.EmailService;
import com.fellow.service.ForgetPasswordService;
import com.fellow.service.base.ServiceAbstract;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ForgetPasswordServiceImpl extends ServiceAbstract<ForgetPasswordMapper> implements ForgetPasswordService {

    @Resource
    private EmailService emailService;

    @Override
    public void sendEmail(ForgetPassword forgetPassword) {
        repository.deleteByEmail(forgetPassword);
        repository.insertSelective(forgetPassword);
        //发送邮件
        emailService.sendMailByThreadPool(forgetPassword.getEmailInfo());
    }

    @Override
    public int deleteByEmail(ForgetPassword forgetPassword) {
        return repository.deleteByEmail(forgetPassword);
    }

    @Override
    public ForgetPassword selectByEmailAndToken(ForgetPasswordQuery forgetPasswordQuery) {
        return repository.selectByEmailAndToken(forgetPasswordQuery);
    }
}
