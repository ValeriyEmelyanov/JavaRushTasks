package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Необходимо задать параметр - имя фала.");
            return;
        }

        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        String line;
        TreeMap<String, Double> map = new TreeMap<>();
        String[] words;
        String key;
        String value;
        while ((line = in.readLine()) != null) {
            words = line.split("\\s");
            key = words[0];
            value = words[1];
            if (map.containsKey(words[0])) {
                map.put(key, map.get(key) + Double.parseDouble(value));
            } else {
                map.put(key, Double.parseDouble(value));
            }
        }
        in.close();

        for (Map.Entry<String, Double> entry : map.entrySet()) {
            //System.out.println(String.format("%s %f", entry.getKey(), entry.getValue()));
            System.out.println(String.format(entry.getKey() + " " + entry.getValue()));
        }
    }
}
