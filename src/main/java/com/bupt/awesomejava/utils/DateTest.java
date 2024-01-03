package com.bupt.awesomejava.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class DateTest {
    public static void main(String[] args) {

        String dateStr = "2024-01-01 00:00:00";
        Date date = DateUtil.parse(dateStr);

        for (int i = 0; i < 365; i++) {
            Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, i);
            System.out.println(newDate);
        }
    }

}
