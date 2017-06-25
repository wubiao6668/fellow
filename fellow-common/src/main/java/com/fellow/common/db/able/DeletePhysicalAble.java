/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.common.db.able;

import com.fellow.domain.base.BaseDomain;

public interface DeletePhysicalAble {

    /**
     * 根据主键物理删除
     *
     * @param m
     * @return
     */
    int deletePhysical(BaseDomain m);

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int deletePhysical(Number id);
}
