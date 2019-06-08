package com.javarush.task.task08.task0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Омовение Рамы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
//        String s = "mama mila ramu";
        s = s.trim();

        ArrayList<Integer> indexes = new ArrayList<Integer>();
        indexes.add(0);
        int index = 1;
        while (true) {
            index = s.indexOf(" ", index);
            if (index == -1) {
                break;
            } else {
                indexes.add(++index);
            }
            if (index == s.length()) {
                break;
            }
        }

        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < indexes.size() - 1; i++) {
            String word = s.substring(indexes.get(i), indexes.get(i + 1));
            sb.append(word.substring(0, 1).toUpperCase());
            sb.append(word.substring(1));
        }
        String word = s.substring(indexes.get(indexes.size() - 1), s.length());
        sb.append(word.substring(0, 1).toUpperCase());
        sb.append(word.substring(1));

        s = sb.toString();
        System.out.println(s);
    }
}
