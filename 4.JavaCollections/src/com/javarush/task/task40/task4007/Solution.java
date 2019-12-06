package com.javarush.task.task40.task4007;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* 
Работа с датами
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
        String pattern = "";
        int mode = 0;
        if (date.matches("\\d{1,2}.\\d{1,2}.\\d{4}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
            pattern = "dd.MM.yyyy HH:mm:ss";
            mode = 1;
        } else if (date.matches("\\d{1,2}.\\d{1,2}.\\d{4}")) {
            pattern = "dd.MM.yyyy";
            mode = 2;
        } else if (date.matches("\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
            pattern = "HH:mm:ss";
            mode = 3;
        }

        DateFormat dateFormat = new SimpleDateFormat(pattern);
        Date d = null;
        try {
            d = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        String result = null;
        switch (mode) {
            case 1:
                result = outForTime(calendar, outForDate(calendar) + "\n");
                break;
            case 2:
                result = outForDate(calendar);
                break;
            case 3:
                result = outForTime(calendar, "");
        }

        System.out.println(result);
    }

    private static String outForDate(Calendar calendar) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("День: %d", calendar.get(Calendar.DATE)));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        builder.append(String.format("\nДень недели: %d", dayOfWeek == 1 ? 7 : dayOfWeek - 1));
        builder.append(String.format("\nДень месяца: %d", calendar.get(Calendar.DAY_OF_MONTH)));
        builder.append(String.format("\nДень года: %d", calendar.get(Calendar.DAY_OF_YEAR)));
        builder.append(String.format("\nНеделя месяца: %d", calendar.get(Calendar.WEEK_OF_MONTH)));
        builder.append(String.format("\nНеделя года: %d", calendar.get(Calendar.WEEK_OF_YEAR)));
        builder.append(String.format("\nМесяц: %d", calendar.get(Calendar.MONTH) + 1));
        builder.append(String.format("\nГод: %d", calendar.get(Calendar.YEAR)));
        return builder.toString();
    }

    private static String outForTime(Calendar calendar, String s) {
        StringBuilder builder = new StringBuilder(s);
        builder.append(String.format("AM или PM: %s", calendar.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));
        builder.append(String.format("\nЧасы: %d", calendar.get(Calendar.HOUR)));
        builder.append(String.format("\nЧасы дня: %d", calendar.get(Calendar.HOUR_OF_DAY)));
        builder.append(String.format("\nМинуты: %d", calendar.get(Calendar.MINUTE)));
        builder.append(String.format("\nСекунды: %d", calendar.get(Calendar.SECOND)));
        return builder.toString();
    }
}
