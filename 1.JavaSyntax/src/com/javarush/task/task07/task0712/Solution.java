package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int max = 0;
        int min = Integer.MAX_VALUE;
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            String s = reader.readLine();
            list.add(s);
            int l = s.length();
            if (max < l)
                max = l;
            if (min > l)
                min = l;
        }

        for (String s : list) {
            int l = s.length();
            if (l == min || l == max) {
                System.out.println(s);
                break;
            }
        }
    }
}
