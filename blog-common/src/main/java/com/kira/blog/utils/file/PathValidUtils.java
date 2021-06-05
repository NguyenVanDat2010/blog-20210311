package com.kira.blog.utils.file;

import com.kira.blog.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class PathValidUtils {

    private static final Map<String, String> pathWhiteList = new HashMap<>();

    static {
        for (int i = 0; i < 26; i++) {
            pathWhiteList.put((char) ('a' + i) + "", (char) ('a' + i) + "");
        }
        for (int i = 0; i < 26; i++) {
            pathWhiteList.put((char) ('A' + i) + "", (char) ('A' + i) + "");
        }
        for (int i = 0; i < 10; i++) {
            pathWhiteList.put((char) ('0' + i) + "", (char) ('0' + i) + "");
        }
        pathWhiteList.put("_", "_");
        pathWhiteList.put(":", ":");
        pathWhiteList.put("/", "/");
        pathWhiteList.put("\\", "\\");
        pathWhiteList.put(".", ".");
        pathWhiteList.put("-", "-");
        pathWhiteList.put("@", "@");
    }


    public static String validPath(String path) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < path.length(); i++) {
            if (pathWhiteList.get(path.charAt(i) + "") != null) {
                if (path.charAt(i) == '.' && i < path.length() - 1) {
                    if (path.charAt(i + 1) == '.') {
                        log.info("Invalid path");
                        throw new SystemException("Invalid path");
                    }
                }
                temp.append(pathWhiteList.get(path.charAt(i) + ""));
            }
        }
        return temp.toString();
    }

    public static String validPath1(Object obj) {
        return validPath(String.valueOf(obj));
    }
}
