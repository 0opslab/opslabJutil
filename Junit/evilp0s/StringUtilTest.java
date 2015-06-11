package evilp0s;


import org.junit.Test;

public class StringUtilTest {

    @org.junit.Test
    public void testRequals() throws Exception {
        System.out.println(StringUtil.requals("1", "1	,5,7,9,10,13,14"));
        System.out.println(StringUtil.requals("7", "1,5, 7 ,9,10,13,14"));
        System.out.println(StringUtil.requals("9", "1,5,7,9,10,13,14"));
        System.out.println(StringUtil.requals("15", "1,5,7,9,10,13,14"));
    }

    @Test
    public void testString2Unicode() throws Exception {
        String test = "网站地址:www.XXX.com";
        String unicode = StringUtil.string2Unicode(test);
        String string = StringUtil.unicode2String(unicode);
        System.out.println(unicode);
        System.out.println(string);
    }

    @Test
    public void testChineseLength() {
        String input = "234判234断一的fg456个字符rer串d23213fg中有de多少g45fhh个中文324";
        System.out.println(StringUtil.ChineseLength(input));
    }

    @Test
    public void testIsChinese() {
        String input0 = "234判234断一的fg456个字符rer串d23213fg中有de多少g45fhh个中文324";
        String input1 = "Process finished with exit code 0";
        System.out.println(StringUtil.isChinese(input0));
        System.out.println(StringUtil.isChinese(input1));
    }

    @Test
    public void testStringSimilar() {
        String input1 = "每样东西都有根本有枝末，每件事情都有开始有终结。明白了这本末始终的道理，就接近事物发展的规律了。";
        String input2 = "物品有基础也有末路，事情有开始也有终结。知道先与后，就近乎得道了。";
        System.out.println(StringUtil.SimilarDegree(input1, input2));
        System.out.println(StringUtil.SimilarityRatio(input1, input2));
        String input3 = "鬼谷子是春秋战国时期道家、纵横家的鼻祖.";
        String input4 = "鬼谷子是春秋战国著名的思想家、道家、谋略家，称得上是先秦最神秘的历史人物";
        System.out.println(StringUtil.SimilarDegree(input3, input4));
        System.out.println(StringUtil.SimilarityRatio(input3, input4));
    }
}