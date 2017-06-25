package com.fellow.common.util;

import com.fellow.domain.enums.ShortEnum;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by wubiao on 2016/11/15.
 */
public class PinyinHelperUtil {
    public static Log log = LogFactory.getLog(PinyinHelperUtil.class);

    public static final String shortFirst(String shortStr) {
        if (StringUtils.isNotBlank(shortStr)) {
            shortStr = shortStr.trim();
            shortStr = shortStr.substring(0, 1);
            try {
                shortStr = PinyinHelper.getShortPinyin(shortStr);
            } catch (PinyinException e) {
                log.error("shortFirst--", e);
            }
            return ShortEnum.getKey(shortStr);
        } else {
            return ShortEnum.OTHER.getValue();
        }
    }

}
