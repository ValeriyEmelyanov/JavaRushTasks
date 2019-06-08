package com.javarush.task.task04.task0428;

/* 
Положительное число
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        // Input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = new int[3];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(reader.readLine());
        }
        // Analysis
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                count++;
        }
        // Output
        System.out.println(count);
    }
}
