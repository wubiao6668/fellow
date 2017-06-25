/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.MessageDetail;
import com.fellow.domain.mybatis.PageListImpl;
import com.fellow.domain.query.MessageDetailQuery;

import java.util.List;

public interface MessageDetailService extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble{

    PageListImpl<MessageDetail> selectPersonalMessages(MessageDetailQuery messageDetailQuery);

    List<MessageDetail> selectMessagesAndSetHadRead(MessageDetailQuery messageDetailQuery);

    List<MessageDetail> selectReceiveMessages(MessageDetailQuery messageDetailQuery);

    List<MessageDetail> selectReceiveMessagesAndSetRead(MessageDetailQuery messageDetailQuery);

    void setAllHadRead(String account);

    void setAllHadReadBatch(String[] accounts);

    void deleteMessage(String account);

    void deleteMessageBatch(String[] accounts);

    int updateDeleteMessageById(MessageDetail messageDetail);

    int updateDeleteMessageByIdBatch(MessageDetail[] messageDetails);
}
