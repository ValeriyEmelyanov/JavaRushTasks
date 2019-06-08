package com.javarush.task.task04.task0418;

/* 
Минимум двух чисел
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sBuf = reader.readLine();
        int num1 = Integer.parseInt(sBuf);
        sBuf = reader.readLine();
        int num2 = Integer.parseInt(sBuf);
        if (num1 < num2)
            System.out.println(num1);
        else
            System.out.println(num2);
    }
}