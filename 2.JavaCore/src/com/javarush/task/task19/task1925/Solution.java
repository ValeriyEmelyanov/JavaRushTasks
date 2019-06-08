package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("В параметры должно быть передано 2 имени файлов");
            return;
        }

        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        BufferedWriter out = new BufferedWriter(new FileWriter(args[1]));
        String line;
        boolean needComma = false;
        char comma = ',';
        while ((line = in.readLine()) != null) {
            String[] words = line.split("\\s");
            for (String word : words) {
                if (word.length() > 6) {
                    if (needComma) {
                        out.write(comma);
                    } else {
                        needComma = true;
                    }
                    out.write(word);
                }
            }
        }
        in.close();
        out.close();
    }
}
