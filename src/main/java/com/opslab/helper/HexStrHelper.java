package com.opslab.helper;

import com.opslab.Opslab;

/**
 * 十六进制字符串相关的一些操作
 */
public class HexStrHelper {
    private static String CHARS_HEX = "0123456789ABCDEF";

    private static char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
    /**
     * @return
     * @MethodName
     * @Author liaowenxiong
     * @Description 获取字符串的Unicode编码
     * @Date 下午10:26 2021/8/24
     * @Param
     */

    public static String getUnicode(String str) {
        String strTemp = "";
        if (str != null) {
            for (char c : str.toCharArray()) {
                if (c > 255) {
                    strTemp += "\\u" + Integer.toHexString((int)c);
                } else {
                    strTemp += "\\u00" + Integer.toHexString((int)c);
                }
            }
        }
        return strTemp;
    }

    /**
     * 返回字符串是十六进制数组
     * @param str
     * @return
     */
    public static Integer[] intArray(String str) {
        if( str == null || str.length() <= 0){
            return null;
        }

        Integer[] arr = new Integer[str.length()];
        int  i = 0;
            for (char c : str.toCharArray()) {
                //System.out.println(c+">"+((int)c));
               arr[i] = ((int)c);
               i = i+1;
            }
        return arr;
    }

    /**
     * 返回字符串是十六进制数组
     * @param str
     * @return
     */
    public static String[] hexstrArray(String str) {
        if( str == null || str.length() <= 0){
            return null;
        }

        String[] arr = new String[str.length()];
        int  i = 0;
        for (char c : str.toCharArray()) {
            arr[i] = String.format("0x%s",Integer.toHexString((int)c));
            i = i+1;
        }
        return arr;
    }

    /**
     * 字符串转换成为16进制(无需Unicode编码)
     * @param str
     * @return
     */
    public static String str2HexStr( String str )
    {

        StringBuilder sb = new StringBuilder( "" );
        byte[] bs = str.getBytes();
        int bit;
        for ( byte b : bs )
        {
            bit = (b & 0x0f0) >> 4;
            sb.append( HEX_CHARS[bit] );
            bit = b & 0x0f;
            sb.append( HEX_CHARS[bit] );
            /* sb.append(' '); */
        }
        return(sb.toString().trim() );
    }


    /**
     * 16进制直接转换成为字符串(无需Unicode解码)
     * @param hexStr
     * @return
     */
    public static String hexStr2Str( String hexStr ) {
        char[] hexs	= hexStr.toCharArray();
        byte[] bytes	= new byte[hexStr.length() / 2];
        int n;
        for ( int i = 0; i < bytes.length; i++ )
        {
            n		= CHARS_HEX.indexOf( hexs[2 * i] ) * 16;
            n		+= CHARS_HEX.indexOf( hexs[2 * i + 1] );
            bytes[i]	= (byte) (n & 0xff);
        }
        return(new String( bytes ) );
    }
    /**
     * 将十六进制字符串转为二进制字符串
     *
     * @param hexStr 十六进制字符串
     */
    public static final  String hexStringToBin(String hexStr) {
        hexStr = hexStr.replaceAll("\\s", "").replaceAll("0x", "");
        char[] achar = hexStr.toCharArray();

        StringBuilder result = new StringBuilder();
        for (char a : achar) {
            result.append(Integer.toBinaryString(
                    Integer.valueOf(String.valueOf(a), 16)));
            result.append(" ");
        }
        return result.toString();
    }

    /**
     * 将二进制转换为十六进制字符输出
     *
     * @param bytes bytes数组
     */
    public static final  String bytesToHexString(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            //字节高4位
            result.append(String.valueOf(Opslab.HEX_CHAR_STR.charAt((b & 0xF0) >> 4)));
            //字节低4位
            result.append(String.valueOf(Opslab.HEX_CHAR_STR.charAt(b & 0x0F)));
            result.append(" ");
        }
        return result.toString();
    }

    /**
     * 把16进制字符串转换成字节数组
     *
     * @param hexString 16进制字符串
     * @return byte[]
     */
    public static final  byte[] hexStringToByte(String hexString) {
        int len = (hexString.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hexString.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static final  int toByte(char c) {
        return (byte) Opslab.HEX_CHAR_STR.indexOf(c);
    }



}
