package com.opslab.helper;

import com.opslab.util.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

/**
 * web相关的一些操作方法
 */
public class WebHelper {


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
     */
    public static String replaceXSS(String value) {
        if (value != null) {
            try {
                value = value.replace("+", "%2B");   //'+' replace to '%2B'
                value = URLDecoder.decode(value, "utf-8");
            } catch (UnsupportedEncodingException e) {
            } catch (IllegalArgumentException e) {
            }

            // Avoid null characters
            value = value.replaceAll("\0", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid anything in a src='...' type of e­xpression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid eval(...) e­xpressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid e­xpression(...) e­xpressions
            scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid javascript:... e­xpressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid alert:... e­xpressions
            scriptPattern = Pattern.compile("alert", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid onload= e­xpressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("vbscript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
        }
        return filter(value);
    }

    /**
     * 过滤特殊字符
     */
    public static String filter(String value) {
        if (value == null) {
            return null;
        }
        StringBuffer result = new StringBuffer(value.length());
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
        return result.toString();
    }
}
