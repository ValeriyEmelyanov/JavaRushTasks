package com.javarush.task.task04.task0419;

/* 
Максимум четырех чисел
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = new int[4];
        String sBuf;
        for (int i = 0; i < nums.length; i++) {
            sBuf = reader.readLine();
            nums[i] = Integer.parseInt(sBuf);
        }
        int maxNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (maxNum < nums[i])
                maxNum = nums[i];
        }
        System.out.println(maxNum);
    }
}
