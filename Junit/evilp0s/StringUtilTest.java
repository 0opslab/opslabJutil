package evilp0s;


import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StringUtilTest {


    @Test
    public void testTrim() {
        String str1 = "java-utils.jar";
        assertEquals("utils.jar", StringUtil.ltrim(str1, 5));
        assertEquals("", StringUtil.ltrim(null, 5));
        assertEquals("java-utils", StringUtil.rtrim(str1, 4));
        assertEquals(null, StringUtil.rtrim(null, 4));

    }

    @Test
    public void testjoinString() {
        String[] arr = new String[]{"MySQL", "ORACLE", "MSSQL", "NOSQL"};
        assertEquals("MySQL/ORACLE/MSSQL/NOSQL", StringUtil.joinString(arr, "/"));

        List<String> list = StringUtil.parseString2List(StringUtil.joinString(arr,"/"),"/");
        assertEquals("MySQL:ORACLE:MSSQL:NOSQL",StringUtil.joinString(list,":"));

    }

    @Test
    public void testLeftRigth() throws UnsupportedEncodingException {
        String str1 = "java-utils.jar";
        assertEquals("jar", StringUtil.right(str1, 3));
        assertEquals("java", StringUtil.left(str1, 4));
        String str2 = "每样东西都有根本有枝末，每件事情都有开始有终结。明白了这本末始终的道理，就接近事物发展的规律了。";
        assertEquals("每样东西都有根本有枝末", StringUtil.left(str2, 11));
        assertEquals("每样东西都有根本有枝末，", StringUtil.left(str2, 12));

    }

    @Test
    public void testRequals() throws Exception {
        assertEquals( true, StringUtil.requals("1", "1	,5,7,9,10,13,14"));
        assertEquals( true, StringUtil.requals("7", "1,5, 7 ,9,10,13,14"));
        assertEquals( true, StringUtil.requals("9", "1,5,7,9,10,13,14"));
        assertEquals( false, StringUtil.requals("15", "1,5,7,9,10,13,14"));
        assertEquals( true, StringUtil.requals("15", "15|7|9|10|13|14", "\\|"));
    }

    @Test
    public void testsubStringNotEncode() {
        String str1 = "java.io.FileNotFoundException: /system/etc/hosts";
        String str2 = "异常没有发现文件:/system/etc/hosts";
        assertEquals("java.io.Fi...", StringUtil.subStringOmit(str1, 10));
        assertEquals("异常没有发现文件:...", StringUtil.subStringOmit(str2, 9));
        assertEquals("异常没有发...", StringUtil.subStringOmit(str2, 5));
        assertEquals("异常没有发", StringUtil.subStringSymbol(str2, 5, ""));
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
    public void testgetHideEmailPrefix() {
        String str1 = "test@163.com";
        System.out.println(StringUtil.getHideEmailPrefix(str1));
    }


    @Test
    public void testtrimPunct() {
        String str1 = "鬼谷子是春秋战国著名的思想家、道家、谋略家，称得上是先秦最神秘的历史人物";
        System.out.println(StringUtil.trimPunct(str1));
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


    @Test
    public void testStrlength() {
        assertEquals(6, "abcdef".length());
        assertEquals(10, "abcdef一二三四".length());
    }


    @Test
    public void testReplaceFirst() {
        String str1 = ",1,2,3,4,5";
        String str2 = "1,2,3,4,5,";
        assertEquals("1,2,3,4,5", StringUtil.replaceFirst(str1, ",", ""));
        assertEquals("1,2,3,4,5", StringUtil.replaceLast(str2, ",", ""));
        assertEquals("12345", StringUtil.remove(str1, ","));
    }
}