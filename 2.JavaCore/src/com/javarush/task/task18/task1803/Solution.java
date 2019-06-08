package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        FileInputStream in = new FileInputStream(filename);
        Integer data;
        while (in.available() > 0) {
            data = in.read();
            Integer value = map.get(data);
            if (value == null) {
                map.put(data, 1);
            } else {
                map.put(data, ++value);
            }
        }
        in.close();

        int max = 0;
        for (Integer value: map.values()) {
            if (value > max) {
                max = value;
            }
        }

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() == max) {
                System.out.print(entry.getKey().toString() + " ");
            }
        }
    }
}
