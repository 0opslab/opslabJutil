package evilp0s;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author:Neptune
 * @Description:WebUtil 提供Web相关的个工具类
 */
public class WebUtil {
    /**
     * @param request
     * @Method:打印请求参数
     */
    public static void print(HttpServletRequest request) {
        Map map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    System.out.println("\t\t->" + paramName + "=" + paramValue);
                    map.put(paramName, paramValue);
                }
            }
        }
    }

    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
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
        int lastPos = 0, pos = 0;
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

    public static void main(String[] args) {
        StringBuffer html = new StringBuffer();
        StringBuffer result = new StringBuffer();

        html.append("<div class=\"ipaddress\">服务器IP地址：</div>");
        html.append("<div class=\"ipinput\">");
        html.append("<input type=\"text\" value=\"127.0.5.5.0\" class=\"text\" />");
//		html.append("<input type=\"text\" value=\""+GameCache.getCache().getServerbean().getServeraddress()+"\" class=\"text\" />");
        html.append("<input type=\"button\" value=\"修改\" class=\"button\" onclick=\"\"/>");
        html.append("</div>");
        html.append("<div class=\"iptip\">");
        html.append("<span class=\"red\">说明：</span>");
        html.append("<span class=\"content\">①该服务器IP为你租用的服务器IP地址,如果本机直接为127.0.0.1</span>");
        html.append("<span class=\"content\">②修改服务器IP，会导致所有的GameServer重启，以便重新加载配置</span>");
        html.append("</div>");
        String unescape = html.toString();
        String escape = escape(unescape);
        System.out.println(escape);
        unescape = unescape(escape);
        System.out.println(unescape);
        System.out.println(unescape.equals(html.toString()));

        System.out.println(escape("123 123"));

        String url = "http://www.baidu.com/page.jsp?act=list&ad=12&redirect=true";

        //设置参数
        System.out.println(setParam(url, "act", "listnew"));

        //移除单个参数
        System.out.println(removeParam(url, "act"));

        //移除多个参数
        System.out.println(removeParam(url, "act", "ad", "redirect"));

    }
}
