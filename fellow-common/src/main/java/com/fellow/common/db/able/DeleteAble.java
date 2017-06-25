/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.common.db.able;

import com.fellow.domain.base.BaseDomain;

public interface DeleteAble {
    /**
     * 根据主键逻辑删除
     *
     * @param entity
     * @return
     */
    int delete(BaseDomain entity);

    /**
     * 根据主键逻辑删除
     *
     * @param key
     * @return
     */
    int delete(Number key);
}
