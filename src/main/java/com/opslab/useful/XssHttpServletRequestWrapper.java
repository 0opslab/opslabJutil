package com.opslab.useful;

import com.opslab.helper.WebHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 实现简单的xss过滤
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    HttpServletRequest xssRequest = null;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        xssRequest = request;
    }


    @Override
    public String getParameter(String name) {
        String value = super.getParameter(WebHelper.replaceXSS(name));
        if (value != null) {
            value = WebHelper.replaceXSS(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(WebHelper.replaceXSS(name));
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                values[i] = WebHelper.replaceXSS(values[i]);
            }
        }
        return values;
    }

    @Override
    public String getHeader(String name) {

        String value = super.getHeader(WebHelper.replaceXSS(name));
        if (value != null) {
            value = WebHelper.replaceXSS(value);
        }
        return value;
    }


}

