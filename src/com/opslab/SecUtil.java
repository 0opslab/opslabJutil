package com.opslab;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 一些常用的安全相关的操作方法
 */
public class SecUtil {

    public static MessageDigest MD5 = null;

    static {
        try {
            MD5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ne) {
            ne.printStackTrace();
        }
    }

    public static String FileMD5(File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                MD5.update(buffer, 0, length);
            }
            return new BigInteger(1, MD5.digest()).toString(16);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Function:字符串加密函数MD5实现
     */
    public static String md5(String password) {
        try {
            MD5.update(password.getBytes());
            String pwd = new BigInteger(1, MD5.digest()).toString(16);
            return pwd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }
}
