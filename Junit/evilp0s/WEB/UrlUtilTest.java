package evilp0s.WEB;

import evilp0s.CharsetUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.UnsupportedEncodingException;


public class UrlUtilTest extends TestCase {

    @Test
    public void testEncode() {
        assertEquals("/aaa", UrlUtil.encode("/aaa"));
        assertEquals("/aaa?", UrlUtil.encode("/aaa?"));
        assertEquals("/aaa?b", UrlUtil.encode("/aaa?b"));
        assertEquals("/aaa?b=", UrlUtil.encode("/aaa?b="));
        assertEquals("/aaa?b=c", UrlUtil.encode("/aaa?b=c"));
        assertEquals("/aaa?b=%20c", UrlUtil.encode("/aaa?b= c"));
        assertEquals("/aaa?b=%20c&", UrlUtil.encode("/aaa?b= c&"));
        assertEquals("/aaa?b=%20c&dd", UrlUtil.encode("/aaa?b= c&dd"));
        assertEquals("/aaa?b=%20c&dd=", UrlUtil.encode("/aaa?b= c&dd="));
        assertEquals("/aaa?b=%20%20c&dd==", UrlUtil.encode("/aaa?b=  c&dd=="));
        assertEquals("?data=The%20string%20%C3%BC@foo-bar", UrlUtil.encode("?data=The string ü@foo-bar"));
    }

    @Test
    public void testDecode() {
        assertEquals("/aaa", UrlUtil.decode("/aaa"));
        assertEquals("/aaa?", UrlUtil.decode("/aaa?"));
        assertEquals("/aaa?b", UrlUtil.decode("/aaa?b"));
        assertEquals("/aaa?b=", UrlUtil.decode("/aaa?b="));
        assertEquals("/aaa?b=c", UrlUtil.decode("/aaa?b=c"));
        assertEquals("/aaa?b= c", UrlUtil.decode("/aaa?b=%20c"));
        assertEquals("/aaa?b= c&", UrlUtil.decode("/aaa?b=%20c&"));
        assertEquals("/aaa?b= c&dd", UrlUtil.decode("/aaa?b=%20c&dd"));
        assertEquals("/aaa?b= c&dd=", UrlUtil.decode("/aaa?b=%20c&dd="));
        assertEquals("/aaa?b=  c&dd==", UrlUtil.decode("/aaa?b=%20%20c&dd=="));
        assertEquals("?data=The string ü@foo-bar", UrlUtil.decode("?data=The%20string%20%C3%BC@foo-bar"));
    }


    @Test
    public void testUrlBuilder() {
        assertEquals("http://www.google.com", UrlUtil.build("http://www.google.com").toString());
        assertEquals("https://www.google.com/search?q=java%26struts", UrlUtil.build("https://www.google.com/search").queryParam("q", "java&struts").toString());
        assertEquals("https://www.google.com/search?pa%20ram=jodd%2Bjava", UrlUtil.build("https://www.google.com/search").queryParam("pa ram", "jodd+java").toString());
        assertEquals("/foo?foo=one&bar=two", UrlUtil.build("/foo").queryParam("foo", "one").queryParam("bar", "two").toString());
    }

    @Test
    public void testQuerySimple() throws UnsupportedEncodingException {
        assertEquals("%E4%B8%AD%E5%9B%BD", UrlUtil.encodeQueryParam("中国"));    // utf8
        assertEquals("%D6%D0%B9%FA", UrlUtil.encodeQueryParam("中国", CharsetUtil.GBK));
        assertEquals("https://www.google.com/search?q=Java%20Util", UrlUtil.encodeHttpUrl("https://www.google.com/search?q=Java Util"));

        assertEquals("中国", UrlUtil.decode("%E4%B8%AD%E5%9B%BD"));
        assertEquals("中国", UrlUtil.decodeQuery("%E4%B8%AD%E5%9B%BD"));
        assertEquals("中国", UrlUtil.decode("%e4%b8%ad%e5%9b%bd"));
        assertEquals("中国", UrlUtil.decode("%D6%D0%B9%FA", CharsetUtil.GBK));
        assertEquals("中国", UrlUtil.decodeQuery("%D6%D0%B9%FA", CharsetUtil.GBK));
    }

}