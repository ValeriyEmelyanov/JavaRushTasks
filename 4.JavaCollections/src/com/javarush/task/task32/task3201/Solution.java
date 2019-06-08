package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        if (args.length != 3) {
            System.out.println("Wrong number of parameters!");
            return;
        }

        String fileName = args[0];
        int numder = Integer.parseInt(args[1]);
        String text = args[2];

        try (RandomAccessFile file = new RandomAccessFile(fileName, "rw")){
            long pos = numder > file.length() ? file.length() : numder;
            file.seek(pos);
            file.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
