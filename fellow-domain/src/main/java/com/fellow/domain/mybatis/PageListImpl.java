package com.fellow.domain.mybatis;


import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by wubiao on 2016/9/11.
 */
public class PageListImpl<E> extends ArrayList<E> {
    private static final long serialVersionUID = 1412759446332294208L;

    private PaginatorImpl paginatorImpl;

    public PageListImpl() {
    }

    public PageListImpl(Collection<? extends E> c) {
        super(c);
    }


    public PageListImpl(Collection<? extends E> c, PaginatorImpl p) {
        super(c);
        this.paginatorImpl = p;
    }

    public PageListImpl(PaginatorImpl p) {
        this.paginatorImpl = p;
    }


    /**
     * 得到分页器，通过PaginatorImpl可以得到总页数等值
     *
     * @return
     */
    public PaginatorImpl getPaginatorImpl() {
        return paginatorImpl;
    }

    public void setPaginatorImpl(PaginatorImpl paginatorImpl) {
        this.paginatorImpl = paginatorImpl;
    }
}
