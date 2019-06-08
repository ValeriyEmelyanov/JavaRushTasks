package com.javarush.task.task09.task0927;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* 
Десять котов
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap() {
        HashMap<String, Cat> map = new HashMap<String, Cat>();
        mapPut(map, "Васька");
        mapPut(map, "Барсик");
        mapPut(map, "Мурзик");
        mapPut(map, "Рыжик");
        mapPut(map, "Мурка");
        mapPut(map, "Пушистик");
        mapPut(map, "Кис-кис");
        mapPut(map, "Василиса");
        mapPut(map, "Том");
        mapPut(map, "Багира");
        return map;
    }

    private static void mapPut(HashMap map, String name) {
        map.put(name, new Cat(name));
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map) {
        Set<Cat> set = new HashSet<Cat>();
        for (Cat cat : map.values()) {
            set.add(cat);
        }
        return set;
    }

    public static void printCatSet(Set<Cat> set) {
        for (Cat cat : set) {
            System.out.println(cat);
        }
    }

    public static class Cat {
        private String name;

        public Cat(String name) {
            this.name = name;
        }

        public String toString() {
            return "Cat " + this.name;
        }
    }


}
