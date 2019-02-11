package com.opslab.util.encrypt;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 0opslab
 */
public class DESUtilTest {
    @Test
    public void test() throws Exception {
        String[] arr = new String[]{"hello word", "1234", "系统找不到指定的路径",
                "系统找不到指定的路径hello word,.。.。，、;：？!ˉˇ¨`~ 々～"};
        String key = "E7B3BBE7BB9FE689BEE4B88DE588B0E68C87E";
        for (String str : arr) {
            String str_en1 = DESUtil.encrypt(key, str);
            String decode = DESUtil.decode(key, str_en1);
            assertEquals(str, decode);
        }
    }
}