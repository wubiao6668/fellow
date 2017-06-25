/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.common.plugin.JqGrid;

import java.io.Serializable;
import java.util.List;


public class JqGridTablePage<D> implements Serializable {
    private static final long serialVersionUID = 911462695061490227L;

    /**
     * 当前页
     */
    private int page;
    /**
     * 总页数
     */
    private int total;
    /**
     * 总的记录数
     */
    private int records;

    private List<D> rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public List<D> getRows() {
        return rows;
    }

    public void setRows(List<D> rows) {
        this.rows = rows;
    }
}
