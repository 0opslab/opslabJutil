package evilp0s;

import org.junit.Test;

import java.io.UnsupportedEncodingException;


public class ConvertUtilTest  {

    @Test
    public void testencodeBytes() throws UnsupportedEncodingException {
        String str         = "中文";
        PrintUtil.print(new String(ConvertUtil.encodeBytes(str.getBytes(CharsetUtil.GBK),' ')));
        PrintUtil.print(new String(ConvertUtil.encodeBytes(str.getBytes(CharsetUtil.UTF_8),' ')));
    }

    @Test
    public void testBytesToHexString() throws UnsupportedEncodingException {
        String str         = "中文";
        String utf8_hexStr = ConvertUtil.bytesToHexString(str.getBytes(CharsetUtil.UTF_8));
        PrintUtil.print("utf8 hex:" + utf8_hexStr);
        String gbk_hexStr = ConvertUtil.bytesToHexString(str.getBytes(CharsetUtil.GBK));
        PrintUtil.print("gbk hex:" + gbk_hexStr);
        byte[] bt      = ConvertUtil.hexStringToByte(utf8_hexStr.replaceAll("\\s", ""));
        String utf8Str = new String(bt);
        PrintUtil.print("utf8 Str:" + utf8Str);
    }

    @Test
    public void testIntsToByte() throws UnsupportedEncodingException {
        int[]  arr = new int[]{0xE4, 0xb8, 0xAD, 0xE6, 0x96, 0x87};
        byte[] bt  = ConvertUtil.intToByte(arr);
        String str = new String(bt);
        System.out.println(str);

        int[]  tt   = new int[]{0xD6, 0xD0, 0xCE, 0xC4};
        String strs = new String(ConvertUtil.intToByte(tt), "GBK");
        System.out.println(strs);
    }

    @Test
    public void testHex() {
        String hexStr = "E4 B8 AD E6 96 87";
        String binStr = ConvertUtil.HexStringtoBinarg(hexStr);
        PrintUtil.print(binStr);
    }
}