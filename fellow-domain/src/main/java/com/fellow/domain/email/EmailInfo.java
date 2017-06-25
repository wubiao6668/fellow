package com.fellow.domain.email;

import org.apache.commons.mail.EmailAttachment;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wubiao on 2016/8/1.
 */
public class EmailInfo implements Serializable {
    private static final long serialVersionUID = 2695066132035192040L;

    /**
     * SMTP发送服务器的名字
     */
    private String hostName;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 发件人
     **/
    private String fromAddr;
    /**
     * 默认编码集
     */
    private String newCharset;
    /**
     * 收件人
     **/
    private String[] toAddr;
    /**
     * 抄送人
     **/
    private String[] ccAddr;
    /**
     * 暗送
     **/
    private String[] bccAddr;
    /**
     * 邮件主题
     **/
    private String subject;
    /**
     * 发送时间
     **/
    private Date sendDate;
    /**
     * 邮件正文
     **/
    private String bodyText;
    private String bodyHtml;
    private EmailAttachment emailAttachment;
    /**
     * 业务编码
     */
    private String businessCode;
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFromAddr() {
        return fromAddr;
    }

    public void setFromAddr(String fromAddr) {
        this.fromAddr = fromAddr;
    }

    public String[] getToAddr() {
        return toAddr;
    }

    public void setToAddr(String[] toAddr) {
        this.toAddr = toAddr;
    }

    public String[] getCcAddr() {
        return ccAddr;
    }

    public void setCcAddr(String[] ccAddr) {
        this.ccAddr = ccAddr;
    }

    public String[] getBccAddr() {
        return bccAddr;
    }

    public void setBccAddr(String[] bccAddr) {
        this.bccAddr = bccAddr;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public String getBodyHtml() {
        return bodyHtml;
    }

    public void setBodyHtml(String bodyHtml) {
        this.bodyHtml = bodyHtml;
    }

    public String getNewCharset() {
        return newCharset;
    }

    public void setNewCharset(String newCharset) {
        this.newCharset = newCharset;
    }

    public EmailAttachment getEmailAttachment() {
        return emailAttachment;
    }

    public void setEmailAttachment(EmailAttachment emailAttachment) {
        this.emailAttachment = emailAttachment;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }
}
