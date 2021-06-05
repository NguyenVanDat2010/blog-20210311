package com.kira.blog.utils.file;

import com.kira.blog.exception.SystemException;
import com.kira.blog.utils.idmaker.IdMaker64;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public abstract class FileCommonUtils {

    protected static final String BASE64_PREFIX_IMAGE = "data:image/jpeg;base64,";

    protected static final String BASE64_PREFIX_PDF = "data:application/pdf;base64,";

    protected static final String BASE64_PREFIX = ";base64,";

    protected static final String SMS_TEMPLATE_LOCATION = "sms/template/";

    protected static final String EMAIL_TEMPLATE_LOCATION = "email/template/";

    protected static final String PUSH_NOTIFICATION_TEMPLATE_LOCATION = "push/template/";

    /**
     * Generation filePath with fileName and fileExtension.
     *
     * @param filePath
     * @param fileName
     * @return
     */
    protected static String generationFilePathNameExtension(String filePath, String fileName) {
        return filePath.endsWith("/") ? filePath + fileName : filePath + "/" + fileName;
    }

    /**
     * Generation of fileName with fileExtension. Format like FILENAME.jpeg
     *
     * @param fileExtension
     * @return
     */
    protected static String generateFileName(String fileExtension) {
        final String fileName = new IdMaker64().getID();
        return fileExtension.startsWith(".") ? fileName + fileExtension : fileName + "." + fileExtension;
    }

    /**
     * Check Path exists or not. if in-existing, create the fold
     *
     * @param filePath File Path
     * @return
     */
    protected static Path checkPathExist(String filePath) {
        final Path path = Paths.get(filePath);
        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new SystemException("Error of creating upload dir", e);
            }
        }
        return path;
    }

    /**
     * add prefix when download
     */
    protected static String choosePrefix(String fileName) {
        if (fileName.endsWith(".png")
                || fileName.endsWith(".jpg")
                || fileName.endsWith(".jpeg")) {
            return BASE64_PREFIX_IMAGE;
        }
        if (fileName.endsWith(".pdf")) {
            return BASE64_PREFIX_PDF;
        }
        return "";
    }

    /**
     * Check if base64String contains prefix, like "data:image/jpeg;base64,". If contains, remove it.
     *
     * @param base64String
     * @return
     */
    public static String removeBase64StringPrefix(String base64String) {
        if (base64String.indexOf(FileCommonUtils.BASE64_PREFIX) != -1) {
            return base64String.substring(base64String.indexOf(FileCommonUtils.BASE64_PREFIX) +
                    FileCommonUtils.BASE64_PREFIX.length());
        }

        return base64String;
    }
}
