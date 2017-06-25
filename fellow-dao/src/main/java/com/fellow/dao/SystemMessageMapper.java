package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.SystemMessage;
import com.fellow.domain.query.SystemMessageQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemMessageMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

    List<SystemMessage> selectSystemMessage(SystemMessageQuery systemMessageQuery);

    long selectSystemMessageCount(SystemMessageQuery systemMessageQuery);

    int selectUnReadSum(SystemMessageQuery systemMessageQuery);

    int updateSendHadRead(SystemMessage systemMessage);

    int updateReceiveHadRead(SystemMessage systemMessage);

    int deleteBySendAccount(SystemMessage systemMessage);

    int deleteByReceiveAccount(SystemMessage systemMessage);

    List<SystemMessage> selectMessageByIds(SystemMessageQuery systemMessageQuery);

}