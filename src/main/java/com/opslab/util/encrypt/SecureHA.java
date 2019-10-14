package com.opslab.util.encrypt;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Secure Hash Algorithm，安全散列算法
 */
public class SecureHA {

    public static final String KEY_SHA = "SHA";

    public static String getResult(String inputStr) {
        BigInteger sha = null;
        try {
            byte[] inputData = inputStr.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
            return sha.toString(32);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
