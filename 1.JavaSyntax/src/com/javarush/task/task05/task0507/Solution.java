package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double sum = 0;
        int count = 0;
        int num;
        while (true){
            num = Integer.parseInt(reader.readLine());
            if (num == -1)
                break;
            sum += num;
            count++;
        }
        if (count == 0)
            System.out.println(0.0);
        else
            System.out.println(sum / count);
    }
}

