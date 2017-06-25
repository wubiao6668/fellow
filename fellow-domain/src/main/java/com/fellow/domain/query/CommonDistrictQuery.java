/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.domain.query;

import com.fellow.domain.base.QueryDomain;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

public class CommonDistrictQuery extends QueryDomain implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;

    //date formats


    /**
     * id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 级别分类
     */
    private Integer level;
    /**
     * 用户类型
     */
    private Integer usetype;
    /**
     * 父节点
     */
    private Long upid;
    /**
     * 是否显示
     */
    private Integer displayorder;

    /**
     * 获得id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置id
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * 获得名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置名称
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获得级别分类
     */
    public Integer getLevel() {
        return this.level;
    }

    /**
     * 设置级别分类
     */
    public void setLevel(Integer value) {
        this.level = value;
    }

    /**
     * 获得用户类型
     */
    public Integer getUsetype() {
        return this.usetype;
    }

    /**
     * 设置用户类型
     */
    public void setUsetype(Integer value) {
        this.usetype = value;
    }

    /**
     * 获得父节点
     */
    public Long getUpid() {
        return this.upid;
    }

    /**
     * 设置父节点
     */
    public void setUpid(Long value) {
        this.upid = value;
    }

    /**
     * 获得是否显示
     */
    public Integer getDisplayorder() {
        return this.displayorder;
    }

    /**
     * 设置是否显示
     */
    public void setDisplayorder(Integer value) {
        this.displayorder = value;
    }


    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}

