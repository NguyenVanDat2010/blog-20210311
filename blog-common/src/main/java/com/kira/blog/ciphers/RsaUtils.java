package com.kira.blog.ciphers;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/*
* the com.kira.common.utils
* */
public class RsaUtils {

    private static final String KEY_ALGORITHM = "RSA";

    public static String decryptByPrivateKey(String message, String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey.getBytes());
        PKCS8EncodedKeySpec PKCS8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key priKey = keyFactory.generatePrivate(PKCS8KeySpec);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(message)), StandardCharsets.UTF_8);
    }

    public static String encryptByPublicKey(String message, String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey.getBytes());
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key pubKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes()));
    }

    /**
     * get public key
     */
    public static PublicKey getPublicKey(String pubKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(pubKey.getBytes());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
        return factory.generatePublic(spec);
    }

    /**
     * get private key
     */
    public static PrivateKey getPrivateKey(String priKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(priKey.getBytes());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
        return factory.generatePrivate(spec);
    }

   /* public static void main(String[] args) throws Exception {
        String message = "1234567890abcdef";
        System.out.println("message is: " + message);
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALrfknXWqi9VUUXc0CxTiColoemm7yP5/fAG9YXcO5j763qClcU5Uo964cBF8jOUHntaG6JRGdt9kIBQrjrNnRa47alUIOcYZEiRUIXODWug0lI+zMBt/2XJ34UIQrDZfOJqkgg33ea+i84rkalOzivArCPO5B2qp5njP1MTOkHJAgMBAAECgYAncB2Rvu8FtETFsLdCU5KuCmkkTXW0qwK7kExqprLxM2Fb5olDKTjSTQWttVVLK1xPfhHM7L6/Qr2nRt+uS0x2baHyoJAqJ1BumFyK0lhnet4cZfeU9jjWsHlclu/R9Vu+v0paAxG19cmNPC2bGrRb5klK78BmwakYM6LUwR7cAQJBAOoidvyyeHJYexvcVVhS+pJNVa+46LGlY6rvfoHlwhrpfrBQdtHua46jiEc+I7wgnjT/rMItUpmFBrrBs8M4aUkCQQDMUzcudLsG6YG94KsC0b902Hn0+IGK0yb8O2S/2EeMSrh/sPzHzGfkMggTi8jK3PZQXeh5D3FQn8qwypz2d5SBAkEA5FCff2P6GGc4Bb8ianLgbfKD8Et8fl8the7tinYmhl/TbtPISocY04ucxPxPlJCqNjxslx3jQll6anuhdGl6IQJAQ4H3CGVO6dC6qhaRyeOtAxkG6lY0zr5G8i1Zcz0tHoANhzrdudGiPbX+Dx8vHz90LkdoqJ7ejInO+SxoQdJugQJBALE/vxeTv5YCBoF2Lx420o+6ZlynNodvycQlG27npRAsDDuF8Sejlt8znAH9+iSuM7rPv8v3SJQoR3AZtq8QFfU=";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC635J11qovVVFF3NAsU4gqJaHppu8j+f3wBvWF3DuY++t6gpXFOVKPeuHARfIzlB57WhuiURnbfZCAUK46zZ0WuO2pVCDnGGRIkVCFzg1roNJSPszAbf9lyd+FCEKw2XziapIIN93mvovOK5GpTs4rwKwjzuQdqqeZ4z9TEzpByQIDAQAB";

        String encrypt = encryptByPublicKey(message, publicKey);
        //Unmh2VDGnlxFeuulgXT1G4Gd7e9+xELS2j2/j6it8KRZd+5daRs19PJjBb7ERtNad0k5V0Q4hS15fLMwlvTMS2fpGhKEs6bl/STBZHS9U/kgQrHB1+1xG6z1Jb1/bFqYTyRVY2f1u6tRkuTjdEHgHMn9UEe/e/r7UIDgAkeISQo=
        System.out.println("encrypt is: " + encrypt);
        String decrypt = decryptByPrivateKey(encrypt, privateKey);
        System.out.println("decrypt is: " + decrypt);

    }*/

}
