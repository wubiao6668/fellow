package com.fellow.domain.vo;

import com.fellow.domain.model.PostCommentReply;
import com.fellow.domain.model.post.ImgDomain;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by wubiao on 2017/4/5.
 */
public class PostCommentReplyVo extends PostCommentReply {
    public static final String FORMAT_ORIGINAL_SEND_DATE = DATE_TIME_FORMAT;
    private String originalContent;

    private Date originalSendDate;

    private List<ImgDomain> imgDomainList;

    public String getOriginalContent() {
        return originalContent;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }

    public Date getOriginalSendDate() {
        return originalSendDate;
    }

    public void setOriginalSendDate(Date originalSendDate) {
        this.originalSendDate = originalSendDate;
    }

    /**
     * 获得时间戳
     * */
    public String getOriginalSendDateString() {
        if (null != getOriginalSendDate()){
            return DateFormatUtils.format(getOriginalSendDate(),FORMAT_ORIGINAL_SEND_DATE);
        }
        return null;
    }

    /**
     * 设置时间戳
     */
    public void setOriginalSendDateString(String value)  throws ParseException {
        if (StringUtils.isNotEmpty(value)){
            setOriginalSendDate(DateUtils.parseDate(value, new String[]{FORMAT_ORIGINAL_SEND_DATE}));
        }
    }

    public List<ImgDomain> getImgDomainList() {
        return imgDomainList;
    }

    public void setImgDomainList(List<ImgDomain> imgDomainList) {
        this.imgDomainList = imgDomainList;
    }
}
