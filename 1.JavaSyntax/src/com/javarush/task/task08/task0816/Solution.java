package com.javarush.task.task08.task0816;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Добрая Зинаида и летние каникулы
*/

public class Solution {
    public static HashMap<String, Date> createMap() throws ParseException {
        DateFormat df = new SimpleDateFormat("MMMMM d yyyy", Locale.ENGLISH);
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone", df.parse("JUNE 1 1980"));
        map.put("Ivanov", df.parse("MAY 15 1970"));
        map.put("Petrov", df.parse("APRIL 12 1982"));
        map.put("Smirnoff", df.parse("JULY 3 1965"));
        map.put("Shabalina", df.parse("AUGUST 22 1976"));
        map.put("Vinigoroff", df.parse("OCTOBER 30 1990"));
        map.put("Shatalova", df.parse("JANUARY 7 1992"));
        map.put("Minin", df.parse("FEBRUARY 22 1989"));
        map.put("Sannikov", df.parse("SEPTEMBER 1 1985"));
        map.put("Popoff", df.parse("MARCH 8 1993"));

        return map;
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map) {
        ArrayList<String> list = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        for (Map.Entry<String, Date> entry : map.entrySet()) {
            calendar.setTime(entry.getValue());
            int month = calendar.get(Calendar.MONTH);
            if (month >= Calendar.JUNE && month <= Calendar.AUGUST)
                list.add(entry.getKey());
        }
        for (String key : list) {
            map.remove(key);
        }
    }

    public static void main(String[] args) throws ParseException {

//        HashMap<String, Date> map = createMap();
//        removeAllSummerPeople(map);
//
//        DateFormat df = new SimpleDateFormat("MMMMM d yyyy", Locale.ENGLISH);
//        for (Map.Entry<String, Date> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " " + df.format(entry.getValue()));
//        }

    }
}
