package com.javarush.task.task40.task4012;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/* 
Полезные методы DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(isLeap(LocalDate.now()));
        System.out.println(isLeap(LocalDate.of(2012, 12, 02)));

        System.out.println(isBefore(LocalDateTime.of(2017, 1, 20, 17, 0)));
        System.out.println(isBefore(LocalDateTime.of(2020, 1, 20, 17, 0)));
    }

    public static boolean isLeap(LocalDate date) {
        //int year = date.getYear();
        //return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
        return date.isLeapYear();
    }

    public static boolean isBefore(LocalDateTime dateTime) {
        return dateTime.isBefore(LocalDateTime.now());
    }

    public static LocalTime addTime(LocalTime time, int n, ChronoUnit chronoUnit) {
        return time.plus(n, chronoUnit);
    }

    public static Period getPeriodBetween(LocalDate firstDate, LocalDate secondDate) {
        return firstDate.isBefore(secondDate) ?
                Period.between(firstDate, secondDate) :
                Period.between(secondDate, firstDate);
    }
}
