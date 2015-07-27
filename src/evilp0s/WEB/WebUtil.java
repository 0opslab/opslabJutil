package evilp0s.WEB;

import evilp0s.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 *  提供Web相关的个工具类
 */
public class WebUtil {
    /**
     * 打印请求参数
     * @param request http请求
     */
    public static Map print(HttpServletRequest request) {
        Map<String,String>  map        = new HashMap<>();
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

    public static String escape(String src) {
        int          i;
        char         j;
        StringBuilder tmp = new StringBuilder();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int  lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }


    public final static String setParam(String url, String paramName, String paramValue) {
        int temp_index = url.indexOf("?");
        if (temp_index != -1) {
            int param_index = url.indexOf(paramName + "=", temp_index + 1);
            if (param_index != -1) {
                temp_index = url.indexOf("&", param_index + paramName.length() + 1);
                if (temp_index != -1) {
                    return url.substring(0, param_index) + paramName + "=" + paramValue + url.substring(temp_index);
                }
                return url.substring(0, param_index) + paramName + "=" + paramValue;
            } else {
                if (url.lastIndexOf("&") == url.length() - 1) {
                    return url + paramName + "=" + paramValue;
                }
                return url + "&" + paramName + "=" + paramValue;
            }
        } else {
            return url + "?" + paramName + "=" + paramValue;
        }
    }

    public static final String getParamValue(String url, String paramName) {
        int temp_index = url.indexOf("?");
        if (temp_index != -1) {
            int param_index = url.indexOf(paramName + "=", temp_index + 1);
            if (param_index != -1) {
                temp_index = url.indexOf("&", param_index + paramName.length() + 1);
                if (temp_index != -1) {
                    return url.substring(param_index + paramName.length() + 1, temp_index);
                }
                return url.substring(param_index + paramName.length() + 1);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static final String removeParam(String url, String... paramNames) {
        for (String paramName : paramNames) {
            url = removeParam(url, paramName);
        }
        return url;
    }

    public static final String removeParam(String url, String paramName) {
        int temp_index = url.indexOf("?");
        if (temp_index != -1) {
            int param_index = url.indexOf(paramName + "=", temp_index + 1);
            if (param_index != -1) {
                temp_index = url.indexOf("&", param_index + paramName.length() + 1);
                if (temp_index != -1) {
                    return url.substring(0, param_index) + url.substring(temp_index + 1);
                }
                return url.substring(0, param_index - 1);

            } else {
                return url;
            }
        } else {
            return url;
        }
    }


    /**
     * Example:
     * <pre>
     * {@code
     * url: http://tt.se/                 Location: /start              =>  http://tt.se/start
     * url: http://localhost/moved_perm   Location: /                   =>  http://localhost/
     * url: http://github.com/            Location: http://github.com/  =>  https://github.com/
     * }
     *
     * (If the new url throws a MalformedURLException the url String representation will be returned.)
     */
    public static String urlJoin(URL url, String locationHeader) {
        try {
            if (locationHeader.startsWith("http")) {
                return new URL(locationHeader).toString();
            }
            return new URL(url.getProtocol() + "://" + url.getAuthority() + locationHeader).toString();
        } catch (MalformedURLException e) {
            return url.toString();
        }

    }


    /**
     * HTML标签转义方法 —— java代码库
     *
     * @param content
     * @return
     */
    public static String html(String content) {
        if(StringUtil.isEmpty(content)){
            return "";
        }
        String html = content;
        html = html.replaceAll("'", "&apos;");
        html = html.replaceAll("\"", "&quot;");
        html = html.replaceAll("\t", "&nbsp;&nbsp;");// 替换跳格
        html = html.replaceAll("<", "&lt;");
        html = html.replaceAll(">", "&gt;");
        return html;
    }

    /**
     * 解析字符串返回map键值对(例：a=1&b=2 => a=1,b=2)
     *
     * @param query   源参数字符串
     * @param split1  键值对之间的分隔符（例：&）
     * @param split2  key与value之间的分隔符（例：=）
     * @param dupLink 重复参数名的参数值之间的连接符，连接后的字符串作为该参数的参数值，可为null
     *                null：不允许重复参数名出现，则靠后的参数值会覆盖掉靠前的参数值。
     * @return map
     */
    public static Map<String,String> parseQuery(String query, char split1, char split2, String dupLink) {
        if (!StringUtil.isEmpty(query) && query.indexOf(split2) > 0) {
            Map<String,String> result = new HashMap<>();

            String name = null;
            String value = null;
            String tempValue = "";
            int len = query.length();
            for (int i = 0; i < len; i++) {
                char c = query.charAt(i);
                if (c == split2) {
                    value = "";
                } else if (c == split1) {
                    if (!StringUtil.isEmpty(name) && value != null) {
                        if (dupLink != null) {
                            tempValue = result.get(name);
                            if (tempValue != null) {
                                value += dupLink + tempValue;
                            }
                        }
                        result.put(name, value);
                    }
                    name = null;
                    value = null;
                } else if (value != null) {
                    value += c;
                } else {
                    name = (name != null) ? (name + c) : "" + c;
                }
            }

            if (!StringUtil.isEmpty(name) && value != null) {
                if (dupLink != null) {
                    tempValue = result.get(name);
                    if (tempValue != null) {
                        value += dupLink + tempValue;
                    }
                }
                result.put(name, value);
            }

            return result;
        }
        return null;
    }

    public static Map<String,String> httpParseQuery(String queryUri){
        Map<String,String> result = new HashMap<>();
        if(!StringUtil.isEmpty(queryUri)){
            result = parseQuery(queryUri,'&','=',",");
        }
        return result;
    }
}
