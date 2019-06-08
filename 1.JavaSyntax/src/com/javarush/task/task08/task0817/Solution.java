package com.javarush.task.task08.task0817;

import java.util.*;

/* 
Нам повторы не нужны
*/

public class Solution {
    public static HashMap<String, String> createMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Ivanov", "Ivan");
        map.put("Petrov", "Oleg");
        map.put("Sidoroff", "Timoha");
        map.put("Smirnoff", "Vova");
        map.put("Minin", "Nikolay");
        map.put("Radchenko", "Ivan");
        map.put("Soloviev", "Ilya");
        map.put("Nikolskiy", "Igor");
        map.put("Orekhoff", "Vova");
        map.put("Titov", "Vova");

        return map;
    }

    public static void removeTheFirstNameDuplicates(Map<String, String> map) {
        HashSet<String> set = new HashSet<String>();
        for (Map.Entry<String, String> entry1 : map.entrySet()) {
            String s = entry1.getValue();
            for (Map.Entry<String, String> entry2 : map.entrySet()) {
                if (entry1.getKey().equals(entry2.getKey()))
                    continue;
                if (s.equals(entry2.getValue()))
                    set.add(s);
            }
        }
        for (String s : set) {
            removeItemFromMapByValue(map, s);
        }
    }

    public static void removeItemFromMapByValue(Map<String, String> map, String value) {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }

    public static void main(String[] args) {
//        HashMap<String, String> map = createMap();
//        removeTheFirstNameDuplicates(map);
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
    }
}
