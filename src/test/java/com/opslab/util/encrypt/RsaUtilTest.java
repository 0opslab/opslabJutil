package com.opslab.util.encrypt;

import org.junit.Test;

import java.util.Map;

public class RsaUtilTest {

    @Test
    public void test() throws Exception {
        Map<String, Object> keyMap = RsaUtil.genKeyPair();
        String publicKey = RsaUtil.getPublicKey(keyMap);
        String privateKey = RsaUtil.getPrivateKey(keyMap);

        System.out.println("publicKey:" + publicKey);
        System.out.println("privateKey:" + privateKey);


        String str = "Agg8/9jQt3Gmgcd24l2qJFGxkWH3jNe";

        //私钥签名公钥校验
        String sign = RsaUtil.sign(str.getBytes(), privateKey);
        System.out.println(RsaUtil.verify(str.getBytes(), publicKey, sign));


        //私钥加密-公钥解密失败
        String encryptedDataWithPriKey = RsaUtil.encryptedDataWithPriKey(str, privateKey);
        String decryptDataWithPubKey = RsaUtil.decryptDataWithPubKey(encryptedDataWithPriKey, publicKey);
        if (!decryptDataWithPubKey.equals(str)) {
            System.out.println("私钥加密-公钥解密失败");
        }

        //加密解密测试（公钥加密-私钥解密）
        String date1 = RsaUtil.encryptedDataWithPubKey(str, publicKey);
        String date2 = RsaUtil.decryptDataWithPriKey(date1, privateKey);
        if (!str.equals(date2)) {
            System.out.println("公钥加密-私钥解密失败");
        }
    }

}