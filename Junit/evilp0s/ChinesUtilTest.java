package evilp0s;

import junit.framework.TestCase;
import org.junit.Test;

public class ChinesUtilTest extends TestCase {

    @Test
    public void testMain(){
        String[] strArr = new String[] { "www.micmiu.com", "!@#$%^&*()_+{}[]|\"'?/:;<>,.", "！￥……（）——：；“”‘’《》，。？、", "不要啊", "やめて", "韩佳人", "???" };
        for (String str : strArr) {
            System.out.println("===========> 测试字符串：" + str);
            System.out.println("正则判断结果：" + ChinesUtil.isChineseByREG(str) + " -- " + ChinesUtil.isChineseByName(str));
            System.out.println("Unicode判断结果 ：" + ChinesUtil.isChinese(str));
            System.out.println("详细判断列表：");

        }
    }

    @Test
    public void testChineseLength() {
        String input = "234判234断一的fg456个字符rer串d23213fg中有de多少g45fhh个中文324";
        System.out.println(ChinesUtil.ChineseLength(input));
    }

    @Test
    public void testIsChinese() {
        String input0 = "234判234断一的fg456个字符rer串d23213fg中有de多少g45fhh个中文324";
        String input1 = "Process finished with exit code 0";
        System.out.println(ChinesUtil.isChinese(input0));
        System.out.println(ChinesUtil.isChinese(input1));
    }

    @Test
    public void testgetStringLen() {
        String str1 = "234判断fg456";
        System.out.println(StringUtil.getStringLen(str1));
        System.out.println(ChinesUtil.ChineseLength(str1));
    }


}