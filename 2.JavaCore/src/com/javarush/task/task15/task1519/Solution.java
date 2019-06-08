package com.javarush.task.task15.task1519;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

/* 
Разные методы для разных типов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String symbolsForDoulbe = "1234567890.";
//        String symbolsForInteger = "1234567890";
        while (true) {
            String s = reader.readLine();
            try {
                if (s == null || s.isEmpty()) {
                    print(s);
                } else if (s.equals("exit")) {
                    break;
                } else if (s.contains(".")) {
                    print(Double.parseDouble(s));
                } else {
                    int i = Integer.parseInt(s);
                    if (i > 0 && i < 128) print((short) i);
                    else print(i);
                }
            } catch (Exception e) {
                print(s);
            }
//            if (s == null || s.isEmpty()) {
//                print(s);
//            } else if (s.equals("exit")) {
//                break;
//            } else if (isMatch(s, symbolsForInteger)) {
//                int i = Integer.parseInt(s);
//                if (i > 0 && i < 128) print((short) i);
//                else print(i);
//            } else if (isMatch(s, symbolsForDoulbe) && isOneDot(s)) {
//                print(Double.parseDouble(s));
//            } else {
//                print(s);
//            }
        }

    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }

//    public static boolean isMatch(String s, String symbols) {
//        for (int i = 0; i < s.length(); i++) {
//            if (!symbols.contains(s.subSequence(i, i + 1))) return false;
//        }
//        return true;
//    }
//
//    public static boolean isOneDot(String s) {
//        return s.indexOf(".") == s.lastIndexOf(".");
//    }
}
