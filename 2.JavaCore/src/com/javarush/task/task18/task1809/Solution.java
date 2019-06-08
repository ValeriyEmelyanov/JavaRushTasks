package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = reader.readLine();
        String filename2 = reader.readLine();
        reader.close();

        FileInputStream in = new FileInputStream(filename1);
        byte[] buf = new byte[in.available()];
        int len = in.read(buf);
        in.close();

        FileOutputStream out = new FileOutputStream(filename2);
        for (int i = len - 1; i >= 0; i--) {
            out.write(buf[i]);
        }
        out.close();
    }
}
