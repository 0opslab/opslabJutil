package com.opslab.helper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CryptoHelperTest {

    @Test
    public void testMD5(){

        assertEquals("13574ef0d58b50fab38ec841efe39df4", CryptoHelper.md5("hello word"));
        assertEquals("e10adc3949ba59abbe56e057f20f883e", CryptoHelper.md5("123456"));

    }

    @Test
    public void testDest() throws Exception {
        String[] arr = new String[]{"hello word", "1234", "系统找不到指定的路径",
                "系统找不到指定的路径hello word,.。.。，、;：？!ˉˇ¨`~ 々～"};
        String key = "E7B3BBE7BB9FE689BEE4B88DE588B0E68C87E";
        for (String str : arr) {
            String str_en1 = CryptoHelper.desEncrypt(key, str);
            String decode = CryptoHelper.dseDecode(key, str_en1);
            assertEquals(str, decode);
        }
    }


    @Test
    public void testAes(){
        String[] arr = new String[]{"hello word", "1234", "系统找不到指定的路径",
                "系统找不到指定的路径hello word,.。.。，、;：？!ˉˇ¨`~ 々～"};
        String key = "E7B3BBE7BB9FE689BEE4B88DE588B0E68C87E";
        for (String str : arr) {
            String str_en1 = CryptoHelper.aesEncrypt(key, str);
            String decode = CryptoHelper.aseDecode(key, str_en1);
            //System.out.println(str_en1);
            //System.out.println(decode);
            assertEquals(str, decode);
        }
    }
}