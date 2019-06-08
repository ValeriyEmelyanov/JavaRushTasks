package com.javarush.task.task08.task0815;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* 
Перепись населения
*/

public class Solution {
    public static HashMap<String, String> createMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Иванов", "Иван");
        map.put("Лисицин", "Петр");
        map.put("Зайцев", "Михаил");
        map.put("Смирнов", "Александр");
        map.put("Петров", "Иван");
        map.put("Сидоров", "Тимофей");
        map.put("Сеенов", "Матвей");
        map.put("Федоров", "Иван");
        map.put("Пирогов", "Иван");
        map.put("Блинов", "Алексей");

        return  map;
    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name) {
        int count = 0;
        for (Map.Entry<String, String> entry : map.entrySet())
        {
            if (name.equals(entry.getValue()))
                count++;
        }
        return count;
    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String lastName) {
        int count = 0;
        for (Map.Entry<String, String> entry : map.entrySet())
        {
            if (lastName.equals(entry.getKey()))
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
//        HashMap<String, String> map = createMap();
    }
}
