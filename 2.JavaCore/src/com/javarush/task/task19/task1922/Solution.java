package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        //String fileName = "D:\\Валера\\test6.txt";

        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = in.readLine()) != null) {
            int counter = 0;
            String[] strArr = line.split("\\s");
            for (String word : words) {
                for (String str : strArr) {
                    if (word.equals(str)) {
                        counter ++;
                    }
                }
            }
            if (counter == 2) {
                System.out.println(line);
            }
        }
        in.close();
    }
}
