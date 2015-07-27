package evilp0s.WEB;

import evilp0s.PrintUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Map;

public class WebUtilTest extends TestCase {


    @Test
    public void testescape() {
        StringBuffer html = new StringBuffer();
        html.append("<div class=\"ipaddress\">服务器IP地址：</div>");
        html.append("<div class=\"ipinput\">");
        html.append("<input type=\"text\" value=\"127.0.5.5.0\" class=\"text\" />");
        html.append("<input type=\"button\" value=\"修改\" class=\"button\" onclick=\"\"/>");
        html.append("</div>");
        html.append("<div class=\"iptip\">");
        html.append("<span class=\"red\">说明：</span>");
        html.append("<span class=\"content\">①该服务器IP为你租用的服务器IP地址,如果本机直接为127.0.0.1</span>");
        html.append("<span class=\"content\">②修改服务器IP，会导致所有的GameServer重启，以便重新加载配置</span>");
        html.append("</div>");
        String unescape = html.toString();
        String escape = WebUtil.escape(unescape);
        System.out.println(escape);
        unescape = WebUtil.unescape(escape);
        System.out.println(unescape);
        System.out.println(unescape.equals(html.toString()));
        System.out.println(WebUtil.escape("123 123"));
    }

    @Test
    public void testUrl(){
        String url = "http://www.baidu.com/page.jsp?act=list&ad=12&redirect=true";

        //设置参数
        System.out.println(WebUtil.setParam(url, "act", "listnew"));

        //移除单个参数
        System.out.println(WebUtil.removeParam(url, "act"));

        //移除多个参数
        System.out.println(WebUtil.removeParam(url, "act", "ad", "redirect"));

    }

    @Test
    public void testParseQuery(){
        String query ="id=111&name=test&password=p0ssw0rd";
        Map<String,String> queryMap = WebUtil.parseQuery(query, '&', '=', null);
        PrintUtil.print(queryMap);
        Map<String,String> httpQueryMap = WebUtil.httpParseQuery(query);
        PrintUtil.print(httpQueryMap);

        //数组解析
        String query2 ="id=111&name=test&password[]=p0ssw0rd&password[]=123456";
        Map<String,String> queryMap1 = WebUtil.parseQuery(query2, '&', '=', ",");
        PrintUtil.print(queryMap1);
        Map<String,String> httpQueryMap1 = WebUtil.httpParseQuery(query2);
        PrintUtil.print(httpQueryMap1);

        //"id=111&name=test&password=p0ssw0rd,=123456"
        String query3 ="id=111&name=test&password=p0ssw0rd,123456";
        Map<String,String> queryMap2 = WebUtil.parseQuery(query3, '&', '=', ",");
        PrintUtil.print(queryMap2);
        Map<String,String> httpQueryMap2 = WebUtil.httpParseQuery(query3);
        PrintUtil.print(httpQueryMap2);
    }


}