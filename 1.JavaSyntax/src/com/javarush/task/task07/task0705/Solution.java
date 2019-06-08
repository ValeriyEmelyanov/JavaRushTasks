package com.javarush.task.task07.task0705;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Один большой массив и два маленьких
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] bigArr = new int[20];
        int[] small1 = new int[10];
        int[] small2 = new int[10];

        for (int i = 0; i < bigArr.length; i++) {
            bigArr[i] = Integer.parseInt(reader.readLine());
        }

        for (int i = 0; i < 10; i++) {
            small1[i] = bigArr[i];
            small2[i] = bigArr[i + 10];
        }
        for (int i = 0; i < small2.length; i++) {
            System.out.println(small2[i]);
        }
    }
}
