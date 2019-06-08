package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        if (args.length != 3) {
            System.out.println("Invalid parameters!");
            return;
        }

        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];

        RandomAccessFile file = new RandomAccessFile(fileName, "rw");
        file.seek(number);
        byte[] buffer = new byte[text.getBytes().length];
        int len = file.read(buffer, 0, text.getBytes().length);
        //String readed = new String(buffer, 0, len);
        String readed = new String(buffer);

        file.seek(file.length());
        //String result = text.equals(readed) ? Boolean.toString(true) : Boolean.toString(false);
        //file.write(result.getBytes());
        if (text.equals(readed)) {
            file.write("true".getBytes());
        } else {
            file.write("false".getBytes());
        }

        file.close();
    }
}
