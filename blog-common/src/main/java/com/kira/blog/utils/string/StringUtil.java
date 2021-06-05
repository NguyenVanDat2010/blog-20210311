package com.kira.blog.utils.string;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * To check a string is correct format follow a regex regression
     *
     * @param strSource a string need to check
     * @param regex     a regex regression
     * @return
     */
    public static boolean validFormat(String strSource, String regex) {
        if (strSource == null || StringUtils.isBlank(strSource) || StringUtils.isBlank(regex)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(strSource);
        return matcher.matches();
    }
}
