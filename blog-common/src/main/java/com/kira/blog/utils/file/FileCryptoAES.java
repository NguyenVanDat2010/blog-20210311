package com.kira.blog.utils.file;

import com.alibaba.druid.filter.config.ConfigTools;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.SecureRandom;
import java.security.Security;

@Component
@Slf4j
public class FileCryptoAES {

    private static Logger logger = LoggerFactory.getLogger(FileCryptoAES.class);

    private static int IV_LENGTH = 12;
    private static int SALT_LENGTH = 64;

    private static int PBKDF2_ITERATIONS = 50000;
    private static int KEY_LENGTH = 256;

    private static String keySec;

    private static String rsaPublicKey;

    @Value("${hlb.file.encryptKey}")
    public void setKey(String keySec) {
        FileCryptoAES.keySec = keySec;
    }

    @Value("${hlb.file.publicKey}")
    public void setRsaPublicKey(String rsaPublicKey) {
        FileCryptoAES.rsaPublicKey = rsaPublicKey;
    }

    /**
     * Decrypt key
     *
     * @return
     */
    public static String decryptKey() {
        String deKey = "";
        try {
            deKey = ConfigTools.decrypt(rsaPublicKey, keySec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deKey;
    }

    /**
     * Generate a PBKDF2 hash
     *
     * @param password
     * @param salt
     * @return
     */
    private static byte[] PBKDF2(char[] password, byte[] salt) {
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password, salt, PBKDF2_ITERATIONS, KEY_LENGTH);
            SecretKey secretKey = secretKeyFactory.generateSecret(spec);

            return secretKey.getEncoded();
        } catch (Exception error) {
            logger.error("PBKDF2 error, caused by: {}", error);
            return null;
        }
    }

    /**
     * Generate a random secure bytes
     *
     * @param size
     * @return
     */
    private static byte[] generateSecureBytes(int size) {
        byte[] secureBytes = new byte[size];
        SecureRandom secRand = new SecureRandom();
        secRand.nextBytes(secureBytes);
        return secureBytes;
    }

    /**
     * Encrypt file by giving the filename and destination fold
     *
     * @param resourceFile resource path + file name
     * @param destFold     destination fold
     */
    public static void encrypt(String resourceFile, String destFold) {
        String key = decryptKey();
        char[] password = key.toCharArray();
        Security.addProvider(new BouncyCastleProvider());
        try {

            log.info("... ... Generating Encryption Key... ...");

            //Generate Encryption Key
            byte[] salt = generateSecureBytes(SALT_LENGTH);
            byte[] encryptionKey = PBKDF2(password, salt);

            //Generate the IV
            byte[] IV = generateSecureBytes(IV_LENGTH);

            //Get file size
            long fileSize = new File(resourceFile).length();

            log.info("... ...Encrypting Data... ...");

            //AES Cipher Settings
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "BC");
            SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(IV));

            //Read the file and Encrypt
            FileInputStream plainFile = new FileInputStream(resourceFile);
            FileOutputStream encFile = new FileOutputStream(destFold);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(encFile, cipher);

            //Save Salt and IV
            encFile.write(salt);
            encFile.write(IV);

            byte[] buffer = new byte[8192];
            int c;
            while ((c = plainFile.read(buffer)) > 0) {
                cipherOutputStream.write(buffer, 0, c);
            }
            cipherOutputStream.close();
            plainFile.close();
            encFile.close();

            log.info("... ...Done... ...");
        } catch (Exception error) {
            log.error("Error: " + error.getMessage());
        }
    }


    /**
     * Encrypt file
     *
     * @param inputStream
     * @param fileDestPathAndName File Destination Path And File Name with Extension
     */
    public static void encrypt(InputStream inputStream, String fileDestPathAndName) {
        String key = decryptKey();
        char[] password = key.toCharArray();
        Security.addProvider(new BouncyCastleProvider());
        try {

            log.info("... ...1.Generating Encryption Key... ...");

            //Generate Encryption Key
            byte[] salt = generateSecureBytes(SALT_LENGTH);
            byte[] encryptionKey = PBKDF2(password, salt);

            //Generate the IV
            byte[] IV = generateSecureBytes(IV_LENGTH);

            log.info("... ...2.Encrypting Data... ...");

            //AES Cipher Settings
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "BC");
            SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(IV));

            //Read the file and Encrypt
            //FileInputStream plainFile = (FileInputStream)inputStream;
            FileOutputStream encFile = new FileOutputStream(fileDestPathAndName);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(encFile, cipher);

            //Save Salt and IV
            encFile.write(salt);
            encFile.write(IV);

            byte[] buffer = new byte[8192];

            int c;
            while ((c = inputStream.read(buffer)) > 0) {
                cipherOutputStream.write(buffer, 0, c);
            }
            cipherOutputStream.close();
            encFile.close();
            inputStream.close();
            log.info("... ...3.Done ... ...");
        } catch (Exception error) {
            log.error("4.Error: " + error.getMessage());
        }
    }

    /**
     * Decrypt file by file
     *
     * @param resourceFile
     * @param destFold
     */
    public static void decrypt(String resourceFile, String destFold) {
        String key = decryptKey();
        char[] password = key.toCharArray();
        Security.addProvider(new BouncyCastleProvider());
        try {

            FileInputStream encFile = new FileInputStream(PathValidUtils.validPath(resourceFile));
            FileOutputStream plainFile = new FileOutputStream(PathValidUtils.validPath(destFold));

            //Read Salt and IV
            byte[] salt = new byte[SALT_LENGTH];
            encFile.read(salt);

            byte[] IV = new byte[IV_LENGTH];
            encFile.read(IV);

            //Get file size
            long fileSize = new File(PathValidUtils.validPath(resourceFile)).length();

            log.info("... ...Generating Encryption Key... ...");

            //Generate the Encryption Key
            byte[] encryptionKey = PBKDF2(password, salt);

            log.info("... ...Decrypting Data... ...");

            //AES Cipher Settings
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "BC");
            SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(IV));

            //Read the file and Decrypt
            CipherInputStream cipherInputStream = new CipherInputStream(encFile, cipher);
            cipherInputStream.skip(SALT_LENGTH + IV_LENGTH);
            byte[] buffer = new byte[8192];
            int actualSize = 0;
            int progress;
            int c;
            while ((c = cipherInputStream.read(buffer)) > 0) {
                plainFile.write(buffer, 0, c);

                actualSize += c;
                progress = (int) (actualSize * 100.0 / fileSize + 0.5);
                // System.out.print("Progress: " + actualSize + " / " + fileSize + " - " + progress + "%\r");
            }
            cipherInputStream.close();
            encFile.close();
            plainFile.close();

            log.info("Decryption Done!");

        } catch (Exception error) {
            log.error("Error: " + error.getMessage());
        }
    }


    /**
     * Decrypt by file
     *
     * @param resourceFile
     * @return
     */
    public static byte[] decrypt(String resourceFile) {
        String key = decryptKey();
        char[] password = key.toCharArray();


        byte[] data = new byte[0];

        Security.addProvider(new BouncyCastleProvider());
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(2048);
             FileInputStream encFile = new FileInputStream(PathValidUtils.validPath(resourceFile))) {

            //Read Salt and IV
            byte[] salt = new byte[SALT_LENGTH];
            encFile.read(salt);

            byte[] IV = new byte[IV_LENGTH];
            encFile.read(IV);

            //Get file size
            // long fileSize = new File(PathValidUtils.validPath(resourceFile)).length();
            //Generate the Encryption Key
            byte[] encryptionKey = PBKDF2(password, salt);

            //AES Cipher Settings
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "BC");
            SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(IV));

            //Read the file and Decrypt
            CipherInputStream cipherInputStream = new CipherInputStream(encFile, cipher);
            cipherInputStream.skip(SALT_LENGTH + IV_LENGTH);
            byte[] buffer = new byte[8192];

            int c;
            while ((c = cipherInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, c);
            }
            cipherInputStream.close();
            return outputStream.toByteArray();
        } catch (Exception error) {
            logger.error("descrypt fail, caused by: {}", error);
        }
        return data;
    }

}
