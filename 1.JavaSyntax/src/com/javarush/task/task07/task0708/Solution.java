package com.javarush.task.task07.task0708;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Самая длинная строка
*/

public class Solution {
    private static List<String> strings;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        strings = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            strings.add(reader.readLine());
        }

        int[] lens = new int[strings.size()];
        int max = 0;
        for (int i = 0; i < strings.size(); i++) {
            int buf = strings.get(i).length();
            lens[i] = buf;
            if (buf > max)
                max = buf;
        }

        for (int i = 0; i < lens.length; i++) {
            if (lens[i] == max)
                System.out.println(strings.get(i));
        }
    }
}
