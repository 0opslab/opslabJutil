package evilp0s;

import org.junit.Test;

import java.io.UnsupportedEncodingException;


public class CharsetTest {
    @Test
    public void test() throws UnsupportedEncodingException {
        String str = "This is a 中文的 String!";
        System.out.println("str: " + str);
        String gbk = CharsetUtil.toGBK(str);
        System.out.println("转换成GBK码: " + gbk);
        String ascii = CharsetUtil.toASCII(str);
        System.out.println("转换成US-ASCII码: " + ascii);
        gbk = CharsetUtil.changeCharset(ascii, CharsetUtil.US_ASCII, CharsetUtil.GBK);
        System.out.println("再把ASCII码的字符串转换成GBK码: " + gbk);
        String iso88591 = CharsetUtil.toISO_8859_1(str);
        System.out.println("转换成ISO-8859-1码: " + iso88591);
        gbk = CharsetUtil.changeCharset(iso88591, CharsetUtil.ISO_8859_1, CharsetUtil.GBK);
        System.out.println("再把ISO-8859-1码的字符串转换成GBK码: " + gbk);
        String utf8 = CharsetUtil.toUTF_8(str);
        System.out.println("转换成UTF-8码: " + utf8);
        gbk = CharsetUtil.changeCharset(utf8, CharsetUtil.UTF_8, CharsetUtil.GBK);
        System.out.println("再把UTF-8码的字符串转换成GBK码: " + gbk);
        String utf16be = CharsetUtil.toUTF_16BE(str);
        System.out.println("转换成UTF-16BE码:" + utf16be);
        gbk = CharsetUtil.changeCharset(utf16be, CharsetUtil.UTF_16BE, CharsetUtil.GBK);
        System.out.println("再把UTF-16BE码的字符串转换成GBK码: " + gbk);
        System.out.println();
        String utf16le = CharsetUtil.toUTF_16LE(str);
        System.out.println("转换成UTF-16LE码:" + utf16le);
        gbk = CharsetUtil.changeCharset(utf16le, CharsetUtil.UTF_16LE, CharsetUtil.GBK);
        System.out.println("再把UTF-16LE码的字符串转换成GBK码: " + gbk);
        System.out.println();
        String utf16 = CharsetUtil.toUTF_16(str);
        System.out.println("转换成UTF-16码:" + utf16);
        gbk = CharsetUtil.changeCharset(utf16, CharsetUtil.UTF_16LE, CharsetUtil.GBK);
        System.out.println("再把UTF-16码的字符串转换成GBK码: " + gbk);
        String s = new String("中文".getBytes("UTF-8"), "UTF-8");
        System.out.println(s);
    }
}
