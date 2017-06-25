package com.fellow.service;


import com.fellow.domain.email.EmailInfo;

/**
 * Created by wubiao on 2016/8/1.
 */
public interface EmailService {
    /**
     * 发送邮件
     *
     * @param emailInfo
     */
    void sendMail(EmailInfo emailInfo);

    void sendMailByThreadPool(EmailInfo emailInfo);

    /**
     * 发送邮件并删除附件
     *
     * @param emailInfo
     */
    void sendMailAndDelAttach(EmailInfo emailInfo);

}
