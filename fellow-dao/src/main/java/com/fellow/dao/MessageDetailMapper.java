/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.MessageDetail;
import com.fellow.domain.mybatis.PageListImpl;
import com.fellow.domain.query.MessageDetailQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDetailMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

    PageListImpl<MessageDetail> selectPersonalMessages(MessageDetailQuery messageDetailQuery);

    List<MessageDetail> selectReceiveMessages(MessageDetailQuery messageDetailQuery);

    int updateMessageHadRead(MessageDetail message);

    int sendMessageDelete(MessageDetail message);

    int receiveMessageDelete(MessageDetail message);

    int updateDeleteMessageById(MessageDetail messageDetail);
}