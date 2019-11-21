package com.javarush.task.task39.task3908;

import java.util.HashMap;
import java.util.Map;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("topot"));
    }

    public static boolean isPalindromePermutation(String s) {
        if (s == null || s.isEmpty()) return false;

        char[] chars = s.toLowerCase().toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int odds = 0;
        for (Integer i : map.values()) {
            if (i % 2 == 1) {
                odds++;
            }
        }

        return odds <= 1;
    }
}
