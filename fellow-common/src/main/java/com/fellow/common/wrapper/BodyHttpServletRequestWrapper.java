package com.fellow.common.wrapper;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by wubiao on 2016/9/4.
 */
public class BodyHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final String safeBodyHtml;
    private Whitelist whitelist;

    public BodyHttpServletRequestWrapper(HttpServletRequest request, Whitelist whitelist)
            throws IOException {
        super(request);
        String bodyHtml = IOUtils.toString(request.getInputStream(), Charset.defaultCharset());
        safeBodyHtml = Jsoup.clean(bodyHtml, whitelist);
        this.whitelist = whitelist;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public String getRequestURI() {
        return super.getRequestURI();
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameterValues = super.getParameterValues(name);
        if (ArrayUtils.isNotEmpty(parameterValues)) {
            for (int i = 0; i < parameterValues.length; i++) {
                parameterValues[i] = Jsoup.clean(parameterValues[i], whitelist);
            }
        }
        return parameterValues;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(safeBodyHtml.getBytes());
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }
}
