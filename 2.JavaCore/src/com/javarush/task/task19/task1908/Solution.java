package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.nio.Buffer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = reader.readLine();
        String filename2 = reader.readLine();
        reader.close();
        //String filename1 = "D:\\Валера\\test2.txt";
        //String filename2 = "D:\\Валера\\test3.txt";

        BufferedReader in = new BufferedReader(new FileReader(filename1));
        int len;
        char[] cbuf = new char[32];
        StringBuilder sb = new StringBuilder();
        while (in.ready()) {
            len = in.read(cbuf);
            sb.append(cbuf, 0, len);
        }
        in.close();

        BufferedWriter out = new BufferedWriter(new FileWriter(filename2));
        String text = sb.toString();
        String[] words = text.split("\\s|\\n");
        for (int i = 0; i < words.length; i++) {
            if (words[i].matches("\\d+")) {
                out.write(words[i] + " ");
            }
        }
        out.close();
    }
}
