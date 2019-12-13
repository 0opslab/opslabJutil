package com.opslab.helper;

import com.opslab.util.encrypt.ASEUtil;
import com.opslab.util.encrypt.Base64Ext;
import com.opslab.util.encrypt.DESUtil;
import com.opslab.util.encrypt.Decode;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * 提供常见的加密方法
 */
public class CryptoHelper {

    private static final Charset charset = Charset.forName("UTF-8");

    private static final String UTF8 = "UTF-8";
    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */

    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64Ext.decode(key.getBytes(charset), Base64Ext.NO_WRAP);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return new String(Base64Ext.encode(key, Base64Ext.NO_WRAP),UTF8);

    }


    /**
     * 字符串加密函数MD5实现
     */
    public final static String md5(String password) {
        MessageDigest md;
        try {
            // 生成一个MD5加密计算摘要
            md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(password.getBytes(charset));
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String pwd = new BigInteger(1, md.digest()).toString(16);
            return pwd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    /**
     * 提供ASE加密算法
     *
     * @param secretKey 秘钥
     * @param str       加密的字符串
     * @return string
     */
    public static String aesEncrypt(String secretKey, String str) {
        return Decode.str2HexStr(ASEUtil.AESEncode(secretKey, str));
    }

    /**
     * 提供ASE解密算法
     *
     * @param secretKey 秘钥
     * @param str       解密的字符串
     * @return decode str or null
     */
    public static String aseDecode(String secretKey, String str) {
        return ASEUtil.AESDncode(secretKey, Decode.hexStr2Str(str));
    }


    /**
     * 提供des加密算法
     *
     * @param secretKey 秘钥
     * @param str       加密的字符串
     * @return string
     */
    public static String desEncrypt(String secretKey, String str) {
        try {
            return DESUtil.encrypt(secretKey, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 提供des解密算法
     *
     * @param secretKey 秘钥
     * @param str       解密的字符串
     * @return decode str or null
     */
    public static String dseDecode(String secretKey, String str) {
        try {
            return DESUtil.decode(secretKey, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
