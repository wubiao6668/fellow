/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.Message;
import com.fellow.domain.query.MessageQuery;
import com.fellow.domain.vo.MessageCount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble{

    List<Message> selectPersonMessage(MessageQuery messageQuery);

    MessageCount selectPersonMessageCount(MessageQuery messageQuery);

    int longMessageHadRead(Message message);

    int shortMessageHadRead(Message message);

    int unReadMessageUpdate(Message message);

    Long messageExist(MessageQuery messageQuery);

    int deleteMessage(Message message);
}
