package com.fellow.domain.model.post;

import com.fellow.domain.base.BaseDomain;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Created by wubiao on 2017/1/12.
 */
public class PostDetailDomain extends BaseDomain {

    /**
     * 帖子主键       db_column: post_id
     */
    @NotNull
    @Max(9223372036854775807L)
    private Long postId;
    /**
     * 帖子内容       db_column: post_text
     */
    @NotBlank
    private String postText;
    /**
     * 发帖账号       db_column: account
     */
    @NotBlank
    @Length(max = 50)
    private String account;


    /**
     * 设置帖子主键
     */
    public void setPostId(Long value) {
        this.postId = value;
    }

    /**
     * 获得帖子主键
     */
    public Long getPostId() {
        return this.postId;
    }

    /**
     * 设置帖子内容
     */
    public void setPostText(String value) {
        this.postText = value;
    }

    /**
     * 获得帖子内容
     */
    public String getPostText() {
        return this.postText;
    }

    /**
     * 设置发帖账号
     */
    public void setAccount(String value) {
        this.account = value;
    }

    /**
     * 获得发帖账号
     */
    public String getAccount() {
        return this.account;
    }
}
