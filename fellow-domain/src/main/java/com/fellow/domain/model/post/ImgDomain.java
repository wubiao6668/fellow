/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.domain.model.post;

import com.fellow.domain.base.BaseDomain;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class ImgDomain extends BaseDomain implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    /**
     * post 表主键       db_column: post_id
     */
    @NotNull
    @Max(9223372036854775807L)
    private Long postId;
    /**
     * 回复表id       db_column: reply_id
     */
    @NotNull
    @Max(9223372036854775807L)
    private Long replyId;
    /**
     * 图片类型  100-表情 200-帖子上传 300-回复上传       db_column: img_type
     */
    @NotNull
    @Max(32767)
    private Integer imgType;
    /**
     * 账号       db_column: account
     */
    @NotBlank
    @Length(max = 50)
    private String account;
    /**
     * 图片根路径       db_column: img_root_path
     */
    @NotBlank
    @Length(max = 255)
    private String imgRootPath;
    /**
     * 图片相对路径       db_column: img_relative_path
     */
    @NotBlank
    @Length(max = 255)
    private String imgRelativePath;
    /**
     * 图片名称       db_column: img_name
     */
    @NotBlank
    @Length(max = 255)
    private String imgName;
    //columns END

    public ImgDomain() {
    }

    public ImgDomain(Long id) {
        this.id = id;
    }


    /**
     * 设置post 表主键
     */
    public void setPostId(Long value) {
        this.postId = value;
    }

    /**
     * 获得post 表主键
     */
    public Long getPostId() {
        return this.postId;
    }

    /**
     * 设置回复表id
     */
    public void setReplyId(Long value) {
        this.replyId = value;
    }

    /**
     * 获得回复表id
     */
    public Long getReplyId() {
        return this.replyId;
    }

    /**
     * 设置图片类型  100-表情 200-帖子上传 300-回复上传
     */
    public void setImgType(Integer value) {
        this.imgType = value;
    }

    /**
     * 获得图片类型  100-表情 200-帖子上传 300-回复上传
     */
    public Integer getImgType() {
        return this.imgType;
    }

    /**
     * 设置账号
     */
    public void setAccount(String value) {
        this.account = value;
    }

    /**
     * 获得账号
     */
    public String getAccount() {
        return this.account;
    }

    /**
     * 设置图片根路径
     */
    public void setImgRootPath(String value) {
        this.imgRootPath = value;
    }

    /**
     * 获得图片根路径
     */
    public String getImgRootPath() {
        return this.imgRootPath;
    }

    /**
     * 设置图片相对路径
     */
    public void setImgRelativePath(String value) {
        this.imgRelativePath = value;
    }

    /**
     * 获得图片相对路径
     */
    public String getImgRelativePath() {
        return this.imgRelativePath;
    }

    /**
     * 设置图片名称
     */
    public void setImgName(String value) {
        this.imgName = value;
    }

    /**
     * 获得图片名称
     */
    public String getImgName() {
        return this.imgName;
    }

}

