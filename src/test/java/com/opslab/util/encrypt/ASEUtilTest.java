package com.opslab.util.encrypt;

import com.opslab.helper.RandomHelper;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 0opslab
 */
public class ASEUtilTest {
    @Test
    public void test() throws Exception {
        String[] arr = new String[]{"hello word", "1234", "系统找不到指定的路径",
                "系统找不到指定的路径hello word,.。.。，、;：？!ˉˇ¨`~ 々～"};
        String key = "E7B3BBE7BB9FE689BEE4B88DE588B0E68C87E";
        for (String str : arr) {
            String random = RandomHelper.uuid();
            String str_en1 = ASEUtil.encrypt(key, str + random);
            String decode = ASEUtil.decode(key, str_en1);
            assertEquals(decode, str + random);
        }
    }


}