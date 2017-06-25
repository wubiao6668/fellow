/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.domain.model;

import com.fellow.domain.model.post.PostDomain;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class LostPost extends PostDomain implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //date formats
    public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_TS = DATE_TIME_FORMAT;

    //columns START
    /**
     * 100-拾到  200-丢失       db_column: post_type
     */
    @NotNull
    @Max(32767)
    private Integer postType;
    /**
     * 物品类别 100-证书证件 200-钱包 300-包包 400-钥匙 500-卡 600-电子产品 700-动物 800-装饰品 900-衣服 1000-交通工具       db_column: good_type
     */
    @NotNull
    @Max(32767)
    private Integer goodType;
    /**
     * 地点类型  100-帖子附近 200-公交车 300-地铁 400-出租车 500-火车 600- 飞机 700-私家车 800-其它       db_column: place_type
     */
    @NotNull
    @Max(32767)
    private Integer placeType;
    //columns END

    public LostPost() {
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


//    private LostPostDetail lostPostDetail;
//    private List<LostImg> lostImgList;



    private String postTypeText;
    private String goodTypeText;
    private String placeTypeText;


//    public List<LostImg> getLostImgList() {
//        return lostImgList;
//    }
//
//    public void setLostImgList(List<LostImg> lostImgList) {
//        this.lostImgList = lostImgList;
//    }

//    public LostPostDetail getLostPostDetail() {
//        return lostPostDetail;
//    }
//
//    public void setLostPostDetail(LostPostDetail lostPostDetail) {
//        this.lostPostDetail = lostPostDetail;
//    }


    public String getPostTypeText() {
        return postTypeText;
    }

    public void setPostTypeText(String postTypeText) {
        this.postTypeText = postTypeText;
    }

    public String getGoodTypeText() {
        return goodTypeText;
    }

    public void setGoodTypeText(String goodTypeText) {
        this.goodTypeText = goodTypeText;
    }

    public String getPlaceTypeText() {
        return placeTypeText;
    }

    public void setPlaceTypeText(String placeTypeText) {
        this.placeTypeText = placeTypeText;
    }
}

