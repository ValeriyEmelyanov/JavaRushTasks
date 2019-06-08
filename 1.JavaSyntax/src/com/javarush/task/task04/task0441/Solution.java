package com.javarush.task.task04.task0441;


/* 
Как-то средненько
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
        // Work
        int maxNum = nums[0];
        int minNum = nums[0];
        int indOfMax = 0;
        int indOfMin = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxNum) {
                maxNum = nums[i];
                indOfMax = i;
            }
            if (nums[i] < minNum) {
                minNum = nums[i];
                indOfMin = i;
            }
        }
        if (maxNum == minNum) {
            // All are equal
            System.out.println(maxNum);
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (i != indOfMax && i != indOfMin) {
                    System.out.println(nums[i]);
                    break;
                }
            }

        }

    }
}
