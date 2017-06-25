/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.common.db.able;

import com.fellow.domain.base.BaseDomain;

public interface GetByKeyAble {

    /**
     * 根据主键查询
     *
     * @param k
     * @return
     */
    BaseDomain getByKey(Long k);
}
