package com.javarush.task.task40.task4009;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/* 
Buon Compleanno!
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getWeekdayOfBirthday("30.05.1984", "2015"));
        System.out.println(getWeekdayOfBirthday("1.12.2015", "2016"));
    }

    public static String getWeekdayOfBirthday(String birthday, String year) {
        //напишите тут ваш код
        LocalDate birthDate = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("d.M.yyyy"));

        LocalDate reqDate = birthDate.withYear(Year.parse(year).getValue());

        //LocalDate reqDate = LocalDate.of(
        //        Year.parse(year).getValue(),
        //        birthDate.getMonth().getValue(),
        //        birthDate.getDayOfMonth());

        DayOfWeek dayOfWeek = reqDate.getDayOfWeek();

        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ITALIAN);
    }
}
