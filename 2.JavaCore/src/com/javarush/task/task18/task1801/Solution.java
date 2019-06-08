package com.javarush.task.task18.task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine(); // d:/Валера/test1.txt
        reader.close();

        FileInputStream fis = new FileInputStream(filename);
        int data;
        int maxValue = Byte.MIN_VALUE;
        while (fis.available() > 0) {
            data = fis.read();
            if (data > maxValue) maxValue = data;
        }
        fis.close();
        System.out.println(maxValue);
    }
}
