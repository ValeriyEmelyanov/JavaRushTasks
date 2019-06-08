package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        //String filename =  "D:\\Валера\\test11.txt";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();

        FileReader in = new FileReader(filename);
        char[] chars = new char[64];
        int len;
        StringBuilder sb = new StringBuilder();
        while (in.ready()) {
            len = in.read(chars);
            if (len > 0) {
                sb.append(chars, 0, len);
            }
        }
        in.close();

        String text = sb.toString();
        int counter = 0;
        String[] words = text.split("(\\.|,|!|\\?|:|;|'|-|\\)|\\(|\\)|\"|\\s|\n)");
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("world")) {
                counter++;
            }
        }
//        int index = 0;
//        while (true) {
//            index = text.indexOf("world", index);
//            if (index == -1) {
//                break;
//            }
//            index +=5;
//            counter++;
//        }
        System.out.println(counter);
    }
}
