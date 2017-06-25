/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service.base;

import com.fellow.common.db.able.*;
import com.fellow.common.exception.UniqueException;
import com.fellow.domain.base.BaseDomain;
import com.fellow.domain.base.QueryDomain;
import com.fellow.domain.mybatis.PageListImpl;
import com.fellow.domain.session.Session;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * service 基类
 */
public abstract class ServiceAbstract<R> {
    public Log log = LogFactory.getLog(this.getClass());
    @Autowired
    protected R repository;
    @Autowired
    protected Session session;

    /**
     * 根据主键逻辑删除
     *
     * @param entity
     * @return
     */
    @Transactional
    public int delete(BaseDomain entity) {
        if (StringUtils.isEmpty(entity.getUpdateAccount())) {
            entity.setUpdateAccount(session.getUserAccount());
            entity.setUpdateName(session.getUserName());
        }
        int row = ((DeleteAble) repository).delete(entity);
        if (1 != row) {
            throw new UniqueException("插入失败！");
        }
        return row;
    }

    /**
     * 根据主键逻辑删除
     *
     * @param key
     * @return
     */
    @Transactional
    public int delete(Number key) {
        int row = ((DeleteAble) repository).delete(key);
        if (1 != row) {
            throw new UniqueException("插入失败！");
        }
        return row;
    }

    /**
     * 根据主键物理删除
     *
     * @param
     * @return
     */
    @Transactional
    public int deletePhysical(BaseDomain m) {
        int row = ((DeletePhysicalAble) repository).deletePhysical(m);
        if (1 != row) {
            throw new UniqueException("删除失败！");
        }
        return row;
    }

    @Transactional
    public int deletePhysical(Number id) {
        int row = ((DeletePhysicalAble) repository).deletePhysical(id);
        if (1 != row) {
            throw new UniqueException("删除失败！");
        }
        return row;
    }

    /**
     * 列表查询
     *
     * @param entity
     * @return
     */
    @Transactional(readOnly = true)
    public List<BaseDomain> findList(QueryDomain entity) {
        return ((FindListAble) repository).findList(entity);
    }

    /**
     * 查询符合条数
     *
     * @param q
     * @return
     */
    @Transactional(readOnly = true)
    public long findListCount(QueryDomain q) {
        return ((FindListAble) repository).findListCount(q);
    }

    /**
     * 分页查询
     *
     * @param q
     * @param p
     * @return
     */
    @Transactional(readOnly = true)
    public PageListImpl<BaseDomain> findList(QueryDomain q, PageBounds p) {
        return ((FindListAble) repository).findList(q, p);
    }

    /**
     * 根据主键查询
     *
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    public BaseDomain getByKey(Long key) {
        return ((GetByKeyAble) repository).getByKey(key);
    }

    /**
     * 插入
     *
     * @param entity
     * @return
     */
    @Transactional
    public int insert(BaseDomain entity) {
        if (StringUtils.isEmpty(entity.getCreateAccount())) {
            entity.setCreateAccount(session.getUserAccount());
            entity.setCreateName(session.getUserName());
        }
        int row = ((InsertAble) repository).insert(entity);
        if (1 != row) {
            throw new UniqueException("插入失败！");
        }
        return row;
    }

    /**
     * 可选插入
     *
     * @param entity
     * @return
     */
    @Transactional
    public int insertSelective(BaseDomain entity) {
        if (StringUtils.isEmpty(entity.getCreateAccount())) {
            entity.setCreateAccount(session.getUserAccount());
            entity.setCreateName(session.getUserName());
        }
        int row = ((InsertSelectiveAble) repository).insertSelective(entity);
        if (1 != row) {
            throw new UniqueException("插入失败！");
        }
        return row;
    }

    /**
     * 修改
     *
     * @param entity
     * @return
     */
    public int update(BaseDomain entity) {
        if (StringUtils.isEmpty(entity.getUpdateAccount())) {
            entity.setUpdateAccount(session.getUserAccount());
            entity.setUpdateName(session.getUserName());
        }
        int row = ((UpdateAble) repository).update(entity);
        if (1 != row) {
            throw new UniqueException("修改失败！");
        }
        return row;
    }

    /**
     * 带版本号更新
     *
     * @param entity
     * @return
     */
    public int updateByOptimisticLock(BaseDomain entity) {
        if (StringUtils.isEmpty(entity.getUpdateAccount())) {
            entity.setUpdateAccount(session.getUserAccount());
            entity.setUpdateName(session.getUserName());
        }
        int row = ((UpdateByOptimisticLockAble) repository).updateByOptimisticLock(entity);
        if (1 != row) {
            throw new UniqueException("修改失败！");
        }
        return row;
    }


    public void setRepository(R repository) {
        this.repository = repository;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}