package com.bupt.awesomejava.javacore;

import com.bupt.awesomejava.javacore.models.Employee;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author jason
 * @Description OOP
 * Created on 2022/11/16 8:46
 */

public class EffectiveOOP {
    public static void main(String[] args) {
        // date();
        // calendar();
        employee();
    }

    static void date() {
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

    static void calendar() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int today = date.getDayOfMonth();

        date = date.minusDays(today - 1);
        DayOfWeek weekday = date.getDayOfWeek();
        int value = weekday.getValue();
        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 1; i < value; i++) {
            System.out.print("    ");
        }
        while (date.getMonthValue() == month) {
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == today) {
                System.out.printf("*");
            } else {
                System.out.printf(" ");
            }
            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() == 1) {
                System.out.println();
            }
        }
        if (date.getDayOfWeek().getValue() != 1) {
            System.out.println();
        }
    }

    static void employee() {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 5000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 75000, 1986, 10, 15);
        for (Employee e : staff) {
            e.raiseSalary(5);
        }
        for (Employee e : staff) {
             System.out.println(e.toString());
        }
        var staff1 = new Employee("Carl Cracker", 75000, 1987, 12, 15);
    }



}
