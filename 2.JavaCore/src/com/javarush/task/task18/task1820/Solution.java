package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = reader.readLine();
        String filename2 = reader.readLine();
        reader.close();
        //String filename1 = "D:\\Валера\\test101.txt";
        //String filename2 = "D:\\Валера\\test102.txt";

        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename1)));
        String line;
        final char SPACE = 32;
        String buf;
        ArrayList<String> list = new ArrayList<String>();
        while ((line = in.readLine()) != null) {
            int atIndex = 0;
            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == SPACE || i == chars.length - 1) {
                    buf = String.valueOf(chars, atIndex, (i == chars.length - 1) ? (i + 1 - atIndex) : (i - atIndex));
                    list.add(buf);
                    atIndex = ++i;
                }
            }
        }
        in.close();

        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            double num = Double.parseDouble(s);
            long roundNum = Math.round(num);
            sb.append(roundNum + " ");
        }

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename2)));
        out.write(sb.toString());
        out.close();

    }
}
