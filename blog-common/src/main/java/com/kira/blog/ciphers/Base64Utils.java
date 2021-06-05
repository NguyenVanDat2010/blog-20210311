package com.kira.blog.ciphers;

import com.kira.blog.exception.SystemException;
import com.kira.blog.utils.file.FileUtils;
import com.kira.blog.utils.file.PathValidUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.io.*;

@Slf4j
public final class Base64Utils {

    private static final int CACHE_SIZE = 1024;

    private Base64Utils() {
    }

    public static void decodeToFile(String base64, String filePath) {
        final byte[] bytes = org.springframework.util.Base64Utils.decodeFromString(base64);
        byteArrayToFile(bytes, filePath);
    }

    public static String encodeToString(byte[] bytes) {
        return org.springframework.util.Base64Utils.encodeToString(bytes);
    }

    public static void byteArrayToFile(byte[] bytes, String filePath) {
        InputStream in = new ByteArrayInputStream(bytes);
        File destFile = new File(PathValidUtils.validPath(filePath));
        try {
            destFile.createNewFile();
            OutputStream out = new FileOutputStream(destFile);
            byte[] cache = new byte[CACHE_SIZE];
            int nRead = 0;
            while ((nRead = in.read(cache)) != -1) {
                out.write(cache, 0, nRead);
                out.flush();
            }
            out.close();
            in.close();
        } catch (IOException e) {
            throw new SystemException("error of byteArrayToFile", e);
        }
        log.info(">>> save file [{}]", filePath);
    }

    /**
     * encode byte[] to base64 string
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    public static String encode(byte[] bytes) throws Exception {
        return new String(Base64.encodeBase64(bytes));
    }

    /**
     * BASE64 string decoded as binary data
     *
     * @param base64
     * @return
     * @throws Exception
     */
    public static byte[] decode(String base64) throws Exception {
        return Base64.decodeBase64(base64.getBytes());
    }


    /**
     * get base64 by file path
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String getBase64ByFilePath(String fileName) throws Exception {
        byte[] bytes = FileUtils.fileToByte(fileName);
        return encode(bytes);
    }
}
