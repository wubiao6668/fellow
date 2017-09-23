package com.fellow.domain.vo;

import com.fellow.domain.model.AttitudeInfo;
import com.fellow.domain.model.User;

/**
 * Created by wubiao on 17/9/2017.
 */
public class AttitudeInfoVo extends AttitudeInfo {



    private User fromUser = new User();
    private User toUser = new User();

    private int entityStatus;
    private AttitudeEntity attitudeEntity;

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public int getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(int entityStatus) {
        this.entityStatus = entityStatus;
    }

    public AttitudeEntity getAttitudeEntity() {
        return attitudeEntity;
    }

    public void setAttitudeEntity(AttitudeEntity attitudeEntity) {
        this.attitudeEntity = attitudeEntity;
    }
}
