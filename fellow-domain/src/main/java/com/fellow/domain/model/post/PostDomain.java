package com.fellow.domain.model.post;

import com.fellow.domain.base.BaseDomain;
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

/**
 * Created by wubiao on 2017/1/3.
 */
public class PostDomain extends BaseDomain {
    public static final String FORMAT_POST_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_HAPPENED_DATE = DATE_TIME_FORMAT;
    /**
     * 标题       db_column: title
     */
    @NotBlank
    @Length(max = 50)
    private String title;
    /**
     * 概论       db_column: brief
     */
    @NotBlank
    @Length(max = 500)
    private String brief;
    /**
     * 发帖人       db_column: post_account
     */
    @NotBlank
    @Length(max = 50)
    private String postAccount;
    /**
     * 发帖人昵称       db_column: post_username
     */
    @NotBlank
    @Length(max = 50)
    private String postUsername;
    /**
     * 发帖时间       db_column: post_time
     */
    @NotNull
    private Date postTime;
    /**
     * 发生时间       db_column: happened_date
     */
    @NotNull
    private Date happenedDate;
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
     * 浏览数       db_column: view_num
     */
    @NotNull
    @Max(99999999999L)
    private Integer viewNum;
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
    /**
     * 帖子位置       db_column: post_lng
     */
    @NotNull
    private Double postLng;
    /**
     * 帖子纬度       db_column: post_lat
     */
    @NotNull
    private Double postLat;

    /**
     * 设置标题
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * 获得标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置概论
     */
    public void setBrief(String value) {
        this.brief = value;
    }

    /**
     * 获得概论
     */
    public String getBrief() {
        return this.brief;
    }

    /**
     * 设置发帖人
     */
    public void setPostAccount(String value) {
        this.postAccount = value;
    }

    /**
     * 获得发帖人
     */
    public String getPostAccount() {
        return this.postAccount;
    }

    /**
     * 设置发帖人昵称
     */
    public void setPostUsername(String value) {
        this.postUsername = value;
    }

    /**
     * 获得发帖人昵称
     */
    public String getPostUsername() {
        return this.postUsername;
    }

    /**
     * 设置发帖时间
     */
    public void setPostTime(Date value) {
        this.postTime = value;
    }

    /**
     * 获得发帖时间
     */
    public Date getPostTime() {
        return this.postTime;
    }

    /**
     * 设置发生时间
     */
    public void setHappenedDate(Date value) {
        this.happenedDate = value;
    }

    /**
     * 获得发生时间
     */
    public Date getHappenedDate() {
        return this.happenedDate;
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
     * 设置浏览数
     */
    public void setViewNum(Integer value) {
        this.viewNum = value;
    }

    /**
     * 获得浏览数
     */
    public Integer getViewNum() {
        return this.viewNum;
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

    /**
     * 设置帖子位置
     */
    public void setPostLng(Double value) {
        this.postLng = value;
    }

    /**
     * 获得帖子位置
     */
    public Double getPostLng() {
        return this.postLng;
    }

    /**
     * 设置帖子纬度
     */
    public void setPostLat(Double value) {
        this.postLat = value;
    }

    /**
     * 获得帖子纬度
     */
    public Double getPostLat() {
        return this.postLat;
    }

    /**
     * 获得发帖时间
     */
    public String getPostTimeString() {
        if (null != getPostTime()) {
            return DateFormatUtils.format(getPostTime(), FORMAT_POST_TIME);
        }
        return null;
    }

    /**
     * 设置发帖时间
     */
    public void setPostTimeString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setPostTime(DateUtils.parseDate(value, new String[]{FORMAT_POST_TIME}));
        }
    }


    /**
     * 获得发生时间
     */
    public String getHappenedDateString() {
        if (null != getHappenedDate()) {
            return DateFormatUtils.format(getHappenedDate(), FORMAT_HAPPENED_DATE);
        }
        return null;
    }

    /**
     * 设置发生时间
     */
    public void setHappenedDateString(String value) throws ParseException {
        if (StringUtils.isNotEmpty(value)) {
            setHappenedDate(DateUtils.parseDate(value, new String[]{FORMAT_HAPPENED_DATE}));
        }
    }

    private String mapHtmlName;
    private User postUser = new User();
    private String postText;

    private PostDetailDomain postDetailDomain;
    private List<ImgDomain> imgList;

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getMapHtmlName() {
        return mapHtmlName;
    }

    public void setMapHtmlName(String mapHtmlName) {
        this.mapHtmlName = mapHtmlName;
    }

    public User getPostUser() {
        return postUser;
    }

    public void setPostUser(User postUser) {
        this.postUser = postUser;
    }

    public PostDetailDomain getPostDetailDomain() {
        return postDetailDomain;
    }

    public void setPostDetailDomain(PostDetailDomain postDetailDomain) {
        this.postDetailDomain = postDetailDomain;
    }

    public List<ImgDomain> getImgList() {
        return imgList;
    }

    public void setImgList(List<ImgDomain> imgList) {
        this.imgList = imgList;
    }
}
