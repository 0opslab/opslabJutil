package com.opslab.util;

import com.opslab.util.RegUtil;
import junit.framework.TestCase;
import org.junit.Test;


public class RegUtilTest extends TestCase {

    public void testIsMatche() {
        System.out.println(RegUtil.isMatche("1234", "\\d{4}"));
        System.out.println(RegUtil.isMatche("123as", "\\d{4}"));

        String Wregex = "[a-zA-Z]:(?:[/\\\\][^/\\\\:*?\"<>|.][^/\\\\:*?\"<>|]{0,254})+";
        String Lregex = "(?:[/\\\\][^/\\\\:*?\"<>|.][^/\\\\:*?\"<>|]{0,254})+";

        System.out.println(RegUtil.isMatche("c:\\1.txt", Wregex));
        System.out.println(RegUtil.isMatche("c:/1.txt", Wregex));
        System.out.println(RegUtil.isMatche("/Program Files (x86)/Tencent", Lregex));
        System.out.println(RegUtil.isMatche("/ProgramFiles/Tencent", Lregex));
    }

    @Test
    public void testisNumeric() {
        assertEquals(true, RegUtil.isNumeric("123"));
        assertEquals(false, RegUtil.isNumeric("123 "));
        assertEquals(false, RegUtil.isNumeric("1a23"));
    }

    @Test
    public void testisFloatNumeric() {
        assertEquals(true, RegUtil.isFloatNumeric("123.3"));
        assertEquals(false, RegUtil.isFloatNumeric("123.3 "));
    }

    @Test
    public void testisABC() {
        assertEquals(true, RegUtil.isABC("abc"));
        assertEquals(false, RegUtil.isABC("abc "));
        assertEquals(false, RegUtil.isABC("123abc"));
    }

    @Test
    public void testcountSubStrReg() {
        String str1 = "192是本地址";
        assertEquals(1, RegUtil.countSubStrReg(str1, "\\d{3}"));
    }

    @Test
    public void testisEmail() {
        assertEquals(true, RegUtil.isEmail("12@1.com"));
        assertEquals(false, RegUtil.isEmail("12#1.com"));
    }
}