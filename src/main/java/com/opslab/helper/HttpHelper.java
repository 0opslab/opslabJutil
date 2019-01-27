package com.opslab.helper;


import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * 封装常见的HTTP方法
 */
public class HttpHelper {


    /**
     * 将所有的请求参数转换字符串
     * 例如:
     * uri?param1=111&param2=111,222
     *
     * @param request
     * @return
     */
    public static String requestParamsToString(ServletRequest request) {
        StringBuffer sbuf = new StringBuffer();
        Map<String, String[]> map = request.getParameterMap();
        if (map == null || map.size() == 0) {
            return "";
        }
        int i = 0;

        String arrayJoiner = ",";
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String joiner = "&";
            if (i == 0) {
                joiner = "?";
            }
            String key = entry.getKey();
            String[] value = entry.getValue();
            if (value != null && value.length == 1) {
                sbuf.append(joiner + key + "=" + value[0]);
            } else {
                sbuf.append(joiner + key + "=" + StringHelper.join(value, arrayJoiner));
            }
        }
        return sbuf.toString();
    }


}
