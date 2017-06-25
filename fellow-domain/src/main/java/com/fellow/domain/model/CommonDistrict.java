/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.domain.model;

import com.fellow.domain.base.BaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class CommonDistrict extends BaseDomain implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //date formats

    //columns START
    /**
     * id       db_column: id
     */
    @Max(99999999L)
    private Long id;
    /**
     * 名称       db_column: name
     */
    @NotBlank
    @Length(max = 255)
    private String name;
    /**
     * 级别分类       db_column: level
     */
    @NotNull
    @Max(127)
    private Integer level;
    /**
     * 用户类型       db_column: usetype
     */
    @NotNull
    @Max(127)
    private Integer usetype;
    /**
     * 父节点       db_column: upid
     */
    @NotNull
    @Max(99999999L)
    private Long upid;
    /**
     * 是否显示       db_column: displayorder
     */
    @NotNull
    @Max(32767)
    private Integer displayorder;
    //columns END

    public CommonDistrict() {
    }

    public CommonDistrict(Long id) {
        this.id = id;
    }

    /**
     * 设置id
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * 获得id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置名称
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获得名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置级别分类
     */
    public void setLevel(Integer value) {
        this.level = value;
    }

    /**
     * 获得级别分类
     */
    public Integer getLevel() {
        return this.level;
    }

    /**
     * 设置用户类型
     */
    public void setUsetype(Integer value) {
        this.usetype = value;
    }

    /**
     * 获得用户类型
     */
    public Integer getUsetype() {
        return this.usetype;
    }

    /**
     * 设置父节点
     */
    public void setUpid(Long value) {
        this.upid = value;
    }

    /**
     * 获得父节点
     */
    public Long getUpid() {
        return this.upid;
    }

    /**
     * 设置是否显示
     */
    public void setDisplayorder(Integer value) {
        this.displayorder = value;
    }

    /**
     * 获得是否显示
     */
    public Integer getDisplayorder() {
        return this.displayorder;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("Name", getName())
                .append("Level", getLevel())
                .append("Usetype", getUsetype())
                .append("Upid", getUpid())
                .append("Displayorder", getDisplayorder())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof CommonDistrict == false) return false;
        if (this == obj) return true;
        CommonDistrict other = (CommonDistrict) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}

