package com.javarush.task.task22.task2212;

import java.util.regex.Pattern;

/*
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null || telNumber.isEmpty()) {
            return false;
    }
        String onlyDigits = telNumber.replaceAll("\\D", "");
        if (onlyDigits.length() == 12) {
            return Pattern.matches("^\\+\\d*(\\(\\d{3}\\))?\\d*(-\\d+)?-?\\d*\\d$", telNumber);
        }
        if (onlyDigits.length() == 10) {
            return Pattern.matches("^(\\d|(\\(\\d{3}\\)))\\d*(-\\d+)?-?\\d*\\d$", telNumber);
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(checkTelNumber("+380501234567"));
//        System.out.println(checkTelNumber("+38(050)1234567"));
//        System.out.println(checkTelNumber("+38050123-45-67"));
//        System.out.println(checkTelNumber("+38)050(1234567"));
//        System.out.println(checkTelNumber("+38(050)1-23-45-6-7"));
//        System.out.println(checkTelNumber("050123-4567"));
//        System.out.println(checkTelNumber("050ххх4567"));
//        System.out.println(checkTelNumber("050123456"));
//        System.out.println(checkTelNumber("(0)501234567"));
//        System.out.println(checkTelNumber("(012)5012345"));
    }
}
