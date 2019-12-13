package com.opslab.helper;

import com.opslab.helper.RegHepler;
import junit.framework.TestCase;
import org.junit.Test;


public class RegHeplerTest extends TestCase {

    public void testIsMatche() {
        System.out.println(RegHepler.isMatche("1234", "\\d{4}"));
        System.out.println(RegHepler.isMatche("123as", "\\d{4}"));

        String Wregex = "[a-zA-Z]:(?:[/\\\\][^/\\\\:*?\"<>|.][^/\\\\:*?\"<>|]{0,254})+";
        String Lregex = "(?:[/\\\\][^/\\\\:*?\"<>|.][^/\\\\:*?\"<>|]{0,254})+";

        System.out.println(RegHepler.isMatche("c:\\1.txt", Wregex));
        System.out.println(RegHepler.isMatche("c:/1.txt", Wregex));
        System.out.println(RegHepler.isMatche("/Program Files (x86)/Tencent", Lregex));
        System.out.println(RegHepler.isMatche("/ProgramFiles/Tencent", Lregex));
    }

    @Test
    public void testisNumeric() {
        assertEquals(true, RegHepler.isNumeric("123"));
        assertEquals(false, RegHepler.isNumeric("123 "));
        assertEquals(false, RegHepler.isNumeric("1a23"));
    }

    @Test
    public void testisFloatNumeric() {
        assertEquals(true, RegHepler.isFloatNumeric("123.3"));
        assertEquals(false, RegHepler.isFloatNumeric("123.3 "));
    }

    @Test
    public void testisABC() {
        assertEquals(true, RegHepler.isABC("abc"));
        assertEquals(false, RegHepler.isABC("abc "));
        assertEquals(false, RegHepler.isABC("123abc"));
    }

    @Test
    public void testcountSubStrReg() {
        String str1 = "192是本地址";
        assertEquals(1, RegHepler.countSubStrReg(str1, "\\d{3}"));
    }

    @Test
    public void testisEmail() {
        assertEquals(true, RegHepler.isEmail("12@1.com"));
        assertEquals(false, RegHepler.isEmail("12#1.com"));
    }
}