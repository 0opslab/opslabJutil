package com.opslab.util.encrypt;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 0opslab
 */
public class DecodeTest {

    @Test
    public void str2HexStr() throws Exception {
        String[] arr = new String[]{"hello word", "1234", "系统找不到指定的路径",
                "系统找不到指定的路径hello word,.。.。，、;：？!ˉˇ¨`~ 々～"};
        for (String str : arr) {
            String str_en1 = Decode.str2HexStr(str);
            System.out.println(str + "<加密>" + str_en1 + "<解密前后是否一致>" + str.equals(Decode.hexStr2Str(str_en1)));
        }

    }


}