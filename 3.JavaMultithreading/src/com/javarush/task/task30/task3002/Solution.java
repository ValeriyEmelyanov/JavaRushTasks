package com.javarush.task.task30.task3002;

import java.util.HashMap;
import java.util.Map;

/*
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        if (s == null || s.isEmpty()) return s;

        // Определим основание и строку для перевода
        int radix;
        String digit;
        if (s.length() > 1 && s.substring(0, 2).equals("0b")) {
            radix = 2;
            digit = s.substring(2);
        } else if (s.length() > 1 && s.substring(0, 2).equals("0x")) {
            radix = 16;
            digit = s.substring(2);
        } else if (s.substring(0, 1).equals("0")) {
            radix = 8;
            digit = s.substring(1);
        } else {
            return s;
        }

        return "" + Integer.parseInt(digit, radix);
    }
}
