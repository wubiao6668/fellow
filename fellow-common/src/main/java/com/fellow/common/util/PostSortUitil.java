package com.fellow.common.util;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wubiao on 2016/12/30.
 */
public class PostSortUitil {

    static Map<String, String> sortColumnsMap = new HashMap<String, String>();

    static {
        sortColumnsMap.put("replyTime", "reply_time");
        sortColumnsMap.put("replyNum", "reply_num");
        sortColumnsMap.put("upNum", "up_num");
        sortColumnsMap.put("downNum", "down_num");
        sortColumnsMap.put("loveNum", "love_num");
    }

    public static String getSortField(String field, String defaultField, String defaultSort) {
        String sortColumn = StringUtils.defaultIfBlank(sortColumnsMap.get(field),  defaultField );
        return " " + sortColumn + " " + defaultSort;
    }

    public static String getDefaultSortField(String field) {
        return getSortField(field, "reply_time","desc");
    }


}
