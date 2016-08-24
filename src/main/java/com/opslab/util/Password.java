package com.opslab.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 提供密码相关的工具类
 */
public final class Password {

    //必选包含数字、大写字母、小写字母、特殊字符，长度在8到15位
    private static final String SEC_PASSWORD =
            "^(?=.*?[0-9])(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[@!#$%^&*()_+\\.\\-\\?<>'\"|=]+).{8,15}$";
    /**
     * 字符串加密函数MD5实现
     */
    public final static String md5(String password){
        MessageDigest md;
        try {
            // 生成一个MD5加密计算摘要
            md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(password.getBytes());
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
     * 判断一个密码是否健壮
     * 必选包含数字、大写字母、小写字母、特殊字符，长度在8到15位
     * @param password
     * @return
     */
    public final static  boolean isSec(String password){
        return RegUtil.isMatche(password,SEC_PASSWORD);
    }
}
