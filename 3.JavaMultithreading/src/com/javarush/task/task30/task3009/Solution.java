package com.javarush.task.task30.task3009;

import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
        System.out.println(getRadix("2"));
    }

    private static Set<Integer> getRadix(String number) {
        Set<Integer> result = new HashSet<>();
        int value = 0;
        try {
            value = Integer.valueOf(number);
        } catch (NumberFormatException e) {
            return result;
        }
        for (int i = 2; i <= 36; i++) {
            String digit = Integer.toString(value, i);
            if (digit.equals(new StringBuilder(digit).reverse().toString())) {
                result.add(i);
            }
        }
        return  result;
    }
}