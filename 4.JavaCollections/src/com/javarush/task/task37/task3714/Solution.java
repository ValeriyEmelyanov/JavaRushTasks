package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/
public class Solution {
    private static Map<Character, Integer> romanNums;
    private static Map<String, Integer> romanBiNums;

    static {
        romanNums = new HashMap<>(7);
        romanNums.put('I', 1);
        romanNums.put('V', 5);
        romanNums.put('X', 10);
        romanNums.put('L', 50);
        romanNums.put('C', 100);
        romanNums.put('D', 500);
        romanNums.put('M', 1000);

        romanBiNums = new HashMap<>();
        romanBiNums.put("IV", 4);
        romanBiNums.put("IX", 9);
        romanBiNums.put("XL", 40);
        romanBiNums.put("XC", 90);
        romanBiNums.put("CD", 400);
        romanBiNums.put("CM", 900);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        char[] chars = s.toUpperCase().toCharArray();
        int charsLen = chars.length;

        int sum = 0;

        for (int i = charsLen - 1; i >= 0; i--) {
            if (i - 1 >= 0) {
                Integer value = romanBiNums.get(new String(new char[]{chars[i - 1], chars[i]}));
                if (value != null) {
                    sum += value;
                    i--;
                    continue;
                }
            }

            Integer value = romanNums.get(chars[i]);
            if (value == null) {
                throw new RuntimeException("Error input!");
            }
            sum += value;
        }

        return sum;
    }
}
