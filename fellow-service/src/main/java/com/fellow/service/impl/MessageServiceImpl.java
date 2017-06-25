/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service.impl;

import com.fellow.common.exception.BusinessException;
import com.fellow.dao.MessageDetailMapper;
import com.fellow.dao.MessageImgMapper;
import com.fellow.dao.MessageMapper;
import com.fellow.domain.model.Message;
import com.fellow.domain.model.MessageDetail;
import com.fellow.domain.model.MessageImg;
import com.fellow.domain.query.MessageQuery;
import com.fellow.domain.vo.MessageCount;
import com.fellow.service.MessageService;
import com.fellow.service.base.ServiceAbstract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl extends ServiceAbstract<MessageMapper> implements MessageService {

    @Resource
    private MessageDetailMapper messageDetailMapper;
    @Resource
    private MessageImgMapper messageImgMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Message> selectPersonMessage(MessageQuery messageQuery) {
        return repository.selectPersonMessage(messageQuery);
    }

    @Transactional(readOnly = true)
    @Override
    public MessageCount selectPersonMessageCount(MessageQuery messageQuery) {
        return repository.selectPersonMessageCount(messageQuery);
    }

    @Transactional
    @Override
    public int sendMessage(Message message) {
        int row = 0;
        MessageQuery messageQuery = new MessageQuery();
        messageQuery.setLongAccount(message.getLongAccount());
        messageQuery.setShortAccount(message.getShortAccount());
        Long id = repository.messageExist(messageQuery);
        if (null == id) {
            message.setSendTime(new Date());
            message.setCreateAccount(session.getUserAccount());
            message.setCreateName(session.getUserName());
            repository.insertSelective(message);
        } else {
            message.setId(id);
            message.setUpdateAccount(session.getUserAccount());
            message.setUpdateName(session.getUserName());
            row = repository.unReadMessageUpdate(message);
            if (1 != row) {
                throw new BusinessException("发送消息失败！请重试！");
            }
        }
        MessageDetail messageDetail = message.getMessageDetail();
        messageDetailMapper.insertSelective(messageDetail);
        MessageImg messageImg = message.getMessageImg();
        if (null != messageImg) {
            messageImg.setMessageDetailId(messageDetail.getId());
        }
        messageImgMapper.insertSelective(messageImg);
        return row;
    }

}
