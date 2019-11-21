package com.javarush.task.task39.task3904;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Лестница
*/
public class Solution {
    private static int n = 7;
//    private static int n = 70;

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if (n == 0) return 1L;
        if (n < 0) return 0L;

        long[] variants = new long[n + 1];
        variants[0] = 1;
        variants[1] = 1;
        variants[2] = 2;
        variants[3] = 4;

        for (int i = 4; i <= n; i++) {
            variants[i] = variants[i - 3] + variants[i - 2] + variants[i - 1];
        }

        return variants[n];
    }
}

