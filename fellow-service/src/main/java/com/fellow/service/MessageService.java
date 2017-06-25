/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.Message;
import com.fellow.domain.query.MessageQuery;
import com.fellow.domain.vo.MessageCount;

import java.util.List;

public interface MessageService extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble{

    List<Message> selectPersonMessage(MessageQuery messageQuery);

    MessageCount selectPersonMessageCount(MessageQuery messageQuery);

    int sendMessage(Message message);
}
