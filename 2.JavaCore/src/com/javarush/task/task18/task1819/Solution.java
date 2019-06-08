package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = reader.readLine();
        String filename2 = reader.readLine();
//        String filename1 = "D:\\Валера\\test1.txt";
//        String filename2 = "D:\\Валера\\test2.txt";

        FileInputStream in1 = new FileInputStream(filename1);
        byte[] buffer1 = new byte[in1.available()];
        in1.read(buffer1);
        in1.close();
        FileInputStream in2 = new FileInputStream(filename2);
        byte[] buffer2 = new byte[in2.available()];
        in2.read(buffer2);
        in2.close();
        FileOutputStream out = new FileOutputStream(filename1);
        out.write(buffer2);
        out.write(buffer1);
        out.close();
    }
}
