package com.fellow.domain.web.array;

import com.fellow.domain.model.MessageDetail;

import java.io.Serializable;

/**
 * Created by wubiao on 2017/3/29.
 */
public class MessageDetailArray implements Serializable {
    private MessageDetail[] messageDetails;

    public MessageDetail[] getMessageDetails() {
        return messageDetails;
    }

    public void setMessageDetails(MessageDetail[] messageDetails) {
        this.messageDetails = messageDetails;
    }
}
