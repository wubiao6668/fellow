package com.fellow.common.filter;

import com.fellow.common.wrapper.BodyHttpServletRequestWrapper;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.safety.Whitelist;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by wubiao on 2016/9/4.
 */
public class BodyFilter implements Filter {

    private String excludeMapping;
    private String includeMapping;
    private String whitelist;
    private String tagsStr;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludeMapping = filterConfig.getInitParameter("excludeMapping");
        includeMapping = filterConfig.getInitParameter("includeMapping");
        whitelist = filterConfig.getInitParameter("whitelist");
        tagsStr = filterConfig.getInitParameter("tagsStr");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String requestUri = httpServletRequest.getRequestURI();
            if (StringUtils.isNotBlank(excludeMapping) && !"*".equals(excludeMapping)) {
                if (isContaint(excludeMapping, requestUri) || (!"*".equals(includeMapping) && StringUtils.isNotBlank(includeMapping) && !isContaint(includeMapping, requestUri))) {
                    chain.doFilter(request, response);
                } else {
                    chain.doFilter(new BodyHttpServletRequestWrapper(httpServletRequest, whitelistType()), response);
                }

            } else {
                if (!"*".equals(includeMapping) && (StringUtils.isNotBlank(includeMapping) && !isContaint(includeMapping, requestUri))) {
                    chain.doFilter(request, response);
                } else {
                    chain.doFilter(new BodyHttpServletRequestWrapper(httpServletRequest, whitelistType()), response);
                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    public boolean isContaint(String mapping, String requestUri) {
        for (String uriTemp : mapping.split(";")) {
            if (requestUri.startsWith(uriTemp)) {
                return true;
            }
        }
        return false;
    }

    public Whitelist whitelistType() {
        Whitelist whitelistBasic = Whitelist.basic();
        if (StringUtils.isNotBlank(whitelist)) {
            if ("simpleText".equals(whitelist)) {
                whitelistBasic = Whitelist.simpleText();
            } else if ("basicWithImages".equals(whitelist)) {
                whitelistBasic = Whitelist.basicWithImages();
            } else if ("relaxed".equals(whitelist)) {
                whitelistBasic = Whitelist.relaxed();
            }
        }
        if (StringUtils.isNotBlank(tagsStr)) {
            whitelistBasic.addTags(tagsStr.split(";"));
        }
        return whitelistBasic;
    }

    public String getExcludeMapping() {
        return excludeMapping;
    }

    public void setExcludeMapping(String excludeMapping) {
        this.excludeMapping = excludeMapping;
    }

    public String getIncludeMapping() {
        return includeMapping;
    }

    public void setIncludeMapping(String includeMapping) {
        this.includeMapping = includeMapping;
    }

    public String getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(String whitelist) {
        this.whitelist = whitelist;
    }

    public String getTagsStr() {
        return tagsStr;
    }

    public void setTagsStr(String tagsStr) {
        this.tagsStr = tagsStr;
    }
}
