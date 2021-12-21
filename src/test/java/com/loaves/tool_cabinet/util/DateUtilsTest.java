package com.loaves.tool_cabinet.util;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Date;

class DateUtilsTest {

    @Test
    void dateTest() {
//        LocalDate ld1 = LocalDate.now();
//        LocalDate ld2 = LocalDate.of(2020, 5, 1);
//        Period period = Period.between(ld2, ld1);
//        System.out.println("两个日期相差：" + period.getYears() + "年，" + period.getMonths() + "个月，"
//                + period.getDays() + "天....");
//
//        //检查此期间的三个单位是否为负
//        System.out.println(period.isNegative());
//        //检查此期间的所有三个单位是否为零
//        System.out.println(period.isZero());

        LocalDateTime lt1 = LocalDateTime.now();

        LocalDateTime lt2 = LocalDateTime.of(2022, 11, 10, 11, 8, 10);
        Duration duration = Duration.between(lt1, lt2);
        System.out.println("两个时间相差：" + duration.toDays() + "天，" + duration.toHours() + "个小时，" + duration.toMinutes() + "分钟，"
                + duration.getSeconds() + "秒....");
        System.out.println(duration.isNegative()); //检查此期间的三个单位是否为负
        System.out.println(duration.isZero()); //检查此期间的所有三个单位是否为


    }


}
