package com.opslab.util;

import com.opslab.Opslab;
import com.opslab.helper.SysHepler;
import org.junit.Ignore;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

@Ignore
public class CharsetUtilTest {


    @Test
    public void test() throws UnsupportedEncodingException, CharacterCodingException {

        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("file.encoding=" + System.getProperty("file.encoding"));
        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("Default Charset in Use=" + CharsetUtil.getDefaultCharSet());
        System.out.println("JVM_ENCODING ->" + SysHepler.JVM_ENCODING);
        String str = "中文的字符串,编码结果会应项目的编译和JVM的运行环境有所影响";
        String gbk = CharsetUtil.toGBK(str);
        System.out.println("转换成GBK码: " + gbk);
        String ascii = CharsetUtil.toASCII(str);
        System.out.println("转换成US-ASCII码: " + ascii);
        gbk = CharsetUtil.changeCharset(ascii, Opslab.US_ASCII, Opslab.GBK);
        System.out.println("再把ASCII码的字符串转换成GBK码: " + gbk);
        String iso88591 = CharsetUtil.toISO_8859_1(str);
        System.out.println("转换成ISO-8859-1码: " + iso88591);
        gbk = CharsetUtil.changeCharset(iso88591, Opslab.ISO_8859_1, Opslab.GBK);
        System.out.println("再把ISO-8859-1码的字符串转换成GBK码: " + gbk);
        String utf8 = CharsetUtil.toUTF_8(str);
        System.out.println("转换成UTF-8码: " + utf8);
        gbk = CharsetUtil.changeCharset(utf8, Opslab.UTF_8, Opslab.GBK);
        System.out.println("再把UTF-8码的字符串转换成GBK码: " + gbk);
        String utf16be = CharsetUtil.toUTF_16BE(str);
        System.out.println("转换成UTF-16BE码:" + utf16be);
        gbk = CharsetUtil.changeCharset(utf16be, Opslab.UTF_16BE, Opslab.GBK);
        System.out.println("再把UTF-16BE码的字符串转换成GBK码: " + gbk);
        System.out.println();
        String utf16le = CharsetUtil.toUTF_16LE(str);
        System.out.println("转换成UTF-16LE码:" + utf16le);
        gbk = CharsetUtil.changeCharset(utf16le, Opslab.UTF_16LE, Opslab.GBK);
        System.out.println("再把UTF-16LE码的字符串转换成GBK码: " + gbk);
        System.out.println();
        String utf16 = CharsetUtil.toUTF_16(str);
        System.out.println("转换成UTF-16码:" + utf16);
        gbk = CharsetUtil.changeCharset(utf16, Opslab.UTF_16LE, Opslab.GBK);
        System.out.println("再把UTF-16码的字符串转换成GBK码: " + gbk);
        String s = new String("中文".getBytes(Opslab.UTF_8), Opslab.UTF_8);
        System.out.println(s);
    }
}
