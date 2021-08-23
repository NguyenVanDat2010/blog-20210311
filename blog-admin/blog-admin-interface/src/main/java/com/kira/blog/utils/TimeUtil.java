package com.kira.blog.utils;

import com.kira.blog.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtil {

    private static Logger logger = LoggerFactory.getLogger(TimeUtil.class);

    public static String localDateTimeToCron(LocalDateTime ldt) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ss mm HH dd MM ? yyyy");
        return dateTimeFormatter.format(ldt);
    }

    public static String longToLocalDateTime(Long timestamp) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(dateTime);
    }

    public static String date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(localDateTime);
    }

    /*
     * convert the time to timestamp
     * type: start/ end
     * start: the first day of the month, 00:00:00
     * end: the last day of the month, 23:59:59
     * */
    public static Long month2Timestamp(String time, String type) {
        String[] strArr = time.split("-");
        int year = Integer.parseInt(strArr[0]);
        int month = Integer.parseInt(strArr[1]);
        logger.info("year is: {}, month is: {}", year, month);
        LocalDateTime localDateTime;
        switch (type) {
            case "start":
                localDateTime = LocalDateTime.of(year, month, 1, 0, 0, 0);
                break;
            case "end":
                LocalDate temp = LocalDate.of(year, month, 1);
                int monthDays = temp.getMonth().length(temp.isLeapYear());
                localDateTime = LocalDateTime.of(year, month, monthDays, 23, 59, 59);
                break;
            default:
                throw new BizException("213234234", "bad time");
        }
        logger.info("localDateTime is: {}", localDateTime);
        return Timestamp.valueOf(localDateTime).getTime() / 1000;
    }

    public static Long getTimestamp(boolean isEnd) {
        LocalDateTime now = LocalDateTime.now();
        String type;
        int year;
        int month;
        if (isEnd) {
            year = now.getYear();
            month = now.getMonthValue();
            type = "end";
        } else {
            year = now.minusMonths(2L).getYear();
            month = now.minusMonths(2L).getMonthValue();
            type = "start";
        }
        return month2Timestamp(year + "-" + month, type);
    }

    public static void main(String[] args) {
        Long start = month2Timestamp("2020-6", "start");
        System.out.println(start);

        Long end = month2Timestamp("2020-6", "end");
        System.out.println(end);

        Long timestamp1 = getTimestamp(true);
        System.out.println(timestamp1);

        Long timestamp2 = getTimestamp(false);
        System.out.println(timestamp2);

        /*LocalDateTime now = LocalDateTime.now();
        System.out.println(localDateTimeToCron(now));*/
    }

}
