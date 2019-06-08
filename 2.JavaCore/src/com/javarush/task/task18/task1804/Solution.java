package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();

        FileInputStream in = new FileInputStream(filename);
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Integer data;
        while (in.available() > 0) {
            data = in.read();
            Integer count = map.get(data);
            map.put(data, (count == null) ? 1 : ++count);
        }
        in.close();

        int min = Byte.MAX_VALUE;
        for (Integer count : map.values()) {
            if (count < min) {
                min = count;
            }
        }
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if (entry.getValue() == min) {
                System.out.print(entry.getKey() + " ");
            }
        }
    }
}
