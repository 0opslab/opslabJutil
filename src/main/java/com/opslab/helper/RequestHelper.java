package com.opslab.helper;

import com.opslab.bean.ClientInfo;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 封装常见的Request方法
 */
public class RequestHelper {
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
        Map<String, Object> returnMap = new HashMap<String, Object>();
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
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
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
        Map<String, Object> returnMap = new HashMap<String, Object>();
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
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
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
