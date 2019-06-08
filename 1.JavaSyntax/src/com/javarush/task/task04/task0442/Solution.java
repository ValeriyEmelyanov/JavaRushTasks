package com.javarush.task.task04.task0442;


/* 
Суммирование
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num;
        int sum = 0;
        do {
            num = Integer.parseInt(reader.readLine());
            sum += num;
        } while (num != -1);
        System.out.println(sum);
    }
}
