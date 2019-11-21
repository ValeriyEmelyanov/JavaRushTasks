package com.javarush.task.task39.task3903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/* 
Неравноценный обмен
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Please enter a number: ");

        long number = Long.parseLong(reader.readLine());
        System.out.println("Please enter the first index: ");
        int i = Integer.parseInt(reader.readLine());
        System.out.println("Please enter the second index: ");
        int j = Integer.parseInt(reader.readLine());

        System.out.println("The result of swapping bits is " + swapBits(number, i, j));
    }

    public static long swapBits(long number, int i, int j) {
        char[] prefix = new char[64];
        Arrays.fill(prefix, '0');

        char[] chars = new StringBuilder(new String(prefix))
                .append(Long.toBinaryString(number))
                .reverse()
                .subSequence(0, 64)
                .toString()
                .toCharArray();

        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;

        String binaryString = new StringBuilder(new String(chars))
                .reverse()
                .toString();

        return Long.valueOf(binaryString, 2);
    }

    private static void reverse(char[] chars) {
        char buf;
        int j;
        for (int i = 0; i < chars.length / 2; i++) {
            buf = chars[i];
            j = chars.length - i - 1;
            chars[i] = chars[j];
            chars[j] = buf;
        }
    }
}
