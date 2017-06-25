/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.domain.query;

import com.fellow.domain.query.post.PostReplyQuery;

import java.io.Serializable;
import java.util.Set;

public class LostPostReplyQuery extends PostReplyQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;

    //date formats
    public static final String FORMAT_REPLY_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
    public static final String FORMAT_TS = DATE_TIME_FORMAT;

    private Set<Long> idSet;

    public Set<Long> getIdSet() {
        return idSet;
    }

    public void setIdSet(Set<Long> idSet) {
        this.idSet = idSet;
    }
}

