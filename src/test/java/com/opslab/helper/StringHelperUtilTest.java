package com.opslab.helper;



import com.opslab.util.StringUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore
public class StringHelperUtilTest {

    @Test
    public void testsubString() {
        //测试JDK字符串的截取
        System.out.println("中文字符串测试".substring(1, 3));
        System.out.println("中a文b字c符d串e测f试g".substring(3, 7));
    }

    @Test
    public void testStringLen() {
        //测试JDK字符串的长度函数
        System.out.println("中文字符串测试".length());
    }

    @Test
    public void testTrim() {
        String str1 = "java-utils.jar";
        Assert.assertEquals("utils.jar", StringUtil.ltrim(str1, 5));
        assertEquals("", StringUtil.ltrim(null, 5));
        assertEquals("java-utils", StringUtil.rtrim(str1, 4));
        assertEquals(null, StringUtil.rtrim(null, 4));

    }

    @Test
    public void testformatDouble() {
        double d = Double.valueOf("10.012034");
        assertEquals("10.01", StringUtil.formatDouble(d, "##.00"));
        assertEquals("10.0120", StringUtil.formatDouble(d, "##.0000"));
    }

    @Test
    public void testjoinString() {
        String[] arr = new String[]{"MySQL", "ORACLE", "MSSQL", "NOSQL"};
        assertEquals("MySQL/ORACLE/MSSQL/NOSQL", StringUtil.joinString(arr, "/"));

        List<String> list = StringUtil.parseString2List(StringUtil.joinString(arr, "/"), "/");
        assertEquals("MySQL:ORACLE:MSSQL:NOSQL", StringUtil.joinString(list, ":"));


        System.out.println(StringUtil.join("/"));
        System.out.println(StringUtil.join("/", "MySQL", "ORACLE", "MSSQL", "NOSQL"));
    }

    @Test
    public void testLeftRigth() throws UnsupportedEncodingException {
        //正常字符串测试
        String str1 = "java-utils.jar";
        assertEquals("jar", StringUtil.right(str1, 3));
        assertEquals("java", StringUtil.left(str1, 4));

        //非正常测试
        String str2 = "ar";
        System.out.println(StringUtil.right(str2, 3));
        System.out.println(StringUtil.left(str2, 4));


        String str3 = "每样东西都有根本有枝末，每件事情都有开始有终结。明白了这本末始终的道理，就接近事物发展的规律了。";
        assertEquals("每样东西都有根本有枝末", StringUtil.left(str3, 11));
        assertEquals("每样东西都有根本有枝末，", StringUtil.left(str3, 12));

    }

    @Test
    public void testRequals() throws Exception {
        assertEquals(true, StringUtil.requals("1", "1	,5,7,9,10,13,14"));
        assertEquals(true, StringUtil.requals("7", "1,5, 7 ,9,10,13,14"));
        assertEquals(true, StringUtil.requals("9", "1,5,7,9,10,13,14"));
        assertEquals(false, StringUtil.requals("15", "1,5,7,9,10,13,14"));
        assertEquals(true, StringUtil.requals("15", "15|7|9|10|13|14", "\\|"));
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
        String test = "中文";
        String unicode = "\\u4e2d\\u6587";
        assertEquals(unicode, StringUtil.string2Unicode(test));
        assertEquals(test, StringUtil.unicode2String(unicode));
    }


    @Test
    public void testgetHideEmailPrefix() {
        String str1 = "test@163.com";
        System.out.println(StringUtil.getHideEmailPrefix(str1));
    }


    @Test
    public void testtrimPunct() {
        String str1 = "鬼谷子是春秋战国著名的思想家、道家、谋略家，称得上是先秦最神秘的历史人物。）】*&";
        System.out.println(StringUtil.trimPunct(str1));
    }

    @Test
    public void testStringSimilar() {
        //英文测试
        String en1 = "# Summary\n" +
                "* [SSH简介](README.md)\n" +
                "* [Hibernate](chapter2/README.md)\n" +
                "* [Hibernate](chapter2/hibernate.md)\n" +
                "* [Struts2](chapter3/README.md)\n" +
                "* [Spring整合Hibernate](ssh2/README.md)\n" +
                "* [Chapter](chapter/README)\n" +
                "* [Chapter](chapter/README)\n" +
                "* [Chapter7](chapter7/README)\n" +
                "* [Chapter8](chapter8/README)\n" +
                "* [Chapter9](chapter9/README)\n" +
                "* [Chapter11](chapter11/README)\n" +
                "* [Chapter12](chapter12/README)\n" +
                "* [Chapter13](chapter13/README)\n" +
                "* [Chapter14](chapter14/README)\n" +
                "* [Chapter15](chapter15/README)\n" +
                "* [Chapter16](chapter16/README)\n" +
                "* [Chapter17](chapter17/README)\n" +
                "* [Chapter18](chapter18/README)\n" +
                "* [Chapter19](chapter19/README)\n" +
                "* [Chapter20](chapter20/README)\n" +
                "* [Chapter20](chapter20/README)\n";
        String en2 = "$ Summary\n" +
                "> [SSH简介](README.md)\n" +
                "> [Hibernate](chapter2/README.md)\n" +
                "> [Hibernate](chapter2/hibernate.md)\n" +
                "> [Struts2](chapter3/README.md)\n" +
                "> [Spring整合Hibernate](ssh2/README.md)\n" +
                "> [Chapter](chapter/README)\n" +
                "> [Chapter](chapter/README)\n" +
                "> [Chapter7](chapter7/README)\n" +
                "> [Chapter8](chapter8/README)\n" +
                "* [Chapter9](chapter9/README)\n" +
                "* [Chapter11](chapter11/README)\n" +
                "* [Chapter12](chapter12/README)\n" +
                "* [Chapter13](chapter13/README)\n" +
                "* [Chapter14](chapter14/README)\n" +
                "* [Chapter15](chapter15/README)\n" +
                "* [Chapter16](chapter16/README)\n" +
                "* [Chapter17](chapter17/README)\n" +
                "* [Chapter18](chapter18/README)\n" +
                "* [Chapter19](chapter19/README)\n" +
                "* [Chapter20](chapter20/README)\n" +
                "* [Chapter20](chapter20/README)\n";
        System.out.println(StringUtil.SimilarDegree(en1, en2));
        //中文测试
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
    public void testcountSubStr() {
        String str = "鬼谷子是春秋战国著名的思想家、鬼谷子是道家、鬼谷子谋略家，鬼谷子称得上是先秦最神秘的历史人物";
        assertEquals(4, StringUtil.countSubStr(str, "鬼谷子"));
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

    @Test
    public void testBracketStr() {
        String str1 = "（全角）";
        assertEquals("(全角)", StringUtil.full2Half(str1));
        String str2 = "(全角)";
        assertEquals("（全角）", StringUtil.Half2Full(str2));
    }

    @Test
    public void testIsIn() {
        String[] str1 = new String[]{"method1", "method2", "method3", "method4"};
        assertEquals(true, StringUtil.isIn("method3", str1));
    }

    @Test
    public void testreplaceBlank() {
        String str1 = "鬼 谷子   是春秋战国著名\n的思想家、道家、谋略家，称得上是先秦最神秘的历史人物";
        assertEquals("鬼谷子是春秋战国著名的思想家、道家、谋略家，称得上是先秦最神秘的历史人物",
                StringUtil.replaceBlank(str1));
    }

    @Test
    public void testFirstChar() {
        String str = "OperationName";
        assertEquals("operationName", StringUtil.lowerFirstChar(str));
        assertEquals(str, StringUtil.upperFirstChar("operationName"));
    }

    @Test
    public void testRigthEquals() throws Exception {

        assertEquals(true, StringUtil.rigthEquals("utils/StringUtil.java", "utils/StringUtil.java", 5));

    }

    @Test
    public void testLeftEquals() throws Exception {
        assertEquals(true, StringUtil.leftEquals("utils/StringUtil", "utils/EmailUitl", 6));
    }
}