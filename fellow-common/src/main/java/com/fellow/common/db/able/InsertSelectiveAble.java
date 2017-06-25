/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.common.db.able;

import com.fellow.domain.base.BaseDomain;

public interface InsertSelectiveAble {

    /**
     * 可选插入
     *
     * @param entity
     * @return
     */
    int insertSelective(BaseDomain entity);

}
