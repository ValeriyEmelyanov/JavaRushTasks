package com.javarush.task.task04.task0424;

/* 
Три числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        // Input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = new int[3];
        String sBuf;
        for (int i = 0; i < nums.length; i++) {
            sBuf = reader.readLine();
            nums[i] = Integer.parseInt(sBuf);
        }
        // Work
        if (nums[0] == nums[1] && nums[0] != nums[2])
            System.out.println(3);
        else if (nums[0] == nums[2] && nums[0] != nums[1])
            System.out.println(2);
        else if (nums[1] == nums[2] && nums[1] != nums[0])
            System.out.println(1);
    }
}
