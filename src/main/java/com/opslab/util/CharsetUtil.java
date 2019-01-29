package com.opslab.util;

import com.opslab.Opslab;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * Description:
 * 编码相关的封装类
 */
public final class CharsetUtil {


    /**
     * 将字符编码转换成US-ASCII码
     */
    public final static String toASCII(String str) throws UnsupportedEncodingException {
        return changeCharset(str, Opslab.US_ASCII);
    }

    /**
     * 将字符编码转换成ISO-8859-1码
     */
    public final static String toISO_8859_1(String str) throws UnsupportedEncodingException {
        return changeCharset(str, Opslab.ISO_8859_1);
    }

    /**
     * 将字符编码转换成UTF-8码
     */
    public static String toUTF_8(String str) throws UnsupportedEncodingException {
        return changeCharset(str, Opslab.UTF_8);
    }

    /**
     * 将字符编码转换成UTF-16BE码
     */
    public final static String toUTF_16BE(String str) throws UnsupportedEncodingException {
        return changeCharset(str, Opslab.UTF_16BE);
    }

    /**
     * 将字符编码转换成UTF-16LE码
     */
    public final static String toUTF_16LE(String str) throws UnsupportedEncodingException {
        return changeCharset(str, Opslab.UTF_16LE);
    }

    /**
     * 将字符编码转换成UTF-16码
     */
    public final static String toUTF_16(String str) throws UnsupportedEncodingException {
        return changeCharset(str, Opslab.UTF_16);
    }

    /**
     * 将字符编码转换成GBK码
     */
    public final static String toGBK(String str) throws UnsupportedEncodingException {
        return changeCharset(str, Opslab.GBK);
    }

    /**
     * 字符串编码转换的实现方法
     *
     * @param str        待转换编码的字符串
     * @param newCharset 目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public final static String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {
        if (str != null) {
            // 用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            // 用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }

    public final static String getDefaultCharSet() throws UnsupportedEncodingException {
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream(), Opslab.UTF_8);
        String enc = writer.getEncoding();
        return enc;
    }

    /**
     * 字符串编码转换的实现方法
     *
     * @param str        待转换编码的字符串
     * @param oldCharset 原编码
     * @param newCharset 目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public final static String changeCharset(String str, String oldCharset,
                                             String newCharset) throws UnsupportedEncodingException {
        if (str != null) {
            // 用旧的字符编码解码字符串。解码可能会出现异常。
            byte[] bs = str.getBytes(oldCharset);
            // 用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }

    /**
     * Unicode转换成GBK字符集
     *
     * @param input 待转换字符串
     * @return 转换完成字符串
     */
    public final static String toGBKWithUTF8(String input) throws UnsupportedEncodingException {
        if (StringUtil.isEmpty(input)) {
            return "";
        } else {
            String s1;
            s1 = new String(input.getBytes(Opslab.ISO_8859_1), Opslab.GBK);
            return s1;
        }
    }

    /**
     * GBK转换成Unicode字符集
     *
     * @param input 待转换字符串
     * @return 转换完成字符串
     */
    public final static String toUnicodeWithGBK(String input) throws UnsupportedEncodingException {
        if (StringUtil.isEmpty(input)) {
            return "";
        } else {
            String s1;
            s1 = new String(input.getBytes(Opslab.GBK), Opslab.ISO_8859_1);
            return s1;
        }
    }

}