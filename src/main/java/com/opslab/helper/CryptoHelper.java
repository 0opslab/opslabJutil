package com.opslab.helper;

import com.opslab.util.encrypt.ASEUtil;
import com.opslab.util.encrypt.Base64Ext;
import com.opslab.util.encrypt.DESUtil;
import com.opslab.util.encrypt.Decode;

/**
 * 提供常见的加密方法
 */
public class CryptoHelper {



    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */

    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64Ext.decode(key.getBytes(), Base64Ext.NO_WRAP);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return new String(Base64Ext.encode(key, Base64Ext.NO_WRAP));
    }


    /**
     * 提供ASE加密算法
     * @param secretKey 秘钥
     * @param str 加密的字符串
     * @return string
     */
    public static String aesEncrypt(String secretKey, String str){
        return Decode.str2HexStr(ASEUtil.AESEncode(secretKey, str));
    }

    /**
     * 提供ASE解密算法
     * @param secretKey 秘钥
     * @param str 解密的字符串
     * @return decode str or null
     */
    public static String  aseDecode(String secretKey, String str){
        return  ASEUtil.AESDncode(secretKey, Decode.hexStr2Str(str));
    }


    /**
     * 提供des加密算法
     * @param secretKey 秘钥
     * @param str 加密的字符串
     * @return string
     */
    public static String desEncrypt(String secretKey, String str) {
        try {
            return DESUtil.encrypt(secretKey,str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 提供des解密算法
     * @param secretKey 秘钥
     * @param str 解密的字符串
     * @return decode str or null
     */
    public static String  dseDecode(String secretKey, String str) {
        try {
            return DESUtil.decode(secretKey, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
