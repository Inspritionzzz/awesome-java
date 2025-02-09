package com.bupt.awesomejava.utils;


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class DateGenerate {
    public static void main(String[] args) {

        // function01();

        function02();

        // function03();
    }

    public static void function01() {
        String dateStr = "2024-01-01 00:00:00";
        Date date = DateUtil.parse(dateStr);

        for (int i = 0; i < 365; i++) {
            Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, i);
            System.out.println(newDate);
        }

    }

    public static void function02() {

        String dateStr = "2025-01-01";
        Date date = DateUtil.parse(dateStr);

        for (int i = 0; i < 366; i++) {
            Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, i);
            if (DateUtil.dayOfWeek(newDate) != 1) {
                continue;
            } else {
                System.out.println(
                        "第" +
                                DateUtil.weekOfYear(newDate) +
                                "周（" +
                                DateUtil.format(DateUtil.beginOfWeek(newDate), "yyyyMMdd") +
                                "-" +
                                DateUtil.format(DateUtil.endOfWeek(newDate), "yyyyMMdd") + "）")
                ;
            }

        }


    }

    public static void function03() {

        String dateStr = "2025-01-01";
        Date date = DateUtil.parse(dateStr);

        for (int i = 0; i < 366; i++) {
            Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, i);
            System.out.println(DateUtil.format(newDate, "yyyy-MM-dd"));
        }

    }
}
