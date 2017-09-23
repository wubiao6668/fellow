package com.fellow.domain.vo;

import com.fellow.domain.model.post.ImgDomain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wubiao on 17/9/2017.
 */
public class AttitudeEntity implements Serializable {

    private long entityId;
    private String title;
    private String content;
    private long postId;
    private long replyId;
    private long commentId;
    private Integer imgNum;
    private List<ImgDomain> imgList;

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getReplyId() {
        return replyId;
    }

    public void setReplyId(long replyId) {
        this.replyId = replyId;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImgNum() {
        return imgNum;
    }

    public void setImgNum(Integer imgNum) {
        this.imgNum = imgNum;
    }

    public List<ImgDomain> getImgList() {
        return imgList;
    }

    public void setImgList(List<ImgDomain> imgList) {
        this.imgList = imgList;
    }
}
