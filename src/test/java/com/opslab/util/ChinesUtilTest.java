package com.opslab.util;

import com.opslab.util.ChinesUtil;
import junit.framework.TestCase;

import java.io.UnsupportedEncodingException;
import java.nio.charset.CharacterCodingException;

public class ChinesUtilTest extends TestCase {

    public void testMain() {
        String[] strArr = new String[]{
                "!@#$%^&*()_+{}[]|\"'?/:;<>,.",
                "！￥……（）——：；“”‘’《》，。？、",
                "不要啊",
                "やめて",
                "韩佳人",
                "???"};
        for (String str : strArr) {
            System.out.println("===========> 测试字符串：" + str);
            System.out.println("正则判断结果：" + ChinesUtil.isChineseByREG(str) +
                    " -- " + ChinesUtil.isChineseByName(str));
            System.out.println("Unicode判断结果 ：" + ChinesUtil.isChinese(str));
            System.out.println("详细判断列表：");

        }
    }

    public void testChineseLength() {
        String input = "234判234断一的fg456个字符rer串d23213fg中有de多少g45fhh个中文324";
        assertEquals("计算有错误", 15, ChinesUtil.ChineseLength(input));
    }

    public void testIsChinese() {
        String input0 = "234判234断一的fg456个字符rer串d23213fg中有de多少g45fhh个中文324";
        String input1 = "Process finished with exit code 0";
        System.out.println(ChinesUtil.isChinese(input0));
        System.out.println(ChinesUtil.isChinese(input1));
    }

    public void testgetStringLen() {
        String str1 = "234判断fg456";
        System.out.println(ChinesUtil.ChineseLength(str1));
    }

    public void testisMessyCode() throws UnsupportedEncodingException, CharacterCodingException {

        String str1 = "涓枃鐨勫瓧绗︿覆,缂栫爜缁撴灉浼氬簲椤圭洰鐨勭紪璇戝拰JVM鐨勮繍琛岀幆澧冧笉鐥涙湁鎵�奖鍝�";
        assertEquals("判断乱码有问题", true, ChinesUtil.isMessyCode(str1));
    }


}