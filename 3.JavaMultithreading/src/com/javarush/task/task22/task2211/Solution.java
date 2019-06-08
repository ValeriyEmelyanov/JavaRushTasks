package com.javarush.task.task22.task2211;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            //System.out.println("Необходимо передат в аргументы 2 имени файла (входной и выходной)!");
            return;
        }
        // D:\Валера\test01.txt

        FileInputStream input = new FileInputStream(args[0]);
        byte[] buffer = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while (input.available() > 0) {
            len = input.read(buffer);
            sb.append(new String(buffer, 0, len, "Windows-1251"));
        }
        input.close();

        buffer = sb.toString().getBytes("UTF-8");
        FileOutputStream output = new FileOutputStream(args[1]);
        output.write(buffer);
        output.close();
    }
}
