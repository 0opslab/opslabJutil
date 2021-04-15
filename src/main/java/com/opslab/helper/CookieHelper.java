package com.opslab.helper;

import com.opslab.Opslab;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * CookieHelper : 针对Http Cookie的工具类
 *
 */
public class CookieHelper {

    /**
     * 设置 Cookie, 过期时间为30分钟
     *
     * @param name  名称
     * @param value 值
     */
    public static Cookie setCookie(HttpServletResponse response, String name, String value, String path) {
        return addCookie(response, name, value, path, 60 * 30);
    }

    /**
     * 设置 Cookie, 过期时间自定义
     *
     * @param name   名称
     * @param value  值
     * @param maxAge 过期时间, 单位秒
     */
    public static Cookie addCookie(HttpServletResponse response, String name, String value, String path, int maxAge) {
        Cookie cookie = null;
        try {
            cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
            cookie.setMaxAge(maxAge);
            if (null != path) {
                cookie.setPath(path);
            }
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return cookie;
    }

    /**
     * 设置 Cookie, 过期时间自定义
     *
     * @param name   名称
     * @param value  值
     * @param maxAge 过期时间, 单位秒
     */
    public static Cookie addCookie(HttpServletResponse response, String name, String value, String domain, String path, int maxAge) {
        Cookie cookie = null;
        try {
            cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
            if(StringHelper.isNotEmpty(domain)){
                cookie.setDomain(domain);
            }
            cookie.setMaxAge(maxAge);
            if (null != path) {
                cookie.setPath(path);
            }
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return cookie;
    }

    /**
     * 设置 Cookies, 过期时间自定义
     *
     * @param response 响应对象
     * @param values   值
     * @param path     路径
     * @param maxAge   过期时间, 单位秒
     * @return Cookies
     */
    public static ArrayList<Cookie> addCookies(HttpServletResponse response, Map<String, String> values, String path, int maxAge) {
        Set<Map.Entry<String, String>> entries = values.entrySet();
        ArrayList<Cookie> cookies = new ArrayList<Cookie>();
        try {
            for (Map.Entry<String, String> entry : entries) {
                Cookie cookie = new Cookie(entry.getKey(), URLEncoder.encode(entry.getValue(), "UTF-8"));
                cookie.setMaxAge(maxAge);
                if (null != path) {
                    cookie.setPath(path);
                }
                response.addCookie(cookie);
                cookies.add(cookie);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return cookies;
    }


    /**
     * 获得指定Cookie的值
     *
     * @param name 名称
     * @return 值
     */
    public static String getCookie(HttpServletRequest request, String name) {
        return getCookie(request, null, name, false);
    }

    /**
     * 获得指定Cookie的值，并删除。
     *
     * @param name 名称
     * @return 值
     */
    public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        return getCookie(request, response, name, true);
    }

    /**
     * 获得指定Cookie的值
     *
     * @param request   请求对象
     * @param response  响应对象
     * @param name      名字
     * @param isRemoved 是否移除
     * @return 值
     */
    public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name, boolean isRemoved) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    try {
                        value = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    if (isRemoved) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                    return value;
                }
            }
        }
        return value;
    }

    /**
     * 从http头和cookie中获取值
     * @param request
     * @param key
     * @return
     */
    public static String headerOrcookieKey(HttpServletRequest request,String key){
        String header = request.getHeader(key);
        if(StringHelper.isNotEmpty(header)){
            return header;
        }
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return Opslab.STR_EMPTY;
        }
        for (Cookie cookie : cookies) {
            if(key.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return Opslab.STR_EMPTY;
    }
}
