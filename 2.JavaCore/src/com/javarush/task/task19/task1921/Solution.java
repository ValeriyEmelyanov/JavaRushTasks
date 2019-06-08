package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException {
        if (args.length < 1) {
            System.out.println("Первым параметром необходимо передать имя файла.");
            return;
        }

        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        String line;
        Pattern patternName = Pattern.compile("[^\\d]+");
        Matcher matcherName;
        Pattern patternDate = Pattern.compile("\\d{1,2}\\s\\d{1,2}\\s\\d{4}");
        Matcher matcherDate;
        String name;
        String sDate;
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("d M yyyy");
        while ((line = in.readLine()) != null){
            name = null;
            date = null;
            if (line.isEmpty()) {
                continue;
            }
            matcherName = patternName.matcher(line);
            if (matcherName.find()) {
                name = matcherName.group().trim();
                //ystem.out.println("*" + name + "*");
            }
            matcherDate = patternDate.matcher(line);
            if (matcherDate.find()) {
                sDate = matcherDate.group();
                //System.out.println("*" + sDate + "*");
                date = dateFormat.parse(sDate);
                //System.out.println(date);
            }
            if (name != null) {
                PEOPLE.add(new Person(name, date));
            }
        }
        in.close();

        //for (Person person : PEOPLE) {
        //    System.out.println(person);
        //}
    }
}
