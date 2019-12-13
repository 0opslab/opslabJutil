package com.opslab.helper;

import com.opslab.Opslab;
import com.opslab.util.StringUtil;
import com.opslab.util.web.URIPart;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * URL相关的工具类
 * 以下工具类来自开源项目jodd
 * <p/>
 * Here is an example of full URL:
 * {@literal https://jodd:ddoj@www.jodd.org:8080/file;p=1?q=2#third}.
 * It consist of:
 * <ul>
 * <li>scheme (https)</li>
 * <li>user (jodd)</li>
 * <li>password (ddoj)</li>
 * <li>host (www.jodd.org)</li>
 * <li>port (8080)</li>
 * <li>path (file)</li>
 * <li>path parameter (p=1)</li>
 * <li>query parameter (q=2)</li>
 * <li>fragment (third)</li>
 * </ul>
 */
public final class UrlHelper {
    private static final String SCHEME_PATTERN = "([^:/?#]+):";

    private static final String HTTP_PATTERN = "(http|https):";

    private static final String USERINFO_PATTERN = "([^@/]*)";

    private static final String HOST_PATTERN = "([^/?#:]*)";

    private static final String PORT_PATTERN = "(\\d*)";

    private static final String PATH_PATTERN = "([^?#]*)";

    private static final String QUERY_PATTERN = "([^#]*)";

    private static final String LAST_PATTERN = "(.*)";

    // Regex patterns that matches URIs. See RFC 3986, appendix B

    private static final Pattern URI_PATTERN = Pattern.compile("^(" + SCHEME_PATTERN + ")?" + "(//(" + USERINFO_PATTERN + "@)?" + HOST_PATTERN + "(:" + PORT_PATTERN +
            ")?" + ")?" + PATH_PATTERN + "(\\?" + QUERY_PATTERN + ")?" + "(#" + LAST_PATTERN + ")?");

    private static final Pattern HTTP_URL_PATTERN = Pattern.compile('^' + HTTP_PATTERN + "(//(" + USERINFO_PATTERN + "@)?" + HOST_PATTERN + "(:" + PORT_PATTERN + ")?" + ")?" +
            PATH_PATTERN + "(\\?" + LAST_PATTERN + ")?");

    /**
     * Encodes single URI component.
     */
    private static String encodeUriComponent(String source, String encoding, URIPart uriPart) {
        if (source == null) {
            return null;
        }

        byte[] bytes;
        try {
            bytes = encodeBytes(source.getBytes(encoding), uriPart);
        } catch (UnsupportedEncodingException ignore) {
            return null;
        }

        char[] chars = new char[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            chars[i] = (char) bytes[i];
        }
        return new String(chars);
    }

    /**
     * Encodes byte array using allowed characters from {@link URIPart}.
     */
    private static byte[] encodeBytes(byte[] source, URIPart uriPart) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(source.length);
        for (byte b : source) {
            if (b < 0) {
                b += 256;
            }
            if (uriPart.isValid((char) b)) {
                bos.write(b);
            } else {
                bos.write('%');
                char hex1 = Character.toUpperCase(Character.forDigit((b >> 4) & 0xF, 16));
                char hex2 = Character.toUpperCase(Character.forDigit(b & 0xF, 16));
                bos.write(hex1);
                bos.write(hex2);
            }
        }
        return bos.toByteArray();
    }

    /**
     * Encodes the given URI scheme with the given encoding.
     */
    public static String encodeScheme(String scheme, String encoding) {
        return encodeUriComponent(scheme, encoding, URIPart.SCHEME);
    }

    public static String encodeScheme(String scheme) {
        return encodeUriComponent(scheme, Opslab.UTF_8, URIPart.SCHEME);
    }

    /**
     * Encodes the given URI user info with the given encoding.
     */
    public static String encodeUserInfo(String userInfo, String encoding) {
        return encodeUriComponent(userInfo, encoding, URIPart.USER_INFO);
    }

/*	/**
     * Encodes the given URI authority with the given encoding.
	 *

	public static String encodeAuthority(String authority, String encoding) {
		return encodeUriComponent(authority, encoding, URIPart.AUTHORITY);
	}
	public static String encodeAuthority(String authority) {
		return encodeUriComponent(authority, JoddCore.encoding, URIPart.AUTHORITY);
	}
*/

    public static String encodeUserInfo(String userInfo) {
        return encodeUriComponent(userInfo, Opslab.UTF_8, URIPart.USER_INFO);
    }

    /**
     * Encodes the given URI host with the given encoding.
     */
    public static String encodeHost(String host, String encoding) {
        return encodeUriComponent(host, encoding, URIPart.HOST);
    }

    public static String encodeHost(String host) {
        return encodeUriComponent(host, Opslab.UTF_8, URIPart.HOST);
    }

    /**
     * Encodes the given URI port with the given encoding.
     */
    public static String encodePort(String port, String encoding) {
        return encodeUriComponent(port, encoding, URIPart.PORT);
    }

    public static String encodePort(String port) {
        return encodeUriComponent(port, Opslab.UTF_8, URIPart.PORT);
    }

    /**
     * Encodes the given URI path with the given encoding.
     */
    public static String encodePath(String path, String encoding) {
        return encodeUriComponent(path, encoding, URIPart.PATH);
    }

    public static String encodePath(String path) {
        return encodeUriComponent(path, Opslab.UTF_8, URIPart.PATH);
    }

    /**
     * Encodes the given URI path segment with the given encoding.
     */
    public static String encodePathSegment(String segment, String encoding) {
        return encodeUriComponent(segment, encoding, URIPart.PATH_SEGMENT);
    }

    public static String encodePathSegment(String segment) {
        return encodeUriComponent(segment, Opslab.UTF_8, URIPart.PATH_SEGMENT);
    }

    /**
     * Encodes the given URI query with the given encoding.
     */
    public static String encodeQuery(String query, String encoding) {
        return encodeUriComponent(query, encoding, URIPart.QUERY);
    }

    public static String encodeQuery(String query) {
        return encodeUriComponent(query, Opslab.UTF_8, URIPart.QUERY);
    }

    /**
     * Encodes the given URI query parameter with the given encoding.
     */
    public static String encodeQueryParam(String queryParam, String encoding) {
        return encodeUriComponent(queryParam, encoding, URIPart.QUERY_PARAM);
    }

    public static String encodeQueryParam(String queryParam) {
        return encodeUriComponent(queryParam, Opslab.UTF_8, URIPart.QUERY_PARAM);
    }

    /**
     * Encodes the given URI fragment with the given encoding.
     */
    public static String encodeFragment(String fragment, String encoding) {
        return encodeUriComponent(fragment, encoding, URIPart.FRAGMENT);
    }

    public static String encodeFragment(String fragment) {
        return encodeUriComponent(fragment, Opslab.UTF_8, URIPart.FRAGMENT);
    }

    /**
     * @see #encode(String, String)
     */
    public static String encode(String uri) {
        return encode(uri, Opslab.UTF_8);
    }

    /**
     * Encodes the given source URI into an encoded String. All various URI components are
     * encoded according to their respective valid character sets.
     * <p>This method does <b>not</b> attempt to encode "=" and "{@literal &}"
     * characters in query parameter names and query parameter values because they cannot
     * be parsed in a reliable way.
     */
    public static String encode(String uri, String encoding) {
        Matcher m = URI_PATTERN.matcher(uri);
        if (m.matches()) {
            String scheme = m.group(2);
            String authority = m.group(3);
            String userinfo = m.group(5);
            String host = m.group(6);
            String port = m.group(8);
            String path = m.group(9);
            String query = m.group(11);
            String fragment = m.group(13);

            return encodeUriComponents(scheme, authority, userinfo, host, port, path, query, fragment, encoding);
        }
        throw new IllegalArgumentException("Invalid URI: " + uri);
    }

    /**
     * @see #encodeHttpUrl(String, String)
     */
    public static String encodeHttpUrl(String httpUrl) {
        return encodeHttpUrl(httpUrl, Opslab.UTF_8);
    }

    /**
     * Encodes the given HTTP URI into an encoded String. All various URI components are
     * encoded according to their respective valid character sets.
     * <p>This method does <b>not</b> support fragments ({@code #}),
     * as these are not supposed to be sent to the server, but retained by the client.
     * <p>This method does <b>not</b> attempt to encode "=" and "{@literal &}"
     * characters in query parameter names and query parameter values because they cannot
     * be parsed in a reliable way.
     */
    public static String encodeHttpUrl(String httpUrl, String encoding) {
        Matcher m = HTTP_URL_PATTERN.matcher(httpUrl);
        if (m.matches()) {
            String scheme = m.group(1);
            String authority = m.group(2);
            String userinfo = m.group(4);
            String host = m.group(5);
            String portString = m.group(7);
            String path = m.group(8);
            String query = m.group(10);

            return encodeUriComponents(scheme, authority, userinfo, host, portString, path, query, null, encoding);
        }
        throw new IllegalArgumentException("Invalid HTTP URL: " + httpUrl);
    }

    private static String encodeUriComponents(String scheme, String authority, String userInfo, String host,
                                              String port, String path, String query, String fragment, String encoding) {

        StringBuilder sb = new StringBuilder();

        if (scheme != null) {
            sb.append(encodeScheme(scheme, encoding));
            sb.append(':');
        }

        if (authority != null) {
            sb.append("//");
            if (userInfo != null) {
                sb.append(encodeUserInfo(userInfo, encoding));
                sb.append('@');
            }
            if (host != null) {
                sb.append(encodeHost(host, encoding));
            }
            if (port != null) {
                sb.append(':');
                sb.append(encodePort(port, encoding));
            }
        }

        sb.append(encodePath(path, encoding));

        if (query != null) {
            sb.append('?');
            sb.append(encodeQuery(query, encoding));
        }

        if (fragment != null) {
            sb.append('#');
            sb.append(encodeFragment(fragment, encoding));
        }

        return sb.toString();
    }

    /**
     * Creates URL builder for user-friendly way of building URLs.
     * Provided path is parsed and {@link #encode(String) encoded}.
     *
     * @see #build(String, boolean)
     */
    public static Builder build(String path) {
        return build(path, true);
    }

    /**
     * Creates URL builder with given path that can be optionally encoded.
     * Since most of the time path is valid and does not require to be encoded,
     * use this method to gain some performance. When encoding flag is turned off,
     * provided path is used without processing.
     * <p/>
     * The purpose of builder is to help with query parameters. All other URI parts
     * should be set previously or after the URL is built.
     */
    public static Builder build(String path, boolean encodePath) {
        return new Builder(path, encodePath, Opslab.UTF_8);
    }

    /**
     * Decodes URL elements.
     */
    public static String decode(String url) {
        return decode(url, Opslab.UTF_8, false);
    }

    /**
     * Decodes URL elements. This method may be used for all
     * parts of URL, except for the query parts, since it does
     * not decode the '+' character.
     *
     * @see #decodeQuery(String, String)
     */
    public static String decode(String source, String encoding) {
        return decode(source, encoding, false);
    }

    /**
     * Decodes query name or value.
     */
    public static String decodeQuery(String source) {
        return decode(source, Opslab.UTF_8, true);
    }

    /**
     * Decodes query name or value.
     */
    public static String decodeQuery(String source, String encoding) {
        return decode(source, encoding, true);
    }

    private static String decode(String source, String encoding, boolean decodePlus) {
        int length = source.length();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(length);

        boolean changed = false;

        for (int i = 0; i < length; i++) {
            int ch = source.charAt(i);
            switch (ch) {
                case '%':
                    if ((i + 2) < length) {
                        char hex1 = source.charAt(i + 1);
                        char hex2 = source.charAt(i + 2);
                        int u = Character.digit(hex1, 16);
                        int l = Character.digit(hex2, 16);
                        if (u == -1 || l == -1) {
                            throw new IllegalArgumentException("Invalid sequence: " + source.substring(i));
                        }
                        bos.write((char) ((u << 4) + l));
                        i += 2;
                        changed = true;
                    } else {
                        throw new IllegalArgumentException("Invalid sequence: " + source.substring(i));
                    }
                    break;

                case '+':
                    if (decodePlus) {
                        ch = ' ';
                        changed = true;
                    }

                default:
                    bos.write(ch);
            }
        }
        try {
            return changed ? new String(bos.toByteArray(), encoding) : source;
        } catch (UnsupportedEncodingException ignore) {
            return null;
        }
    }



    public static class Builder {
        protected final StringBuilder url;
        protected final String encoding;
        protected boolean hasParams;

        public Builder(String path, boolean encodePath, String encoding) {
            this.encoding = encoding;
            url = new StringBuilder();
            if (encodePath) {
                url.append(encode(path, encoding));
            } else {
                url.append(path);
            }
            this.hasParams = url.indexOf("?") != -1;
        }

        /**
         * Appends new query parameter to the url.
         */
        public Builder queryParam(String name, String value) {
            url.append(hasParams ? '&' : '?');
            hasParams = true;

            url.append(encodeQueryParam(name, encoding));

            if ((value != null) && (value.length() > 0)) {
                url.append('=');
                url.append(encodeQueryParam(value, encoding));
            }
            return this;
        }

        /**
         * Returns full URL.
         */
        @Override
        public String toString() {
            return url.toString();
        }
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
     * 拼接url方法
     *
     *
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
     * 获取HTTPrequest的请求参数
     *
     * @param request http请求
     */
    public static Map print(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
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
     * 解析字符串返回map键值对(例：a=1&b=2 => a=1,b=2)
     *
     * @param query   源参数字符串
     * @param split1  键值对之间的分隔符（例：&）
     * @param split2  key与value之间的分隔符（例：=）
     * @param dupLink 重复参数名的参数值之间的连接符，连接后的字符串作为该参数的参数值，可为null
     *                null：不允许重复参数名出现，则靠后的参数值会覆盖掉靠前的参数值。
     * @return map
     */
    public static Map<String, String> parseQuery(String query, char split1, char split2, String dupLink) {
        if (!StringUtil.isEmpty(query) && query.indexOf(split2) > 0) {
            Map<String, String> result = new HashMap<>();

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

    /**
     * 解析http请求URI
     *
     * @param queryUri http请求的uri
     */
    public static Map<String, String> httpParseQuery(String queryUri) {
        Map<String, String> result = new HashMap<>();
        if (!StringUtil.isEmpty(queryUri)) {
            result = parseQuery(queryUri, '&', '=', ",");
        }
        return result;
    }
}
