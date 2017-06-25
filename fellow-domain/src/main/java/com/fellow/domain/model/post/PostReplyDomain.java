package com.fellow.domain.model.post;

import com.fellow.domain.base.BaseDomain;
import com.fellow.domain.model.PostCommentReply;
import com.fellow.domain.model.User;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class PostReplyDomain extends BaseDomain implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
    //date formats
    public static final String FORMAT_REPLY_TIME = DATE_TIME_FORMAT;

    /**
     * post 表主键       db_column: post_id
     */
    @NotNull
    @Max(9223372036854775807L)
    private Long postId;
    /**
     * content       db_column: content
     */
    @NotBlank
    @Length(max = 500)
    private String content;
    /**
     * replyTime       db_column: reply_time
     */
    @NotNull
    private Date replyTime;
    /**
     * 回复账号       db_column: reply_account
     */
    @NotBlank
    @Length(max = 50)
    private String replyAccount;
    /**
     * 顶数量       db_column: up_num
     */
    @NotNull
    @Max(99999999999L)
    private Integer upNum;
    /**
     * 踩数量       db_column: down_num
     */
    @NotNull
    @Max(99999999999L)
    private Integer downNum;
    /**
     * 回复数       db_column: reply_num
     */
    @NotNull
    @Max(99999999999L)
    private Integer replyNum;
    /**
     * 爱心数量       db_column: love_num
     */
    @NotNull
    @Max(99999999999L)
    private Integer loveNum;
    /**
     * 图片数量       db_column: img_num
     */
    @NotNull
    @Max(99999999999L)
    private Integer imgNum;
    //columns END

    public PostReplyDomain() {
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
     * 设置content
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * 获得content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 获得replyTime
     */
    public String getReplyTimeString() {
        if (null != getReplyTime()) {
            return DateFormatUtils.format(getReplyTime(), FORMAT_REPLY_TIME);
        }
        return null;
    }

    /**
     * 设置replyTime
     */
    public void setReplyTimeString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setReplyTime(DateUtils.parseDate(value, new String[]{FORMAT_REPLY_TIME}));
        }
    }

    /**
     * 设置replyTime
     */
    public void setReplyTime(Date value) {
        this.replyTime = value;
    }

    /**
     * 获得replyTime
     */
    public Date getReplyTime() {
        return this.replyTime;
    }

    /**
     * 设置回复账号
     */
    public void setReplyAccount(String value) {
        this.replyAccount = value;
    }

    /**
     * 获得回复账号
     */
    public String getReplyAccount() {
        return this.replyAccount;
    }

    /**
     * 设置顶数量
     */
    public void setUpNum(Integer value) {
        this.upNum = value;
    }

    /**
     * 获得顶数量
     */
    public Integer getUpNum() {
        return this.upNum;
    }

    /**
     * 设置踩数量
     */
    public void setDownNum(Integer value) {
        this.downNum = value;
    }

    /**
     * 获得踩数量
     */
    public Integer getDownNum() {
        return this.downNum;
    }

    /**
     * 设置回复数
     */
    public void setReplyNum(Integer value) {
        this.replyNum = value;
    }

    /**
     * 获得回复数
     */
    public Integer getReplyNum() {
        return this.replyNum;
    }

    /**
     * 设置爱心数量
     */
    public void setLoveNum(Integer value) {
        this.loveNum = value;
    }

    /**
     * 获得爱心数量
     */
    public Integer getLoveNum() {
        return this.loveNum;
    }

    /**
     * 设置图片数量
     */
    public void setImgNum(Integer value) {
        this.imgNum = value;
    }

    /**
     * 获得图片数量
     */
    public Integer getImgNum() {
        return this.imgNum;
    }


    private List<ImgDomain> imgList;
    private List<PostCommentReply> postCommentReplyList;
    private PostCommentReply postCommentReply;
    private PostDomain postDomain;
    private User replyUser = new User();

    public User getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(User replyUser) {
        this.replyUser = replyUser;
    }

    public List<ImgDomain> getImgList() {
        return imgList;
    }

    public void setImgList(List<ImgDomain> imgList) {
        this.imgList = imgList;
    }

    public List<PostCommentReply> getPostCommentReplyList() {
        return postCommentReplyList;
    }

    public void setPostCommentReplyList(List<PostCommentReply> postCommentReplyList) {
        this.postCommentReplyList = postCommentReplyList;
    }

    public PostCommentReply getPostCommentReply() {
        return postCommentReply;
    }

    public void setPostCommentReply(PostCommentReply postCommentReply) {
        this.postCommentReply = postCommentReply;
    }

    public PostDomain getPostDomain() {
        return postDomain;
    }

    public void setPostDomain(PostDomain postDomain) {
        this.postDomain = postDomain;
    }
}

