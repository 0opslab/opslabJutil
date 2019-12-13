package com.opslab.helper;

import com.opslab.Opslab;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.UnsupportedEncodingException;


public class WebHeplerTest extends TestCase {

    @Test
    public void testhtml() {
        String html = "<div class=\"ipaddress\">服务器IP地址：</div>";
        System.out.println(WebHelper.unhtml(html));
        assertEquals(html, WebHelper.html(WebHelper.unhtml(html)));
    }

    @Test
    public void testescapeEncoding() throws UnsupportedEncodingException {
        String unescape = "<div class=\"ipaddress\">服务器IP地址：</div>";
        String gbk = "%3C%64%69%76%20%63%6C%61%73%73%3D%22%69%70%61%64%64%72%65%73" +
                "%73%22%3E%B7%FE%CE%F1%C6%F7%49%50%B5%D8%D6%B7%A3%BA%3C%2F%64%69%76%3E";
        String utf8 = "%3C%64%69%76%20%63%6C%61%73%73%3D%22%69%70%61%64%64%72%65%73%" +
                "73%22%3E%E6%9C%8D%E5%8A%A1%E5%99%A8%49%50%E5%9C%B0%E5%9D%80%EF%BC" +
                "%9A%3C%2F%64%69%76%3E";
        //GBK
        assertEquals(gbk, WebHelper.escape(unescape, Opslab.GBK));
        assertEquals(unescape, WebHelper.unescape(gbk, Opslab.GBK));

        //utf8
        assertEquals(utf8, WebHelper.escape(unescape, Opslab.UTF_8));
        assertEquals(unescape, WebHelper.unescape(utf8, Opslab.UTF_8));
    }


}