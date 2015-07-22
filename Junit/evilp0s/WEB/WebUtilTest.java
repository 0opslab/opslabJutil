package evilp0s.WEB;

import junit.framework.TestCase;
import org.junit.Test;

public class WebUtilTest extends TestCase {


    @Test
    public void test() {
        StringBuffer html = new StringBuffer();
        StringBuffer result = new StringBuffer();

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

        String url = "http://www.baidu.com/page.jsp?act=list&ad=12&redirect=true";

        //设置参数
        System.out.println(WebUtil.setParam(url, "act", "listnew"));

        //移除单个参数
        System.out.println(WebUtil.removeParam(url, "act"));

        //移除多个参数
        System.out.println(WebUtil.removeParam(url, "act", "ad", "redirect"));

    }

}