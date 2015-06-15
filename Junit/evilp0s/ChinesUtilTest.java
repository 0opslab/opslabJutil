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

}