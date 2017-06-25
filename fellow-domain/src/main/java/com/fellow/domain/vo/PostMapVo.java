package com.fellow.domain.vo;

import com.fellow.domain.base.BaseDomain;
import com.fellow.domain.model.User;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by wubiao on 2016/9/13.
 */
public class PostMapVo extends BaseDomain implements Serializable {
    //date formats
    public static final String FORMAT_POST_TIME = DATE_TIME_FORMAT;
    private static final long serialVersionUID = -3061993941708833872L;

    private String mapHtmlName;
    private Integer postCount;
    private Double postLng;
    private Double postLat;
    private String title;
    private String postAccount;
    private String postUsername;
    private Date postTime;
    private User postUser = new User();

    public String getMapHtmlName() {
        return mapHtmlName;
    }

    public void setMapHtmlName(String mapHtmlName) {
        this.mapHtmlName = mapHtmlName;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostAccount() {
        return postAccount;
    }

    public void setPostAccount(String postAccount) {
        this.postAccount = postAccount;
    }

    public String getPostUsername() {
        return postUsername;
    }

    public void setPostUsername(String postUsername) {
        this.postUsername = postUsername;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
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

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public User getPostUser() {
        return postUser;
    }

    public void setPostUser(User postUser) {
        this.postUser = postUser;
    }
}
