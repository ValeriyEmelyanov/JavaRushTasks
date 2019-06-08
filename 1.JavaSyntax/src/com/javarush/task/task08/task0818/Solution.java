package com.javarush.task.task08.task0818;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("Ivanov", 500);
        map.put("Petrov", 501);
        map.put("Sidorov", 300);
        map.put("Smirnoff",499);
        map.put("Elkin", 600);
        map.put("Pirogov", 1000);
        map.put("Blinoff", 720);
        map.put("Dolgorukov", 450);
        map.put("Durakoff", 670);
        map.put("Trubicin", 420);
        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() < 500)
                list.add(entry.getKey());
        }
        for (String key : list) {
            map.remove(key);
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = createMap();
        removeItemFromMap(map);
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }

//        map.forEach((k, v) -> System.out.println(k + " " + v));

//        for (String s : map.keySet()) {
//            System.out.println(s);
//        }
    }
}