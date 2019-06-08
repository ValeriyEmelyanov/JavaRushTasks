package com.javarush.task.task08.task0814;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* 
Больше 10? Вы нам не подходите
*/

public class Solution {
    public static HashSet<Integer> createSet() {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < 20; i++) {
            set.add(i);
        }
        return set;
    }

    public static HashSet<Integer> removeAllNumbersGreaterThan10(HashSet<Integer> set) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (Integer num : set) {
            if (num > 10)
                list.add(num);
        }
        for (Integer num : list) {
            set.remove(num);
        }
        return set;
    }

    public static void main(String[] args) {
//        HashSet<Integer> set = createSet();
//        removeAllNumbersGreaterThan10(set);
//        for (Integer num : set) {
//            System.out.println(num);
//        }
    }
}
