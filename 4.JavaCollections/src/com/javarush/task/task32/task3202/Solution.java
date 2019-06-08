package com.javarush.task.task32.task3202;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("D:\\Валера\\test2.txt"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter sw = new StringWriter();

        if (is == null) {
            return sw;
        }

        try {
            int data;
            while ((data = is.read()) != -1) {
                sw.write(data);
            }
            return sw;
        } catch (IOException e) {
            return new StringWriter();
        }
    }
}