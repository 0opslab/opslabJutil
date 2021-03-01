package com.opslab.helper;

import com.opslab.bean.ClientInfo;
import com.opslab.util.StringUtil;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Pattern;

/**
 * web相关的一些操作方法
 */
@SuppressWarnings("unchecked")
public class WebHelper {
    // Avoid anything between script tags
    static Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);

    static Pattern scriptPattern_html_src = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern
            .CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    static Pattern scriptPattern_html_script = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern
            .MULTILINE | Pattern.DOTALL);
    /**
     * 对字符串进行编码
     *
     * @param str      需要处理的字符串
     * @param encoding 编码方式
     * @return 编码后的字符串
     */
    public static String escape(String str, String encoding) throws UnsupportedEncodingException {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        char[] chars = ConvertHepler.bytesToChars(ConvertHepler.encodeBytes(str.getBytes(encoding), '%'));
        return new String(chars);
    }

    /**
     * 对字符串进行解码
     *
     * @param str      需要处理的字符串
     * @param encoding 解码方式
     * @return 解码后的字符串
     */
    public static String unescape(String str, String encoding) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        return UrlHelper.decodeQuery(str, encoding);
    }


    /**
     * HTML标签转义方法
     * <p>
     * 空格	 &nbsp;
     * <	小于号	&lt;
     * >	大于号	&gt;
     * &	和号	 &amp;
     * "	引号	&quot;
     * '	撇号 	&apos;
     * ￠	分	 &cent;
     * £	镑	 &pound;
     * ¥	日圆	&yen;
     * €	欧元	&euro;
     * §	小节	&sect;
     * ©	版权	&copy;
     * ®	注册商标	&reg;
     * ™	商标	&trade;
     * ×	乘号	&times;
     * ÷	除号	&divide;
     */
    public static String unhtml(String content) {
        if (StringUtil.isEmpty(content)) {
            return "";
        }
        String html = content;
        html = html.replaceAll("'", "&apos;");
        html = html.replaceAll("\"", "&quot;");
        html = html.replaceAll("\t", "&nbsp;&nbsp;");
        html = html.replaceAll("<", "&lt;");
        html = html.replaceAll(">", "&gt;");
        return html;
    }

    /**
     * html转实体
     * @param content
     * @return
     */
    public static String html(String content) {
        if (StringUtil.isEmpty(content)) {
            return "";
        }
        String html = content;
        html = html.replaceAll("&apos;", "'");
        html = html.replaceAll("&quot;", "\"");
        html = html.replaceAll("&nbsp;", " ");
        html = html.replaceAll("&lt;", "<");
        html = html.replaceAll("&gt;", ">");
        return html;
    }

    /**
     * 去除待带script、src的语句，转义替换后的value值
     * 简单的过滤有一定的弊端
     */
    public static String replaceXSS(String value) {
        if (value != null) {
            StringBuilder result = new StringBuilder(value.length());
            for (int i = 0; i < value.length(); ++i) {
                switch (value.charAt(i)) {
                    case '<':
                        result.append("<");
                        break;
                    case '>':
                        result.append(">");
                        break;
                    case '"':
                        result.append("\"");
                        break;
                    case '\'':
                        result.append("'");
                        break;
                    case '%':
                        result.append("%");
                        break;
                    case ';':
                        result.append(";");
                        break;
                    case '(':
                        result.append("(");
                        break;
                    case ')':
                        result.append(")");
                        break;
                    case '&':
                        result.append("&");
                        break;
                    case '+':
                        result.append("+");
                        break;
                    default:
                        result.append(value.charAt(i));
                        break;
                }
            }
            value = result.toString();
            value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
            value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
            value = value.replaceAll("'", "&#39;");
        }
        return value;
    }



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
            if(domain != null && !"".equals(domain)){
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
        ArrayList<Cookie> cookies = new ArrayList<>();
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
        return null;
    }

    /**
     * 将所有的请求参数转换字符串
     * 例如:
     * uri?param1=111&param2=111,222
     *
     * @param request
     * @return
     */
    public static String requestParamsToString(ServletRequest request) {
        StringBuilder sbuf = new StringBuilder();
        Map<String, String[]> map = request.getParameterMap();
        if (map == null || map.size() == 0) {
            return "";
        }
        int i = 0;

        String arrayJoiner = ",";
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String joiner = "&";
            String key = entry.getKey();
            String[] value = entry.getValue();
            if (value != null && value.length == 1) {
                sbuf.append(joiner).append(key).append("=").append(value[0]);
            } else {
                sbuf.append(joiner).append(key).append("=").append(StringHelper.join(value, arrayJoiner));
            }
        }
        return sbuf.toString();
    }


    /**
     * 判断是否为ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equalsIgnoreCase(header);
    }

    /**
     * 获取客户端请求的IP
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 获取请求参数
     *
     * @param request
     * @return
     */
    public static Map<String, Object> convertDataMap(HttpServletRequest request) {
        Map<String, String[]> properties = request.getParameterMap();
        Map<String, Object> returnMap = new HashMap<>();
        Iterator<?> entries = properties.entrySet().iterator();
        Map.Entry<?, ?> entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry<?, ?>) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                value = StringHelper.join(values,",");
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    /**
     * 获取请求参数
     *
     * @param request
     * @return
     */
    public static Map<String, Object> getParams(HttpServletRequest request) {
        Map<String, Object> map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }

    /**
     * 获取请求参数
     *
     * @param request
     * @return
     */
    public static Map<String, Object> paramMaps(HttpServletRequest request) {
        Map<String, String[]> properties = request.getParameterMap();
        Map<String, Object> returnMap = new HashMap<>();
        Iterator<?> entries = properties.entrySet().iterator();
        Map.Entry<?, ?> entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry<?, ?>) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                value = StringHelper.join(values,",");
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    /**
     * 获取整型参数
     *
     * @param request
     * @param paramName
     * @return
     */
    public static Integer paramInteger(HttpServletRequest request, String paramName) {
        String parameter = request.getParameter(paramName);
        Integer value = null;
        try {
            value = Integer.parseInt(parameter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取客户端请求信息
     */
    public static ClientInfo clientInfo(HttpServletRequest request) {
        ClientInfo info = new ClientInfo();
        info.setReqTime(System.currentTimeMillis());
        info.setClientIp(getIpAddr(request));
        info.setReferer(request.getHeader("referer"));
        info.setUri(request.getRequestURI());


        String ua = request.getHeader("User-Agent");
        info.setUserAgents(ua);
        ua = ua.toUpperCase();


        if (ua.contains("WINDOWS NT 5.1")) {
            info.setPlatform("WindowsVista");
        }
        if (ua.contains("WINDOWS NT 6.1")) {
            info.setPlatform("Windows7");
        }
        if (ua.contains("WINDOWS NT 6.2")) {
            info.setPlatform("Windows8");
        }
        if (ua.contains("WINDOWS NT 10")) {
            info.setPlatform("Windows10");
        }
        if (ua.contains("MAC")) {
            info.setPlatform("MacOS");
        }
        if (ua.contains("LINUX")) {
            info.setPlatform("Linux");
        }
        if (ua.contains("ANDROID")) {
            info.setPlatform("android");
        }
        if (ua.contains("IPHONE")) {
            info.setPlatform("ios");
        }
        if (ua.contains("IOS")) {
            info.setPlatform("ios");
        }

        //if (!multiRequest) {
        //    Map<String, Object> params = RequestHelper.getParams(request);
        //    info.setParams(params);
        //    // 转换request
        //    //MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        //    //// 获得文件
        //    //Map<String, MultipartFile> fileMap = multiRequest.getFileMap();
        //    //if(fileMap != null && fileMap.size() > 0){
        //    //    params = new HashMap<>();
        //    //    for (Map.Entry<String,MultipartFile> file:fileMap.entrySet()){
        //    //        MultipartFile ff = file.getValue();
        //    //        params.put("fileName",ff.getOriginalFilename());
        //    //        params.put("fileSize",ff.getSize());
        //    //    }
        //    //}
        //}
        return info;
    }
}
