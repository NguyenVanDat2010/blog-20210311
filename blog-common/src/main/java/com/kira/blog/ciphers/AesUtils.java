package com.kira.blog.ciphers;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;

/*
* the com.kira.blog.utils using AES to encrypt or decrypt
* */
public class AesUtils {

    private static final String KEY_ALGORITHM = "AES";

    private static final String AES_ECB_INSTANCE = "AES/ECB/PKCS5Padding";

    private static final String AES_CBC_INSTANCE = "AES/CBC/PKCS5Padding";

    private static byte[] encryptByECB(byte[] key, byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(AES_ECB_INSTANCE);
        SecretKeySpec keySpec= new SecretKeySpec(key, KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(input);
    }

    public static String encryptByECB(String key, String input) throws GeneralSecurityException {
        return Base64.getEncoder().encodeToString(encryptByECB(key.getBytes(), input.getBytes()));
    }

    private static byte[] decryptByECB(byte[] key, byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(AES_ECB_INSTANCE);
        SecretKey keySpec = new SecretKeySpec(key, KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(input);
    }

    public static String decryptByECB(String key, String input) throws GeneralSecurityException {
        return new String(decryptByECB(key.getBytes(), Base64.getDecoder().decode(input)), StandardCharsets.UTF_8);
    }

    private static byte[] encryptByCBC(byte[] key, byte[] input) throws GeneralSecurityException {
        // 1. Obtain Cipher instance according to algorithm, working mode, and filling mode
        Cipher cipher = Cipher.getInstance(AES_CBC_INSTANCE);
        // 2. Initialize a SecretKey instance according to the algorithm name, the length of the key is specified
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, KEY_ALGORITHM);
        // 3. CBC mode needs to use SecureRandom to create an iv instance
        SecureRandom sr = SecureRandom.getInstanceStrong();
        byte[] iv = sr.generateSeed(16);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        // 4. Use SecretKey, Iv to initialize the Cipher instance, and set the encryption and decryption mode
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] data = cipher.doFinal(input);
        return join(iv, data);
    }

    public static String encryptByCBC(String key, String input) throws GeneralSecurityException {
        return Base64.getEncoder().encodeToString(encryptByCBC(key.getBytes(), input.getBytes()));
    }

    private static byte[] decryptByCBC(byte[] key, byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(AES_CBC_INSTANCE);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, KEY_ALGORITHM);
        byte[] iv = new byte[16];
        byte[] data = new byte[input.length - 16];
        System.arraycopy(input, 0, iv, 0, 16);
        System.arraycopy(input, 16, data, 0, data.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(data);
    }

    public static String decryptByCBC(String key, String input) throws GeneralSecurityException {
        return new String(decryptByCBC(key.getBytes(), Base64.getDecoder().decode(input)), StandardCharsets.UTF_8);
    }

    private static byte[] join(byte[] bs1, byte[] bs2) {
        byte[] r = new byte[bs1.length + bs2.length];
        System.arraycopy(bs1, 0, r, 0, bs1.length);
        System.arraycopy(bs2, 0, r, bs1.length, bs2.length);
        return r;
    }

//    public static void main(String[] args) throws GeneralSecurityException {
//        // test ECB mode
//        /*String message = "Hello world213";
//        System.out.println("Message: " + message);
//        String key = "1234567890abcdef";
//        // AES ECB mode encrypt:
//        String encrypt = encryptByECB(key, message);
//        System.out.println("Encrypt by ECB mode: " + encrypt);*/
//        // AES ECB mode decrypt:
//        String decrypted = decryptByECB("DUYNDMXKGCEMKGNV", "7IzgkvtAxjHuU4NBc0UnXI48ZfDthSog1nNVJk6Xboa9BMOHkAI6eTyqsWWmqi83ttwBWVP3SU1j6EZcuaC64rxpONXZyfGLY1zg8i/ZhwEtuA0eNq0ygH6O46tQEJI9GsEA4eJSZYDcGuS0xFT6oGQbpoEaHPSGNUgb5KAe8VQ9R3dOpwxGauHHOlGU5W5nWbj9atQMbXhqLWLogeb7bLSzpD99EueeVhxnvTyCmVLgmUwUON0LBEd8QTnsU7Kcl9u8/GC/T1ygqDOaY7GMy+bUmvi0ZjG6XIj8ldWwz5km+2R6VhkriocaP+FSygKmZ+Ekj6KpjNPcuQ5rY1bGd6c5z+/ZOpXb4WHoZUe0Xs2z+X0vS/Pcgv3B6tXwgLn9MOo5vspXnLvf5Hr5JQDwmlHioTjcx26dupqDy/PoQPFu1bjslnZw/dtLg5irxgCexR+9pbY40JIu7+2AIbn73DVARVcY28GhkAXjJIqu8ytp7P97eXAC7+5bl94QERkivVYtH9foICLPjjU/+0u+X1JRIyeFP4OllWcRJnCyYrvoH61fGzmvEYwZjTjy1wXUi4vBRWfhOHCCXXIfbTrEojSdvOTiKL+UuZ5aopMMTWq5iv30dywevn+OF2Epg4ZkWZNnen0OfNWe98wd+KquOslXLlRBk77fngNx2X0Hs/w/zzBi9xvCISkxkXBpqEE1");
//        System.out.println("Decrypt by ECB mode: " + decrypted);
//
//        /////////////////////////////////////////////////////
//        /*String encrypted = encryptByCBC(key, message);
//        System.out.println("Encrypt by CBC mode: " + encrypted);
//        String decrypt = decryptByCBC(key, encrypted);
//        System.out.println("Decrypt by CBC mode: " + decrypt);*/
//    }
}
