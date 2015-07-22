package evilp0s;

import junit.framework.TestCase;

import java.io.File;


public class EmailUtilTest  {

    public void testMail() {
        EmailUtil se = new EmailUtil(false);
        se.doSendHtmlEmail("邮件主题", "邮件内容", "438558488@qq.com");
        File affix = new File("c:/windows/system.ini");
        se.doSendHtmlEmail("邮件主题", "邮件内容", "438558488@qq.com", affix);//
    }
}