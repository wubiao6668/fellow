/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.domain.page;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.commons.lang.ArrayUtils;

import java.io.Serializable;

public class JqGridPageable implements Serializable {
    /**
     * 每页多少行
     */
    private int rows = 10;
    /**
     * 当前页数
     */
    private int page = 0;
    /**
     * 排序字段
     */
    private String[] sidx;
    /**
     * 排序字段方向
     */
    private String[] sord;

    public PageBounds toPageBounds() {
        String sortString = "";
        if (ArrayUtils.isNotEmpty(sidx)) {
            for (int i = 0; i < sidx.length; i++) {
                String sidxTemp = sidx[i];
                sortString += (sidxTemp + "." + sord[i]);
                if (i != sidx.length - 1) {
                    sortString += ",";
                }
            }
        }
        PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
        return pageBounds;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String[] getSidx() {
        return sidx;
    }

    public void setSidx(String[] sidx) {
        this.sidx = sidx;
    }

    public String[] getSord() {
        return sord;
    }

    public void setSord(String[] sord) {
        this.sord = sord;
    }

}
