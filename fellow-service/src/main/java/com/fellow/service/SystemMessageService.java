/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.SystemMessage;
import com.fellow.domain.query.SystemMessageQuery;

import java.util.List;

public interface SystemMessageService extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

    List<SystemMessage> selectSystemMessage(SystemMessageQuery systemMessageQuery);

    long selectSystemMessageCount(SystemMessageQuery systemMessageQuery);

    int selectUnReadSum(SystemMessageQuery systemMessageQuery);

    int updateSendHadRead(SystemMessage systemMessage);

    int updateReceiveHadRead(SystemMessage systemMessage);

    int deleteMessageByAccount(SystemMessage sendMessage,SystemMessage receiveMessage);

    List<SystemMessage> selectMessageByIds(SystemMessageQuery systemMessageQuery);

}
