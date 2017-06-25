package com.fellow.domain.vo;

import com.fellow.domain.model.PersonalDynamicsComment;

/**
 * Created by wubiao on 2017/4/7.
 */
public class PersonalDynamicsCommentVo extends PersonalDynamicsComment {
    private static final long serialVersionUID = -8630103806660086447L;
    private String originalContent;

    public String getOriginalContent() {
        return originalContent;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }
}
