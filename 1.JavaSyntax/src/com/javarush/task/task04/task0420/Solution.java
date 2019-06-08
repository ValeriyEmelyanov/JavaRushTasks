package com.javarush.task.task04.task0420;

/* 
Сортировка трех чисел
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        // Input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sBuf;
        int[] nums = new int[3];
        for (int i = 0; i < nums.length; i++) {
            sBuf = reader.readLine();
            nums[i] = Integer.parseInt(sBuf);
        }
        // Sort
        int iTmp;
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    iTmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = iTmp;
                }
            }
        }
        // Output
        for (int i = 1; i <= nums.length; i++) {
            System.out.print(nums[nums.length - i] + " ");
        }
    }
}
