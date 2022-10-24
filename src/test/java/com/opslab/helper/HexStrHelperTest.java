package com.opslab.helper;


import com.opslab.util.JacksonUtil;
import org.junit.Test;

public class HexStrHelperTest {
    @Test
    public void testHexStr(){
        String strs = "字符串与十六进制互转";
        String hexStrs = HexStrHelper.str2HexStr(strs);
        System.out.println(hexStrs);
        System.out.println(HexStrHelper.hexStr2Str(hexStrs));
        String unicode = HexStrHelper.getUnicode(strs);
        System.out.println(unicode);


        Integer[] array = HexStrHelper.intArray(strs);
        System.out.println(JacksonUtil.toJson(array));

        String[] strs1 = HexStrHelper.hexstrArray(strs);

        System.out.println(String.join(",",strs1));
    }
}