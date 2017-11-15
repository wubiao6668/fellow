/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.domain.base;

public class QueryDomain extends BaseDomain {

    private int page = 1;
    private int pageSize = 10;
    private String sortColumns;
    private String limit;
    private int count = 1;
    private long queryTime;

    public String getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(String sortColumns) {
        this.sortColumns = sortColumns;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getQueryTime() {
        return queryTime;
    }
    public void initMysqlLimit(){
        if (page <=1){
            setLimit(pageSize+"");
        }else {
            setLimit(((page - 1) * pageSize) + "," + pageSize);
        }
    }
    public void setQueryTime(long queryTime) {
        this.queryTime = queryTime;
    }
}
