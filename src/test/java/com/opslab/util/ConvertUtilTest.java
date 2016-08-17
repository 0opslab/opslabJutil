package com.opslab.util;

import com.opslab.util.CharsetUtil;
import com.opslab.util.ConvertUtil;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;


public class ConvertUtilTest {

    @Test
    public void testencodeBytes() throws UnsupportedEncodingException {
        String str = "中文";
        System.out.println(new String(ConvertUtil.encodeBytes(str.getBytes(CharsetUtil.GBK), ' ')));
        System.out.println(new String(ConvertUtil.encodeBytes(str.getBytes(CharsetUtil.UTF_8), ' ')));
    }

    @Test
    public void testBytesToHexString() throws UnsupportedEncodingException {
        String str = "中文";
        byte[] uft8_bytes = str.getBytes(CharsetUtil.UTF_8);
        String utf8_hexStr = ConvertUtil.bytesToHexString(uft8_bytes);
        System.out.println("UTF-8 BYTE:"+ Arrays.toString(uft8_bytes));
        System.out.println("UTF-8 HEX: " + utf8_hexStr);
        byte[] gbk_bytes = str.getBytes(CharsetUtil.GBK);
        String gbk_hexStr = ConvertUtil.bytesToHexString(str.getBytes(CharsetUtil.GBK));
        System.out.println("GBK BYTE:" + Arrays.toString(gbk_bytes));
        System.out.println("GBK HEX: " + gbk_hexStr);

        //用UTF-8数字新建字符串
        System.out.println(new String(uft8_bytes));
        //用户GBK数字新建字符串
        System.out.println(new String(gbk_bytes, CharsetUtil.GBK));


    }

    @Test
    public void testIntsToByte() throws UnsupportedEncodingException {
        int[] arr = new int[]{0xE4, 0xb8, 0xAD, 0xE6, 0x96, 0x87};
        byte[] bt = ConvertUtil.intToByte(arr);
        String str = new String(bt);
        System.out.println(str);

        int[] tt = new int[]{0xD6, 0xD0, 0xCE, 0xC4};
        String strs = new String(ConvertUtil.intToByte(tt), "GBK");
        System.out.println(strs);
    }

    @Test
    public void testshortToByte(){
        byte[] bt = ConvertUtil.shortToByte((short)1);
        System.out.println(Arrays.toString(bt));
        System.out.println(ConvertUtil.byteToBinary(bt[0]));
        System.out.println(ConvertUtil.byteToBinary(bt[1]));
    }
    @Test
    public void testHex() {
        String hexStr = "E4 B8 AD E6 96 87";
        System.out.println(ConvertUtil.hexStringtoBinarg(hexStr));
        System.out.println(Arrays.toString(ConvertUtil.hexStringToByte(hexStr)));
        for(byte b:ConvertUtil.hexStringToByte(hexStr)){
            System.out.print(ConvertUtil.byteToBinary(b)+" ");
        }
        System.out.print("\n" + ConvertUtil.byteToLong(ConvertUtil.hexStringToByte(hexStr)) + " ");
        System.out.print("\n" + ConvertUtil.bytesToInt(ConvertUtil.hexStringToByte(hexStr)) + " ");
        System.out.print("\n" + ConvertUtil.byteToShort(ConvertUtil.hexStringToByte(hexStr)) + " ");
    }

    @Test
    public void testLong(){
        System.out.println(Arrays.toString(ConvertUtil.longToByte(1L)));

        System.out.println(ConvertUtil.byteToBinary(ConvertUtil.longToByte(1L)[0]));

        System.out.println(Arrays.toString(ConvertUtil.longToByte(10L)));

        System.out.println(ConvertUtil.byteToBinary(ConvertUtil.longToByte(10L)[0]));
    }
}