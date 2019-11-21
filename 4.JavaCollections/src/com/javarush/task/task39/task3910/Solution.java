package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(10));
        System.out.println(isPowerOfThree(27));
    }

    public static boolean isPowerOfThree(int n) {
        for (int i = 1; i <= n ; i *= 3) {
            if (i == n) {
                return true;
            }
        }
        return false;
    }
}
