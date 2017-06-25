package com.fellow.common.util;

import com.fellow.common.exception.BusinessException;
import com.fellow.domain.email.EmailInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wubiao on 2016/8/1.
 */
public class EmailUtil {
    private static Log log = LogFactory.getLog(EmailUtil.class);

    private static ExecutorService asyncThreadPool = Executors.newFixedThreadPool(2);

    /**
     * 邮件发送
     *
     * @param emailInfo
     */
    public static void sendMail(EmailInfo emailInfo) {
        log.info("sendMail--开始发送邮件--businessCode=" + emailInfo.getBusinessCode());
        HtmlEmail email = new HtmlEmail();
        try {
            // 字符编码集的设置
            if (org.apache.commons.lang.StringUtils.isNotEmpty(emailInfo.getNewCharset())) {
                email.setCharset(emailInfo.getNewCharset());
            }
            //SMTP发送服务器
            email.setHostName(emailInfo.getHostName());
            // 发送人的邮箱
            email.setFrom(emailInfo.getFromAddr(), emailInfo.getSubject());
            // 设置认证：用户名-密码
            email.setAuthentication(emailInfo.getUserName(), emailInfo.getPassword());
            //设置收件人
            email.addTo(emailInfo.getToAddr());
            //设置抄送
            if (ArrayUtils.isNotEmpty(emailInfo.getCcAddr())) {
                email.addCc(emailInfo.getCcAddr());
            }
            //设置暗送人
            if (ArrayUtils.isNotEmpty(emailInfo.getBccAddr())) {
                email.addBcc(emailInfo.getBccAddr());
            }
            //设置标题
            email.setSubject(emailInfo.getSubject());
            //设置发送内容
            if (org.apache.commons.lang.StringUtils.isNotEmpty(emailInfo.getBodyHtml())) {
                email.setHtmlMsg(emailInfo.getBodyHtml());
            } else {
                email.setTextMsg(emailInfo.getBodyText());
            }
            //发附件
            if (null != emailInfo.getEmailAttachment()) {
                email.attach(emailInfo.getEmailAttachment());
            }
            // 发送
            email.send();
            log.info("sendMail--邮件成功发送！businessCode=" + emailInfo.getBusinessCode());
        } catch (Exception e) {
            log.info("sendMail--邮件发送异常--e=", e);
            throw new BusinessException("邮箱发送异常!");
        }
    }

    public static void sendMailByThreadPool(final EmailInfo emailInfo) {
        asyncThreadPool.execute(new Thread(){
            @Override
            public void run() {
                EmailUtil.sendMail(emailInfo);
            }
        });
    }

    /**
     * 删除邮件并删除附件
     *
     * @param emailInfo
     */
    public static void sendMailAndDelAttach(EmailInfo emailInfo) {
        try {
            sendMail(emailInfo);
        } finally {
            //删除附件
            EmailAttachment emailAttachment = emailInfo.getEmailAttachment();
            if (null != emailAttachment) {
                File file = new File(emailAttachment.getPath());
                if (file.isFile() && file.exists()) {
                    try {
                        log.info("sendMailAndDelAttach--开始删除附件--path=" + emailAttachment.getPath());
                        FileUtils.forceDelete(file);
                        log.info("sendMailAndDelAttach--附件删除成功！");
                    } catch (Exception e) {
                        log.error("sendMailAndDelAttach--删除附件异常！", e);
                    }
                }
            }
        }
    }


}
