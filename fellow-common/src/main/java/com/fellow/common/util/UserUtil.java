package com.fellow.common.util;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

/**
 * Created by wubiao on 2016/11/18.
 */
public class UserUtil {

    public static Integer calcAge(Date date) {
        if (null != date) {
            return Integer.parseInt(DateFormatUtils.format(new Date(), "yyyy")) - Integer.parseInt(DateFormatUtils.format(date, "yyyy"));
        }
        return 0;
    }

}
