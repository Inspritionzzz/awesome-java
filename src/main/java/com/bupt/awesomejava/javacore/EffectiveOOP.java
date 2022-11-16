package com.bupt.awesomejava.javacore;

import java.time.LocalDate;
import java.util.Date;

/**
 * @Description: OOP
 * Created by jason on 2022/11/16 8:46
 */

public class EffectiveOOP {
    public static void main(String[] args) {
        Date();
    }

    static void Date() {
        System.out.println(new Date());
        Date deadLine = new Date();
        System.out.println(LocalDate.now());

        LocalDate newYearEve = LocalDate.of(1999, 12, 31);
        int year = newYearEve.getYear();
        int month = newYearEve.getMonthValue();
        int day = newYearEve.getDayOfMonth();
        System.out.println(year + "-" + month + "-" + day);

        LocalDate aThousandDaysLater = newYearEve.plusDays(1000);
        year = aThousandDaysLater.getYear();
        month = aThousandDaysLater.getMonthValue();
        day = aThousandDaysLater.getDayOfMonth();
        System.out.println(year + "-" + month + "-" + day);


    }
}
