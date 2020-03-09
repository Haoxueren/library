package com.haoxueren.library.encrypt;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class AESUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    /**
     * 生成加密秘钥
     */
    private static SecretKeySpec getSecretKey(byte[] key) throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
        generator.init(256, new SecureRandom(key));
        SecretKey secretKey = generator.generateKey();
        return new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);// 转换为AES专用密钥
    }

    /**
     * AES 加密操作
     */
    public static byte[] encrypt(byte[] content, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(AESUtil.TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, AESUtil.getSecretKey(key));
        return cipher.doFinal(content);
    }

    /**
     * AES 解密操作
     */
    public static byte[] decrypt(byte[] content, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(AESUtil.TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, AESUtil.getSecretKey(key));
        return cipher.doFinal(content);
    }

}