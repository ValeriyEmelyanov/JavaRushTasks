package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        //String fileName = "D:\\Валера\\test7.txt";

        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = in.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }
            char[] arr = line.toCharArray();
            int half = arr.length / 2;
            for (int i = 0; i < half; i++) {
                char tmp = arr[i];
                arr[i] = arr[arr.length - i - 1];
                arr[arr.length - i - 1] = tmp;
            }
            line = String.valueOf(arr);
            System.out.println(line);
        }
        in.close();
    }
}
