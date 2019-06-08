package com.javarush.task.task05.task0528;

/* 
Вывести на экран сегодняшнюю дату
*/

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Solution {
    public static void main(String[] args) {

        Date date = new Date();

//        TimeZone tz = TimeZone.getTimeZone("Europe/Moscow");
        DateFormat df = new SimpleDateFormat("dd MM yyyy");
//        df.setLenient(false);
//        df.setTimeZone(tz);

        System.out.println(df.format(date));

    }
}
