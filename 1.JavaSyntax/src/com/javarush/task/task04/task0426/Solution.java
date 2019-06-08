package com.javarush.task.task04.task0426;

/* 
Ярлыки и числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sBuf = reader.readLine();
        int num = Integer.parseInt(sBuf);
        if (num < 0 && num % 2 == 0)
            System.out.println("отрицательное четное число");
        else if (num < 0)
            System.out.println("отрицательное нечетное число");
        else if (num > 0 && num % 2 == 0)
            System.out.println("положительное четное число");
        else if (num > 0)
            System.out.println("положительное нечетное число");
        else
            System.out.println("ноль");
    }
}
