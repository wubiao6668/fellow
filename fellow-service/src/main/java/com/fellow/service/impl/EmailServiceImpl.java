package com.fellow.service.impl;

import com.fellow.common.util.EmailUtil;
import com.fellow.domain.email.EmailInfo;
import com.fellow.service.EmailService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by wubiao on 2016/8/1.
 */
@Service
public class EmailServiceImpl implements EmailService {

    /**
     * SMTP发送服务器的名字
     */
    @Value("${email.hostName}")
    private String hostName;
    /**
     * 用户名
     */
    @Value("${email.userName}")
    private String userName;
    /**
     * 密码
     */
    @Value("${email.password}")
    private String password;
    /**
     * 发件人
     **/
    @Value("${email.fromAddr}")
    private String fromAddr;
    /**
     * 默认编码集
     */
    @Value("${email.newCharset}")
    private String newCharset;

    /**
     * 发送邮件
     * @param emailInfo
     */
    @Override
    public void sendMail(EmailInfo emailInfo) {
        setEmailDefault(emailInfo);
        EmailUtil.sendMail(emailInfo);
    }

    /**
     * 发送邮件
     * @param emailInfo
     */
    @Override
    public void sendMailByThreadPool(EmailInfo emailInfo) {
        setEmailDefault(emailInfo);
        EmailUtil.sendMailByThreadPool(emailInfo);
    }
    /**
     * 发送邮件并删除附件
     * @param emailInfo
     */
    @Override
    public void sendMailAndDelAttach(EmailInfo emailInfo) {
        setEmailDefault(emailInfo);
        EmailUtil.sendMailAndDelAttach(emailInfo);
    }

    private void setEmailDefault(EmailInfo emailInfo) {
        //SMTP发送服务器的名字
        if (StringUtils.isEmpty(emailInfo.getHostName())) {
            emailInfo.setHostName(hostName);
        }
        //用户名
        if (StringUtils.isEmpty(emailInfo.getUserName())) {
            emailInfo.setUserName(userName);
        }
        //密码
        if (StringUtils.isEmpty(emailInfo.getPassword())) {
            emailInfo.setPassword(password);
        }
        //发件人
        if (StringUtils.isEmpty(emailInfo.getFromAddr())) {
            emailInfo.setFromAddr(fromAddr);
        }
        //默认编码集
        if (StringUtils.isEmpty(emailInfo.getNewCharset())) {
            emailInfo.setNewCharset(newCharset);
        }
    }
}
