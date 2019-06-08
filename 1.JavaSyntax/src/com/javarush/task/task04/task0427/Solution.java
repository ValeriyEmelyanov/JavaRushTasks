package com.javarush.task.task04.task0427;

/* 
Описываем числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sNum = reader.readLine();
        int num = Integer.parseInt(sNum);
        if (num > 0) {
            if (sNum.length() == 1 && num % 2 == 0)
                System.out.println("четное однозначное число");
            else if (sNum.length() == 1)
                System.out.println("нечетное однозначное число");
            else if (sNum.length() == 2 && num % 2 == 0)
                System.out.println("четное двузначное число");
            else if (sNum.length() == 2)
                System.out.println("нечетное двузначное число");
            else if (sNum.length() == 3 && num % 2 == 0)
                System.out.println("четное трехзначное число");
            else if (sNum.length() == 3)
                System.out.println("нечетное трехзначное число");
        }
    }
}
