package com.javarush.task.task40.task4008;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m:s");

        String[] parts = date.split("\\s");
        String result = "";

        for (String s : parts) {
            if (s.matches("\\d{1,2}.\\d{1,2}.\\d{4}")) {
                result = outForDate(LocalDate.parse(s, dateFormatter));
            } else if (s.matches("\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
                if (!result.isEmpty()) result += "\n";
                result += outForTime(LocalTime.parse(s, timeFormatter));
            }
        }

        System.out.println(result);
    }

    private static String outForDate(LocalDate date) {
        return new StringBuilder()
                .append(String.format("День: %d", date.getDayOfMonth()))
                .append(String.format("\nДень недели: %d", date.getDayOfWeek().getValue()))
                .append(String.format("\nДень месяца: %d", date.getDayOfMonth()))
                .append(String.format("\nДень года: %d", date.getDayOfYear()))
                .append(String.format("\nНеделя месяца: %d", date.get(WeekFields.of(Locale.getDefault()).weekOfMonth())))
                .append(String.format("\nНеделя года: %d", date.get(WeekFields.of(Locale.getDefault()).weekOfYear())))
                .append(String.format("\nМесяц: %d", date.getMonthValue()))
                .append(String.format("\nГод: %d", date.getYear()))
                .toString();
    }

    private static String outForTime(LocalTime time) {
        return new StringBuilder()
                .append(String.format("AM или PM: %s", time.get(ChronoField.AMPM_OF_DAY) == 0 ? "AM" : "PM"))
                .append(String.format("\nЧасы: %d", time.get(ChronoField.CLOCK_HOUR_OF_AMPM)))
                .append(String.format("\nЧасы дня: %d", time.getHour()))
                .append(String.format("\nМинуты: %d", time.getMinute()))
                .append(String.format("\nСекунды: %d", time.getSecond()))
                .toString();
    }
}
