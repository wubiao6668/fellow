/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.common.db.able;

import com.fellow.domain.base.BaseDomain;
import com.fellow.domain.base.QueryDomain;
import com.fellow.domain.mybatis.PageListImpl;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;

public interface FindListAble {
    /**
     * 列表查询
     *
     * @param q
     * @return
     */
    List<BaseDomain> findList(QueryDomain q);

    /**
     * 查询符合条数
     *
     * @param q
     * @return
     */
    long findListCount(QueryDomain q);

    /**
     * 分页查询
     *
     * @param q
     * @param p
     * @return
     */
    PageListImpl<BaseDomain> findList(QueryDomain q, PageBounds p);
}
