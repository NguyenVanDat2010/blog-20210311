package com.kira.blog.ciphers;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/*
 * Util used to encrypt/ decrypt FR/OCR request and com.kira.common.response
 * */
public class EkycAesUtil {

    private static final String KEY_ALGORITHM = "AES";

    // It is a stupid idea to use NoPadding
    private static final String CIPHER_ALGORITHM = "AES/CBC/NoPadding";

    private static final String DEFAULT_AES_KEY = "1234567890ABCDEF";

    public static String aesKey;

    public static String encrypt(String message, String aesKey) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(aesKey.getBytes(), KEY_ALGORITHM);

        // for AesKey, the blockSize is 16, see AESCipher.engineGetBlockSize()
        int blockSize = cipher.getBlockSize();
        byte[] msgBytes = message.getBytes(StandardCharsets.UTF_8);

        // the length of message bytes
        int msgLength = msgBytes.length;

        // the real length should be a multiple of blockSize(16).
        // for example if message is 30, the plainText should be 32, need to add 2 delta
        int plainTextLength;

        // Create a filled array which is the multiple of blockSize.
        int delta = blockSize - (msgLength % blockSize);
        plainTextLength = msgLength + delta;
        byte[] plaintext = new byte[plainTextLength];
        System.arraycopy(msgBytes, 0, plaintext, 0, msgLength);

        // to fill array with the delta byte
        // for example, if the message byte length is 29, the delta is 3, so the whole message before encrypt should be xxxx...333
        Arrays.fill(plaintext, msgLength, plainTextLength, (byte) delta);

        // generate the iv param
        /*SecureRandom sr = SecureRandom.getInstanceStrong();
        byte[] iv = sr.generateSeed(16);*/
        byte[] iv = "0123456789ABCDEF".getBytes();
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // init the cipher and encrypt
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encryptedMsg = cipher.doFinal(plaintext);

        // combine the whole encrypted message with iv param in the first 16 byte
        int totalLength = plainTextLength + iv.length;
        byte[] finalMsg = new byte[totalLength];
        System.arraycopy(iv, 0, finalMsg, 0, iv.length);
        System.arraycopy(encryptedMsg, 0, finalMsg, iv.length, encryptedMsg.length);

        return Base64.getEncoder().encodeToString(finalMsg);
    }

    public static String decrypt(String encryptMsg, String aesKey) throws GeneralSecurityException {
        byte[] plainText = Base64.getDecoder().decode(encryptMsg);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(aesKey.getBytes(), KEY_ALGORITHM);

        // capture the iv param
        byte[] iv = new byte[16];
        System.arraycopy(plainText, 0, iv, 0, iv.length);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // capture the encrypt message
        byte[] message = new byte[plainText.length - 16];
        System.arraycopy(plainText, 16, message, 0, message.length);

        // init cipher and decrypt
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] original = cipher.doFinal(message);

        // get the last value to know how many length need to delete
        int delta = original[original.length - 1];
        byte[] result = new byte[original.length - delta];
        System.arraycopy(original, 0, result, 0, result.length);
        return new String(result);
    }

    /**
     * random to generate the aesKey
     */
    public static String getAesKey() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            SecretKey sk = kg.generateKey();
            byte[] binary = sk.getEncoded();
            aesKey = String.format("%032X", new BigInteger(+1, binary)).substring(0, 16);
            return aesKey;
        } catch (NoSuchAlgorithmException e) {
            return DEFAULT_AES_KEY;
        }
    }

    /*public static void main(String[] args) throws GeneralSecurityException {
        String message = "Hello world! 123";
        String aesKey = EkycAesUtil.getAesKey();
        System.out.println("aesKey is: " + aesKey);

        String encryptMsg = EkycAesUtil.encrypt(message, aesKey);
        System.out.println("encrypt message is: " + encryptMsg);

        String decryptMsg = EkycAesUtil.decrypt(encryptMsg, aesKey);
        System.out.println("decrypt message is: " + decryptMsg);
    }*/

}
