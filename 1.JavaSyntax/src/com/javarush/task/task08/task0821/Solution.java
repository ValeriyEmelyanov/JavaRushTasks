package com.javarush.task.task08.task0821;

import java.util.HashMap;
import java.util.Map;

/* 
Однофамильцы и тёзки
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = createPeopleList();
        printPeopleList(map);
    }

    public static Map<String, String> createPeopleList() {
        HashMap<String, String> result = new HashMap<String, String>();
        result.put("Ivanov", "Ivan");
        result.put("Ivanov", "Semen");
        result.put("Petrov", "Ivan");
        result.put("Sminoff", "Igor");
        result.put("Lisin", "Vlad");
        result.put("Oreshin", "Kolja");
        result.put("Sundukoff", "Petr");
        result.put("Kurochkin", "Alexandr");
        result.put("Blinoff", "Pavel");
        result.put("Volkof", "Titomir");

        return result;
    }

    public static void printPeopleList(Map<String, String> map) {
        for (Map.Entry<String, String> s : map.entrySet()) {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }
}
