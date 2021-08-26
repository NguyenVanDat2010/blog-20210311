package com.kira.blog.utils.file;

import com.kira.blog.ciphers.Base64Utils;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;


@Slf4j
public final class FileEncryptUtils extends FileCommonUtils {


    private FileEncryptUtils() {
    }

    /**
     * decode file and encrypt file and save it
     *
     * @param fileDestPath  Where file will be uploaded
     * @param fileBase64    File is format of Base64String
     * @param fileExtension File's extension
     * @return
     */
    public static String encryptAndUploadFile(String fileDestPath, String fileBase64, String fileExtension) {
        fileExtension = FileCommonUtils.getFileExtension(fileBase64, fileExtension);
        String fileName = FileCommonUtils.generateFileName(fileExtension);
        FileCommonUtils.checkPathExist(fileDestPath);
        fileBase64 = FileCommonUtils.removeBase64StringPrefix(fileBase64);

        // save
        try {
            byte[] bytes = Base64Utils.decode(fileBase64);
            InputStream in = new ByteArrayInputStream(bytes);
            FileCryptoAES.encrypt(in, generationFilePathNameExtension(fileDestPath, fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * decrypt file and return base64 string
     *
     * @return
     */
    public static String downloadDecryptFileEncodeBase64(String location, String fileName) {
        String filePath = generationFilePathNameExtension(location, fileName);
        File file = new File(PathValidUtils.validPath(filePath));
        if (file.exists()) {
            try {
                byte[] bytes = FileCryptoAES.decrypt(filePath);
                return choosePrefix(fileName) + Base64Utils.encodeToString(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
