/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.domain.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Model的基类.
 */
public abstract class BaseDomain implements Serializable {

    public static final int ENABLED = 0;

    public static final int DELETED = 1;

    protected static final String DATE_FORMAT = "yyyy-MM-dd";

    protected static final String TIME_FORMAT = "HH:mm:ss";

    protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 主键
     */
    protected Long id;
    /**
     * 创建时间
     */
    protected Date createTime;
    /**
     * 创建人
     */
    protected String createAccount;
    /**
     * 创建人代码
     */
    protected String createName;

    /**
     * 修改时间
     */
    protected Date updateTime;
    /**
     * 修改人
     */
    protected String updateAccount;
    /**
     * 修改人代码
     */
    protected String updateName;
    /**
     * 状态(默认启用)
     */
    protected Integer isDelete = ENABLED;
    /**
     * 乐观锁版本号
     */
    protected Integer sysVersion = 1;
    /**
     * 数据操作时间ts
     */
    protected Date ts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(String createAccount) {
        this.createAccount = createAccount;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateAccount() {
        return updateAccount;
    }

    public void setUpdateAccount(String updateAccount) {
        this.updateAccount = updateAccount;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(Integer sysVersion) {
        this.sysVersion = sysVersion;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }
}