package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("В качестве параметра необходимо указать имя файла");
            return;
        }

        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        TreeMap<String, Double> map = new TreeMap<>();
        String line;
        String[] words;
        String key;
        Double value;
        while ((line = in.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }
            words = line.split("\\s");
            key = words[0];
            value = Double.parseDouble(words[1]);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + value);
            } else
                map.put(key, value);
        }
        in.close();

        double maxValue = 0;
        for (Double val : map.values()) {
            if (val > maxValue) {
                maxValue = val;
            }
        }

        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getValue() == maxValue) {
                System.out.println(entry.getKey());
            }
        }
    }
}
