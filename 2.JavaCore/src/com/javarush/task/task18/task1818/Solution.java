package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = reader.readLine();
        String filename2 = reader.readLine();
        String filename3 = reader.readLine();
        reader.close();
//        String filename1 = "D:\\Валера\\test100.txt";
//        String filename2 = "D:\\Валера\\test1.txt";
//        String filename3 = "D:\\Валера\\test2.txt";

        String line;
        FileOutputStream out = new FileOutputStream(filename1);
        appendFromFile(out, filename2);
        appendFromFile(out, filename3);
        out.close();
    }

    private static void appendFromFile(OutputStream out, String filename) throws IOException {
        FileInputStream in = new FileInputStream(filename);
        int bufferLength = 1024;
        int len;
        byte[] buffer = new byte[bufferLength];
        while (in.available() > 0) {
            len = in.read(buffer);
            out.write(buffer, 0, len);
        }
        in.close();
    }
}
