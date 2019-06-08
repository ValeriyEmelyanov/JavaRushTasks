package com.javarush.task.task15.task1514;

import java.util.HashMap;
import java.util.Map;

/* 
Статики-1
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();
    static {
        labels.put(1., "Раз");
        labels.put(1.5, "Полтора");
        labels.put(2.54, "Дюйм");
        labels.put(3.14, "Pi");
        labels.put(12., "Дюжина");
    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
