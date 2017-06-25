/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.common.plugin.JqGrid;

import com.fellow.domain.mybatis.PageListImpl;
import com.fellow.domain.mybatis.PaginatorImpl;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor;
import com.fellow.common.plugin.JqGrid.annotation.JqGridTable;

import java.util.List;

/**
 * Created by wubiao on 2016/8/22.
 */
public class JqGridTablePageHandlerMethodReturnValueHandler extends AbstractMessageConverterMethodProcessor {

    public JqGridTablePageHandlerMethodReturnValueHandler(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return PageListImpl.class.equals(returnType.getMethod().getReturnType()) && returnType.getMethodAnnotation(JqGridTable.class) != null;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        if (null != returnValue) {
            JqGridTablePage jqGridTablePage = new JqGridTablePage();
            PageListImpl pageList = (PageListImpl) returnValue;
            PaginatorImpl paginator = pageList.getPaginatorImpl();
            jqGridTablePage.setPage(paginator.getPage());
            jqGridTablePage.setRecords(paginator.getTotalCount());
            jqGridTablePage.setTotal(paginator.getTotalPages());
            jqGridTablePage.setRows(pageList);
            writeWithMessageConverters(jqGridTablePage, returnType, webRequest);
        }
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return null;
    }

}
