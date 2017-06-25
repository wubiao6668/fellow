/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service.impl;

import com.fellow.dao.SystemMessageDetailMapper;
import com.fellow.dao.SystemMessageMapper;
import com.fellow.domain.model.SystemMessage;
import com.fellow.domain.query.SystemMessageQuery;
import com.fellow.service.SystemMessageService;
import com.fellow.service.base.ServiceAbstract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SystemMessageServiceImpl extends ServiceAbstract<SystemMessageMapper> implements SystemMessageService {
    @Resource
    private SystemMessageDetailMapper systemMessageDetailMapper;

    @Transactional(readOnly = true)
    @Override
    public List<SystemMessage>  selectSystemMessage(SystemMessageQuery systemMessageQuery) {
        return repository.selectSystemMessage(systemMessageQuery);
    }

    @Transactional(readOnly = true)
    @Override
    public long selectSystemMessageCount(SystemMessageQuery systemMessageQuery) {
        return repository.selectSystemMessageCount(systemMessageQuery);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectUnReadSum(SystemMessageQuery systemMessageQuery) {
        return repository.selectUnReadSum(systemMessageQuery);
    }

    @Transactional
    @Override
    public int updateSendHadRead(SystemMessage systemMessage) {
        return repository.updateSendHadRead(systemMessage);
    }

    @Transactional
    @Override
    public int updateReceiveHadRead(SystemMessage systemMessage) {
        return repository.updateReceiveHadRead(systemMessage);
    }

    @Transactional
    @Override
    public int deleteMessageByAccount(SystemMessage sendMessage, SystemMessage receiveMessage) {
        int rows = 0;
        if (null != sendMessage) {
            rows = repository.deleteBySendAccount(sendMessage);
            rows = systemMessageDetailMapper.deleteDetailBySendAccount(sendMessage.getSystemMessageDetail());
        }
        if (null != receiveMessage) {
            rows = repository.deleteByReceiveAccount(receiveMessage);
            rows = systemMessageDetailMapper.deleteDetailByReceiveAccount(receiveMessage.getSystemMessageDetail());
        }
        return rows;
    }

    @Transactional(readOnly = true)
    @Override
    public List<SystemMessage> selectMessageByIds(SystemMessageQuery systemMessageQuery) {
        return repository.selectMessageByIds(systemMessageQuery);
    }
}
