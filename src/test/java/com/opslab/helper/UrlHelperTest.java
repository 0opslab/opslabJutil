package com.opslab.helper;

import com.opslab.Opslab;
import com.opslab.helper.UrlHelper;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Map;


public class UrlHelperTest extends TestCase {

    @Test
    public void testParam() {
        String url = "http://www.baidu.com/page.jsp?act=list&ad=12&redirect=true";

        //设置参数
        System.out.println(UrlHelper.setParam(url, "act", "listnew"));

        //移除单个参数
        System.out.println(UrlHelper.removeParam(url, "act"));

        //移除多个参数
        System.out.println(UrlHelper.removeParam(url, "act", "ad", "redirect"));

    }

    @Test
    public void testEncode() {
        assertEquals("/aaa", UrlHelper.encode("/aaa"));
        assertEquals("/aaa?", UrlHelper.encode("/aaa?"));
        assertEquals("/aaa?b", UrlHelper.encode("/aaa?b"));
        assertEquals("/aaa?b=", UrlHelper.encode("/aaa?b="));
        assertEquals("/aaa?b=c", UrlHelper.encode("/aaa?b=c"));
        assertEquals("/aaa?b=%20c", UrlHelper.encode("/aaa?b= c"));
        assertEquals("/aaa?b=%20c&", UrlHelper.encode("/aaa?b= c&"));
        assertEquals("/aaa?b=%20c&dd", UrlHelper.encode("/aaa?b= c&dd"));
        assertEquals("/aaa?b=%20c&dd=", UrlHelper.encode("/aaa?b= c&dd="));
        assertEquals("/aaa?b=%20%20c&dd==", UrlHelper.encode("/aaa?b=  c&dd=="));
        assertEquals("?data=The%20string%20%C3%BC@foo-bar", UrlHelper.encode("?data=The string ü@foo-bar"));
    }

    @Test
    public void testDecode() {
        assertEquals("/aaa", UrlHelper.decode("/aaa"));
        assertEquals("/aaa?", UrlHelper.decode("/aaa?"));
        assertEquals("/aaa?b", UrlHelper.decode("/aaa?b"));
        assertEquals("/aaa?b=", UrlHelper.decode("/aaa?b="));
        assertEquals("/aaa?b=c", UrlHelper.decode("/aaa?b=c"));
        assertEquals("/aaa?b= c", UrlHelper.decode("/aaa?b=%20c"));
        assertEquals("/aaa?b= c&", UrlHelper.decode("/aaa?b=%20c&"));
        assertEquals("/aaa?b= c&dd", UrlHelper.decode("/aaa?b=%20c&dd"));
        assertEquals("/aaa?b= c&dd=", UrlHelper.decode("/aaa?b=%20c&dd="));
        assertEquals("/aaa?b=  c&dd==", UrlHelper.decode("/aaa?b=%20%20c&dd=="));
        assertEquals("?data=The string ü@foo-bar", UrlHelper.decode("?data=The%20string%20%C3%BC@foo-bar"));
    }


    @Test
    public void testUrlBuilder() {
        assertEquals("http://www.google.com", UrlHelper.build("http://www.google.com").toString());
        assertEquals("https://www.google.com/search?q=java%26struts", UrlHelper.build("https://www.google.com/search").queryParam("q", "java&struts").toString());
        assertEquals("https://www.google.com/search?pa%20ram=jodd%2Bjava", UrlHelper.build("https://www.google.com/search").queryParam("pa ram", "jodd+java").toString());
        assertEquals("/foo?foo=one&bar=two", UrlHelper.build("/foo").queryParam("foo", "one").queryParam("bar", "two").toString());
    }

    @Test
    public void testQuerySimple() throws UnsupportedEncodingException {
        assertEquals("%E4%B8%AD%E5%9B%BD", UrlHelper.encodeQueryParam("中国"));    // utf8
        assertEquals("%D6%D0%B9%FA", UrlHelper.encodeQueryParam("中国", Opslab.GBK));
        assertEquals("https://www.google.com/search?q=Java%20Util",
                UrlHelper.encodeHttpUrl("https://www.google.com/search?q=Java Util"));

        assertEquals("中国", UrlHelper.decode("%E4%B8%AD%E5%9B%BD"));
        assertEquals("中国", UrlHelper.decodeQuery("%E4%B8%AD%E5%9B%BD"));
        assertEquals("中国", UrlHelper.decode("%e4%b8%ad%e5%9b%bd"));
        assertEquals("中国", UrlHelper.decode("%D6%D0%B9%FA", Opslab.GBK));
        assertEquals("中国", UrlHelper.decodeQuery("%D6%D0%B9%FA", Opslab.GBK));
    }

    @Test
    public void testQueryParam() {
        String http_request = "\n" +
                "org.apache.struts.taglib.html.TOKEN=8e1c374155eaf63ef485547e78638ad9\n" +
                "&cmd=commit\n" +
                "&functionType=201\n" +
                "&operateType=01\n" +
                "&flowID=f1\n" +
                "&parentId=\n" +
                "&mainSheet.serviceType=02\n" +
                "&mainSheet.sendTimeLimit=4\n" +
                "&mainSheet.answerTimeLimit=2\n" +
                "&isHightG=0\n" +
                "&acceptRecord.beginTime=2014-12-29+10%3A34%3A45\n" +
                "&mainSheet.isPrePlan=\n" +
                "&prePlan_code=\n" +
                "&mainSheet.isImmed=0\n" +
                "&remedyProductInfo=\n" +
                "&isOnline=\n" +
                "&oldSheetCategory=1\n" +
                "&rdFlag=null\n" +
                "&mainSheet.rdtemplateid=\n" +
                "&mainSheet.categoryID=1\n" +
                "&mainSheet.callinTeleNO=13099753058\n" +
                "&acceptRecord.customerId=19267\n" +
                "&mainSheet.mobileTeleNO=13099753058\n" +
                "&mainSheet.customerId=\n" +
                "&customerInfo.customerName=%D5%C5%D3%F1%B7%E1\n" +
                "&mainSheet.vestAreaID=%CE%F7%C4%FE\n" +
                "&customerInfo.customerLevel=303\n" +
                "&customerInfo.subType=3000\n" +
                "&customerTypeGuwang=11\n" +
                "&customerInfo.networkTime=154\n" +
                "&customerInfo.mobileStatus=1\n" +
                "&customerInfo.lineType=%C8%CE%CE%D2%D0%D0\n" +
                "&acceptRecord.contactMan=%D5%C5%D3%F1%B7%E1\n" +
                "&acceptRecord.contactInfo=%C7%E0%BA%A3%CA%A1%B8%D5%B2%EC%CF%D8%C8%C8%CB%AE%C3%BA%BF%F3%D4%CB%CF%FA%BF%C6%28%D7%CA%C1%CF%D2%D1%B8%FC%D0%C2%29\n" +
                "&acceptRecord.touchFlowNo=13099753058\n" +
                "&acceptRecord.answerType=801\n" +
                "&acceptRecord.recordType=\n" +
                "&previousProblemTypeID=\n" +
                "&mainSheet.problemTypeID=NH1_01_010101\n" +
                "&mainSheet.sheetFlowNO=TS2014122952380\n" +
                "&mainSheet.endPrmReasons=NH1_01_010101\n" +
                "&mainSheet.productTypeID=8a7b88af2c05225a012c055d8ceb051c\n" +
                "&mainSheet.emergencyType=404\n" +
                "&mainSheet.timeLimit=24\n" +
                "&acceptRecord.accessType=701\n" +
                "&mainSheet.appealLevel=601\n" +
                "&customerInfo.specialIdentity=\n" +
                "&mainSheet.relationServerWarnid=\n" +
                "&mainSheet.spareContentField1=\n" +
                "&mainSheet.spareContentField2=\n" +
                "&mainSheet.returnfeeFlag=\n" +
                "&customerInfo.manufacName=%CE%F7%C4%FE\n" +
                "&get_content=3\n" +
                "&acceptRecord.acceptContent=%D3%C3%BB%A7%D2%AA%C7%F3%B0%B2%D7%B0%CE%D2%B9%AB%CB%BE%BF%ED%B4%F8%A3%A8%B9%CC%BB%B0%A3%A9%A3%AC%C7%EB%B9%F3%B2%BF%BA%CB%CA%B5%D3%D0%CE%DE%CF%DF%C2%B7%D7%CA%D4%B4%B2%A2%D4%DA%CA%B1%CF%DE%C4%DA%BB%D8%B8%B4%A1%A3\n" +
                "&mainSheet.dealContent=1\n" +
                "&textfield23=4004\n" +
                "&textfield23=%CD%B6%CB%DF%B4%A6%C0%ED%D6%D0%D0%C4%D7%E9";
        System.out.println(UrlHelper.decodeQuery(http_request, Opslab.GBK));
    }

    @Test
    public void testParseQuery() {
        String query = "id=111&name=test&password=p0ssw0rd";
        Map<String, String> queryMap = UrlHelper.parseQuery(query, '&', '=', null);
        System.out.println(queryMap);
        Map<String, String> httpQueryMap = UrlHelper.httpParseQuery(query);
        System.out.println(httpQueryMap);

        //数组解析
        String query2 = "id=111&name=test&password[]=p0ssw0rd&password[]=123456";
        Map<String, String> queryMap1 = UrlHelper.parseQuery(query2, '&', '=', ",");
        System.out.println(queryMap1);
        Map<String, String> httpQueryMap1 = UrlHelper.httpParseQuery(query2);
        System.out.println(httpQueryMap1);

        //"id=111&name=test&password=p0ssw0rd,=123456"
        String query3 = "id=111&name=test&password=p0ssw0rd,123456";
        Map<String, String> queryMap2 = UrlHelper.parseQuery(query3, '&', '=', ",");
        System.out.println(queryMap2);
        Map<String, String> httpQueryMap2 = UrlHelper.httpParseQuery(query3);
        System.out.println(httpQueryMap2);
    }

}