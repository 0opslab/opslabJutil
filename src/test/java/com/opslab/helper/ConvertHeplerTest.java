package com.opslab.helper;

import com.opslab.Opslab;
import com.opslab.helper.ConvertHepler;
import com.opslab.util.JacksonUtil;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;


public class ConvertHeplerTest {

    @Test
    public void testencodeBytes() throws UnsupportedEncodingException {
        String str = "中文";
        System.out.println(new String(ConvertHepler.encodeBytes(str.getBytes(Opslab.GBK), ' ')));
        System.out.println(new String(ConvertHepler.encodeBytes(str.getBytes(Opslab.UTF_8), ' ')));
    }

    @Test
    public void testBytesToHexString() throws UnsupportedEncodingException {
        String str = "中文";
        byte[] uft8_bytes = str.getBytes(Opslab.UTF_8);
        String utf8_hexStr = ConvertHepler.bytesToHexString(uft8_bytes);
        System.out.println("UTF-8 BYTE:" + Arrays.toString(uft8_bytes));
        System.out.println("UTF-8 HEX: " + utf8_hexStr);
        byte[] gbk_bytes = str.getBytes(Opslab.GBK);
        String gbk_hexStr = ConvertHepler.bytesToHexString(str.getBytes(Opslab.GBK));
        System.out.println("GBK BYTE:" + Arrays.toString(gbk_bytes));
        System.out.println("GBK HEX: " + gbk_hexStr);

        //用UTF-8数字新建字符串
        System.out.println(new String(uft8_bytes));
        //用户GBK数字新建字符串
        System.out.println(new String(gbk_bytes, Opslab.GBK));


    }

    @Test
    public void testIntsToByte() throws UnsupportedEncodingException {
        int[] arr = new int[]{0xE4, 0xb8, 0xAD, 0xE6, 0x96, 0x87};
        byte[] bt = ConvertHepler.intToByte(arr);
        String str = new String(bt);
        System.out.println(str);

        int[] tt = new int[]{0xD6, 0xD0, 0xCE, 0xC4};
        String strs = new String(ConvertHepler.intToByte(tt), "GBK");
        System.out.println(strs);
    }

    @Test
    public void testshortToByte() {
        byte[] bt = ConvertHepler.shortToByte((short) 1);
        System.out.println(Arrays.toString(bt));
        System.out.println(ConvertHepler.byteToBinary(bt[0]));
        System.out.println(ConvertHepler.byteToBinary(bt[1]));
    }

    @Test
    public void testHex() {
        String hexStr = "E4 B8 AD E6 96 87";
        System.out.println(ConvertHepler.hexStringToBin(hexStr));
        System.out.println(Arrays.toString(ConvertHepler.hexStringToByte(hexStr)));
        for (byte b : ConvertHepler.hexStringToByte(hexStr)) {
            System.out.print(ConvertHepler.byteToBinary(b) + " ");
        }
        System.out.print("\n" + ConvertHepler.byteToLong(ConvertHepler.hexStringToByte(hexStr)) + " ");
        System.out.print("\n" + ConvertHepler.bytesToInt(ConvertHepler.hexStringToByte(hexStr)) + " ");
        System.out.print("\n" + ConvertHepler.byteToShort(ConvertHepler.hexStringToByte(hexStr)) + " ");
    }

    @Test
    public void testLong() {
        System.out.println(Arrays.toString(ConvertHepler.longToByte(1L)));

        System.out.println(ConvertHepler.byteToBinary(ConvertHepler.longToByte(1L)[0]));

        System.out.println(Arrays.toString(ConvertHepler.longToByte(10L)));

        System.out.println(ConvertHepler.byteToBinary(ConvertHepler.longToByte(10L)[0]));
    }


    @Test
    public void arrayList(){
        String hexStr = "E4 B8 AD E6 96 87";
        List<String> strings = ConvertHepler.arrayToList(hexStr.split(" "));
        System.out.println(JacksonUtil.toJson(strings));
    }
}