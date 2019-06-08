package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = new int[15];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(reader.readLine());
        }

        int odd = 0;
        int even = 0;
        for (int i = 0; i < array.length; i += 2) {
            even += array[i];
            if (i + 1 < array.length)
                odd += array[i + 1];
        }

        String message = (odd > even)
                ? "В домах с нечетными номерами проживает больше жителей."
                : "В домах с четными номерами проживает больше жителей.";
        System.out.println(message);
    }
}
