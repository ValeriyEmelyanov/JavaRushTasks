package com.javarush.task.task05.task0529;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Консоль-копилка
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sBuf;
        int sum = 0;
        while (true) {
            sBuf = reader.readLine();
            if (sBuf.equals("сумма")) {
                break;
            } else {
                sum += Integer.parseInt(sBuf);
            }
        }
        System.out.println(sum);
    }
}
