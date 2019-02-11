package com.opslab.util.web;

import com.opslab.util.ConvertUtil;
import com.opslab.util.StringUtil;

import java.io.UnsupportedEncodingException;

/**
 * 提供Web相关的个工具类
 */
public final class WebUtil {


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
        char[] chars = ConvertUtil.bytesToChars(ConvertUtil.encodeBytes(str.getBytes(encoding), '%'));
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
        return UrlUtil.decodeQuery(str, encoding);
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


}
