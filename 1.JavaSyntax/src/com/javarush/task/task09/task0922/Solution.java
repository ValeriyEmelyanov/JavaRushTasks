package com.javarush.task.task09.task0922;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* 
Какое сегодня число?
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sDate = reader.readLine();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(sDate);
        //df.applyPattern("MMM d,yyyy");
        SimpleDateFormat newDf = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
        System.out.println(newDf.format(date).toUpperCase());
    }
}
