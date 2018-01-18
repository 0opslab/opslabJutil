package com.opslab.util;

import java.io.ByteArrayOutputStream;

/**
 * 常用类型转换
 */
public final class ConvertUtil {

    private final static String hexStr = "0123456789ABCDEF";

    /**
     * 短整型与字节的转换
     */
    public final static byte[] shortToByte(short number) {
        int temp = number;
        byte[] b = new byte[2];
        for (int i = 0; i < b.length; i++) {
            // 将最低位保存在最低位
            b[i] = new Integer(temp & 0xff).byteValue();
            // 向右移8位
            temp = temp >> 8;
        }
        return b;
    }

    /**
     * 字节的转换与短整型
     */
    public final static short byteToShort(byte[] b) {
        short s;
        // 最低位
        short s0 = (short) (b[0] & 0xff);
        short s1 = (short) (b[1] & 0xff);
        s1 <<= 8;
        s = (short) (s0 | s1);
        return s;
    }

    /**
     * 整型与字节数组的转换
     */
    public final static byte[] intToByte(int i) {
        byte[] bt = new byte[4];
        bt[0] = (byte) (0xff & i);
        bt[1] = (byte) ((0xff00 & i) >> 8);
        bt[2] = (byte) ((0xff0000 & i) >> 16);
        bt[3] = (byte) ((0xff000000 & i) >> 24);
        return bt;
    }

    /**
     * 整型数组转换为字节数组的转换
     *
     * @param arr 整型数组
     */
    public final static byte[] intToByte(int[] arr) {
        byte[] bt = new byte[arr.length * 4];
        for (int i = 0; i < arr.length; i++) {
            byte[] t = intToByte(arr[i]);
            System.arraycopy(t, 0, bt, i + 4, 4);
        }
        return bt;
    }

    public final static byte[] encodeBytes(byte[] source, char split) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(source.length);
        for (byte b : source) {
            if (b < 0) {
                b += 256;
            }
            bos.write(split);
            char hex1 = Character.toUpperCase(Character.forDigit((b >> 4) & 0xF, 16));
            char hex2 = Character.toUpperCase(Character.forDigit(b & 0xF, 16));
            bos.write(hex1);
            bos.write(hex2);
        }
        return bos.toByteArray();
    }

    /**
     * bytes数组转char数组
     * bytes to chars
     *
     * @param bytes bytes数组
     */
    public final static char[] bytesToChars(byte[] bytes) {
        char[] chars = new char[]{};
        if (CheckUtil.valid(bytes)) {
            chars = new char[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                chars[i] = (char) bytes[i];
            }
        }
        return chars;
    }

    /**
     * 字节数组和整型的转换
     */
    public final static int bytesToInt(byte[] bytes) {
        int num = bytes[0] & 0xFF;
        num |= ((bytes[1] << 8) & 0xFF00);
        num |= ((bytes[2] << 16) & 0xFF0000);
        num |= ((bytes[3] << 24) & 0xFF000000);
        return num;
    }

    /**
     * 字节数组和长整型的转换
     */
    public final static byte[] longToByte(long number) {
        long temp = number;
        byte[] b = new byte[8];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Long(temp & 0xff).byteValue();
            // 将最低位保存在最低位
            temp = temp >> 8;
            // 向右移8位
        }
        return b;
    }

    /**
     * 字节数组和长整型的转换
     */
    public final static long byteToLong(byte[] b) {
        long s;
        long s0 = b[0] & 0xff;// 最低位
        long s1 = b[1] & 0xff;
        long s2 = b[2] & 0xff;
        long s3 = b[3] & 0xff;
        long s4 = b[4] & 0xff;// 最低位
        long s5 = b[5] & 0xff;
        long s6 = b[6] & 0xff;
        long s7 = b[7] & 0xff; // s0不变
        s1 <<= 8;
        s2 <<= 16;
        s3 <<= 24;
        s4 <<= 8 * 4;
        s5 <<= 8 * 5;
        s6 <<= 8 * 6;
        s7 <<= 8 * 7;
        s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
        return s;
    }

    /**
     * 将byte转换为对应的二进制字符串
     *
     * @param src 要转换成二进制字符串的byte值
     */
    public final static String byteToBinary(byte src) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            result.append(src % 2 == 0 ? '0' : '1');
            src = (byte) (src >>> 1);
        }
        return result.reverse().toString();
    }

    /**
     * 将十六进制字符串转为二进制字符串
     *
     * @param hexStr 十六进制字符串
     */
    public final static String hexStringtoBinarg(String hexStr) {
        hexStr = hexStr.replaceAll("\\s", "").replaceAll("0x", "");
        char[] achar = hexStr.toCharArray();
        String result = "";
        for (char a : achar) {
            result += Integer.toBinaryString(
                    Integer.valueOf(String.valueOf(a), 16)) + " ";
        }
        return result;
    }

    /**
     * 将二进制转换为十六进制字符输出
     *
     * @param bytes bytes数组
     */
    public final static String bytesToHexString(byte[] bytes) {
        String result = "";
        String hex;
        for (byte b : bytes) {
            //字节高4位
            hex = String.valueOf(hexStr.charAt((b & 0xF0) >> 4));
            //字节低4位
            hex += String.valueOf(hexStr.charAt(b & 0x0F));
            result += hex + " ";
        }
        return result;
    }

    /**
     * 把16进制字符串转换成字节数组
     *
     * @param hexString 16进制字符串
     * @return byte[]
     */
    public final static byte[] hexStringToByte(String hexString) {
        int len = (hexString.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hexString.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private final static int toByte(char c) {
        return (byte) hexStr.indexOf(c);
    }
}
