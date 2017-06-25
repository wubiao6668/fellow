package com.fellow.service.impl;

import com.fellow.dao.MessageDetailMapper;
import com.fellow.dao.MessageMapper;
import com.fellow.domain.enums.IsDeleteEnum;
import com.fellow.domain.enums.MessageReadEnum;
import com.fellow.domain.model.Message;
import com.fellow.domain.model.MessageDetail;
import com.fellow.domain.mybatis.PageListImpl;
import com.fellow.domain.query.MessageDetailQuery;
import com.fellow.domain.session.LocalSession;
import com.fellow.domain.session.Session;
import com.fellow.service.MessageDetailService;
import com.fellow.service.base.ServiceAbstract;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageDetailServiceImpl extends ServiceAbstract<MessageDetailMapper> implements MessageDetailService {

    @Resource
    private MessageMapper messageMapper;

    @Transactional(readOnly = true)
    @Override
    public PageListImpl<MessageDetail> selectPersonalMessages(MessageDetailQuery messageDetailQuery) {
        return repository.selectPersonalMessages(messageDetailQuery);
    }

    @Transactional
    @Override
    public List<MessageDetail> selectMessagesAndSetHadRead(MessageDetailQuery messageDetailQuery) {
        setAllHadRead(messageDetailQuery.getSendAccount());
        return repository.selectPersonalMessages(messageDetailQuery);
    }

    @Transactional
    @Override
    public void setAllHadRead(String account) {
        Session session = LocalSession.getSession();
        Message message = new Message();
        if (session.getUserAccount().compareToIgnoreCase(account) > 0) {
            message.setLongAccount(session.getUserAccount());
            message.setShortAccount(account);
            message.setUpdateAccount(session.getUserAccount());
            message.setUpdateName(session.getUserName());
            messageMapper.longMessageHadRead(message);
        } else {
            message.setShortAccount(session.getUserAccount());
            message.setLongAccount(account);
            message.setUpdateAccount(session.getUserAccount());
            message.setUpdateName(session.getUserName());
            messageMapper.shortMessageHadRead(message);
        }

        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setUpdateReadStatus(MessageReadEnum.HAD_READ.getKey());
        messageDetail.setIsRead(MessageReadEnum.UN_READ.getKey());
        messageDetail.setReceiveAccount(session.getUserAccount());
        messageDetail.setSendAccount(account);
        messageDetail.setUpdateAccount(session.getUserAccount());
        messageDetail.setUpdateName(session.getUserName());
        repository.updateMessageHadRead(messageDetail);
    }

    @Transactional
    @Override
    public void setAllHadReadBatch(String[] accounts) {
        for (String sendAccount : accounts) {
            setAllHadRead(sendAccount);
        }
    }

    @Transactional
    @Override
    public void deleteMessage(String account) {
        Message message = new Message();
        if (session.getUserAccount().compareToIgnoreCase(account) > 0) {
            message.setLongAccount(session.getUserAccount());
            message.setShortAccount(account);
            message.setLongDelete(IsDeleteEnum.YES.getKey());
        } else {
            message.setShortAccount(session.getUserAccount());
            message.setLongAccount(account);
            message.setShortDelete(IsDeleteEnum.YES.getKey());
        }
        message.setUpdateAccount(session.getUserAccount());
        message.setUpdateName(session.getUserName());
        messageMapper.deleteMessage(message);

        //删除发送者为当前人
        MessageDetail sendDeleteMessage = new MessageDetail();
        sendDeleteMessage.setSendAccount(session.getUserAccount());
        sendDeleteMessage.setReceiveAccount(account);
        sendDeleteMessage.setUpdateAccount(session.getUserAccount());
        sendDeleteMessage.setUpdateName(session.getUserName());
        repository.sendMessageDelete(sendDeleteMessage);

        //删除接收者为当前人
        MessageDetail receiveDeleteMessage = new MessageDetail();
        receiveDeleteMessage.setSendAccount(account);
        receiveDeleteMessage.setReceiveAccount(session.getUserAccount());
        receiveDeleteMessage.setUpdateAccount(session.getUserAccount());
        receiveDeleteMessage.setUpdateName(session.getUserName());
        repository.receiveMessageDelete(receiveDeleteMessage);

    }

    @Transactional
    @Override
    public void deleteMessageBatch(String[] accounts) {
        for (String sendAccount : accounts) {
            deleteMessage(sendAccount);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<MessageDetail> selectReceiveMessages(MessageDetailQuery messageDetailQuery) {
        return repository.selectReceiveMessages(messageDetailQuery);
    }

    @Transactional
    @Override
    public List<MessageDetail> selectReceiveMessagesAndSetRead(MessageDetailQuery messageDetailQuery) {
        List<MessageDetail> messageDetailList = repository.selectReceiveMessages(messageDetailQuery);
        if (CollectionUtils.isNotEmpty(messageDetailList)) {
            setAllHadRead(messageDetailQuery.getSendAccount());
        }
        return messageDetailList;
    }

    @Override
    public int updateDeleteMessageById(MessageDetail messageDetail) {
        if (session.getUserAccount().equals(messageDetail.getSendAccount())) {
            messageDetail.setSendDelete(IsDeleteEnum.YES.getKey());
        } else {
            messageDetail.setReceiveDelete(IsDeleteEnum.YES.getKey());
        }
        return repository.updateDeleteMessageById(messageDetail);
    }

    @Override
    public int updateDeleteMessageByIdBatch(MessageDetail[] messageDetails) {
        int row = 0;
        for (MessageDetail detailTemp : messageDetails) {
            row += updateDeleteMessageById(detailTemp);
        }
        return row;
    }
}
