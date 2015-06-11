package evilp0s;

import junit.framework.TestCase;

import java.io.File;

/**
 * <h6>Description:<h6>
 * <p></p>
 *
 * @date 2015-05-29.
 */
public class EmailUtilTest extends TestCase {

        public void testMail(){
            EmailUtil se = new EmailUtil(false);
            se.doSendHtmlEmail("邮件主题", "邮件内容", "438558488@qq.com");
            File affix = new File("c:/windows/system.ini");
            se.doSendHtmlEmail("邮件主题", "邮件内容", "438558488@qq.com", affix);//
        }
}