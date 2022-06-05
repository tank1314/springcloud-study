package com.tank.springcloud.springbootclient.util;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 使用spring自带Cipher对称加密
 * AES算法
 */
public class AESUtil {

    private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";

    //获取cipher
    private static Cipher getCipher(byte[] key, int model) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
        System.out.println("MaxAllowedKeyLength=[" + maxKeyLen + "].");
        cipher.init(model, secretKeySpec);

        return cipher;
    }

    //加密「加密后的数据不具备可读性，使用Base64算法进行编码，成为可读性字符串」
    public static String encrypt(byte[] data,byte[] key) throws Exception {
        Cipher cipher = getCipher(key,Cipher.ENCRYPT_MODE);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data));
    }

    //AES解密
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = getCipher(key, Cipher.DECRYPT_MODE);
        return cipher.doFinal(Base64.getDecoder().decode(data));
    }
}
