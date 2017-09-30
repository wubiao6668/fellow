package com.fellow.domain.es;

import com.fellow.domain.constant.EsConstant;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wubiao on 30/9/2017.
 */
@Document(indexName = EsConstant.FELLOW_INDEX_NAME, type = LostPostEsDomain.TYPE_NAME)
public class LostPostEsDomain implements Serializable {
    public static final String TYPE_NAME = "lost_post";

    private static final long serialVersionUID = -3513988892109224171L;
    @Id
    /**
     * 主键
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 帖子内容
     */
    private String postText;
    /**
     * 贴子精度
     */
    private Double postLng;
    /**
     * 纬度
     */
    private Double postLat;
    /**
     * 100-拾到  200-丢失
     */
    private Integer postType;
    /**
     * 物品类别
     */
    private Integer goodType;
    /**
     * 地点类型
     */
    private Integer placeType;
    /**
     * 发帖人
     */
    private String postAccount;
    /**
     * 发帖时间
     */
    private Date postTime;
    /**
     * 发生时间
     */
    private Date happenedDate;
    /**
     * 创建时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Double getPostLng() {
        return postLng;
    }

    public void setPostLng(Double postLng) {
        this.postLng = postLng;
    }

    public Double getPostLat() {
        return postLat;
    }

    public void setPostLat(Double postLat) {
        this.postLat = postLat;
    }

    public Integer getPostType() {
        return postType;
    }

    public void setPostType(Integer postType) {
        this.postType = postType;
    }

    public Integer getGoodType() {
        return goodType;
    }

    public void setGoodType(Integer goodType) {
        this.goodType = goodType;
    }

    public Integer getPlaceType() {
        return placeType;
    }

    public void setPlaceType(Integer placeType) {
        this.placeType = placeType;
    }

    public String getPostAccount() {
        return postAccount;
    }

    public void setPostAccount(String postAccount) {
        this.postAccount = postAccount;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Date getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
