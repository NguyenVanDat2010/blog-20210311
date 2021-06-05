package com.kira.blog.utils.date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {


    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date value) {
        return format(value, DEFAULT_DATE_FORMAT);
    }

    /**
     * Convert from a date to a string
     *
     * @param value  Date value
     * @param format Date format default"yyyy-MM-dd HH:mm:ss"
     * @return Converted string
     */
    public static String format(Date value, String format) {
        if (value == null) {
            return "";
        }

        if (format == null || format.length() == 0) {
            format = DEFAULT_DATE_FORMAT;
        }

        DateFormat df = new SimpleDateFormat(format, Locale.ENGLISH);
        String result = df.format(value);
        if (result.length() != format.length()) {
            logger.debug("{} is not equals date format {}", result, format);
        }
        return result;
    }

    public static Date format(String value) {
        return format(value, DEFAULT_DATE_FORMAT);
    }

    /**
     * Convert from a string to a date
     *
     * @param value  Date string
     * @param format Date format, default"yyyy-MM-dd HH:mm:ss"
     * @return Date Date value after conversion
     */
    public static Date format(String value, String format) {
        if (value == null || value.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = DEFAULT_DATE_FORMAT;
        }
        if (format.length() > value.length()) {
            format = format.substring(0, value.length());
        }

        Date result = null;
        SimpleDateFormat df = null;
        if (format != null && format.length() > 0) {
            df = new SimpleDateFormat(format);
            try {
                result = df.parse(value);
            } catch (ParseException pe) {
                // logger.debug(value, " parse fail with date format ", format);
                result = null;
            }
        }
        return result;
    }

    /**
     * To get today date
     *
     * @return
     */
    public static Date getToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.getTime();
    }

    /**
     * calculate age
     *
     * @param birthDate birth date
     * @return age
     */
    public static int calculateAge(Date birthDate) {
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDate);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow < monthBirth) {
            age--;
        } else {
            if (monthBirth == monthNow) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            }
        }
        return age;
    }

    /**
     * if (start date + interval seconds) < endDate return true
     * else return false
     *
     * @param startDate   Date
     * @param endDate     Date
     * @param intervalSed int
     * @return boolean
     */
    public static boolean compareDateByInterval(Date startDate, Date endDate, Integer intervalSed) {
        Instant startInstant = startDate.toInstant();
        LocalDateTime startTime = startInstant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        Instant endInstant = endDate.toInstant();
        LocalDateTime endTime = endInstant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return startTime
                .plusSeconds(intervalSed)
                .isBefore(endTime);
    }
}
