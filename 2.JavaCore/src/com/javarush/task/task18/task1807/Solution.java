package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();

        FileInputStream in = new FileInputStream(filename);
        int count = 0;
        int comma = 44;
        while (in.available() > 0) {
            if (in.read() == 44) {
                count++;
            }
        }
        in.close();
        System.out.println(count);
    }
}
