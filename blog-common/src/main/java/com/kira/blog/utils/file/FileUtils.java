package com.kira.blog.utils.file;

import com.kira.blog.ciphers.Base64Utils;
import com.kira.blog.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Slf4j
public final class FileUtils extends FileCommonUtils {


    private FileUtils() {
    }

    /**
     * Decode file and save it
     *
     * @param fileDestPath  Where file will be uploaded
     * @param fileBase64    File is format of Base64String
     * @param fileExtension File's extension
     * @return
     */
    public static String upload(String fileDestPath, String fileBase64, String fileExtension) {
        String fileName = FileCommonUtils.generateFileName(fileExtension);
        FileCommonUtils.checkPathExist(fileDestPath);
        fileBase64 = FileCommonUtils.removeBase64StringPrefix(fileBase64);

        // save
        Base64Utils.decodeToFile(fileBase64, generationFilePathNameExtension(fileDestPath, fileName));
        return fileName;
    }

    /**
     * encode file and return base64 string
     */
    public static String download(String location, String fileName) {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(
                    Paths.get(generationFilePathNameExtension(location, fileName)).toFile());
            final byte[] buf = new byte[1024];
            int len;
            while ((len = fis.read(buf)) != -1) {
                bos.write(buf, 0, len);
                bos.flush();
            }

            return choosePrefix(fileName) + Base64Utils.encodeToString(bos.toByteArray());
        } catch (IOException e) {
            throw new SystemException("error of downloading file", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * delete file
     */
    public static void delete(String location, String fileName) {
        final Path path = Paths.get(PathValidUtils.validPath(generationFilePathNameExtension(location, fileName)));
        if (Files.exists(path)) {
            path.toFile().delete();
        }
    }


    /**
     * read classpath file
     *
     * @author xiaoyu
     * Date 2019-10-15
     */
    private static String readClasspathFile(String filePath) {
        FileInputStream fis = null;
        try {
            final File file = new ClassPathResource(filePath).getFile();
            fis = new FileInputStream(file);
            final long fileLen = file.length();
            final byte[] content = new byte[(int) fileLen];
            fis.read(content);
            return new String(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * read email template
     *
     * @author xiaoyu
     * Date 2019-10-15
     */
    public static String readEmailTemplate(String emailTemplateFileName) {
        return readClasspathFile(EMAIL_TEMPLATE_LOCATION + emailTemplateFileName);
    }

    /**
     * read sms template
     *
     * @author xiaoyu
     * Date 2019-10-15
     */
    public static String readSmsTemplate(String smsTemplateFileName) {
        return readClasspathFile(SMS_TEMPLATE_LOCATION + smsTemplateFileName);
    }

    /**
     * read push notification template
     *
     * @author kongweiqi
     * Date 2019-10-18
     */
    public static String readPushTemplate(String pushTemplateFileName) {
        return readClasspathFile(PUSH_NOTIFICATION_TEMPLATE_LOCATION + pushTemplateFileName);
    }


    /**
     * file to byte[]
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static byte[] fileToByte(String filePath) throws Exception {
        byte[] data = new byte[0];
        //Fortify Path Manipulation
        File file = new File(PathValidUtils.validPath(filePath));
        if (file.exists()) {
            FileInputStream in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
            byte[] cache = new byte[2048];
            int nRead;
            while ((nRead = in.read(cache)) != -1) {
                out.write(cache, 0, nRead);
                out.flush();
            }
            out.close();
            in.close();
            data = out.toByteArray();
        }
        return data;
    }

}
