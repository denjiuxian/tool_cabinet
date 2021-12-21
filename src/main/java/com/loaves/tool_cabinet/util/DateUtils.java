package com.loaves.tool_cabinet.util;

import com.loaves.tool_cabinet.constant.DateConstant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 描述：时间操作工具类
 *
 * @author LBF
 * @date 2021/9/30 10:20
 **/
public class DateUtils {
    /**
     * Date 转 LocalDate 类型
     *
     * @param date Date 类型日期
     * @return LocalDate 类型日期
     */
    public static LocalDate dateToLocalDate(Date date) {
        return null == date ?
                null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * LocalDate 转 Date 类型
     *
     * @param localDate LocalDate 类型日期
     * @return Date 类型日期
     */
    public static Date localDateToDate(LocalDate localDate) {
        return null == localDate ?
                null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date 转 LocalDateTime 类型
     *
     * @param date Date 类型日期
     * @return LocalDateTime 类型日期
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return null == date ?
                null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDateTime 转 Date 类型
     *
     * @param localDateTime LocalDateTime 类型日期
     * @return Date 类型日期
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return null == localDateTime ?
                null : Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当前时间，返回字符串类型时间
     *
     * @return 字符串类型当前日期 "yyyy-MM-dd HH:mm:ss"
     */
    public static String nowToStr() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateConstant.DATE_FORMAT_PNI_YMD_HMS);
        return LocalDateTime.now().format(formatter);
    }



    private DateUtils() {
        throw new IllegalStateException("Date Utility class");
    }
}
