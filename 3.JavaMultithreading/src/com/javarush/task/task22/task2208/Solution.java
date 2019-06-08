package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap();
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("city", "Kiev");
        map.put("age", null);

        System.out.println(getQuery(map));
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,String> entry : params.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            sb.append(" and ")
                    .append(entry.getKey())
                    .append(" = ")
                    .append('\'')
                    .append(entry.getValue())
                    .append('\'');
        }
        sb.delete(0, 5);
        return sb.toString();
    }
}
