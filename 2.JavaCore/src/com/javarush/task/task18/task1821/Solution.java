package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        if (args.length < 1) {
            System.out.println("Необходимл в качестве аргумента передать имя файла.");
            return;
        }

        FileInputStream in = new FileInputStream(args[0]);
        char key;
        TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
        while (in.available() > 0) {
            key = (char) in.read();
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
        in.close();

        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
