package com.javarush.task.task22.task2210;

import java.util.Arrays;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
        //System.out.println(Arrays.toString(getTokens("The weather is nice", " ")));
        //System.out.println(Arrays.toString(getTokens("level22.lesson13.task01", ".")));
        //System.out.println(Arrays.toString(getTokens("com.javarush.task.task22.task2210", ".")));
    }
    public static String [] getTokens(String query, String delimiter) {
        if (query == null || query.isEmpty()) {
            return null;
        }
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        String[] tokens = new String[tokenizer.countTokens()];
        int i = 0;
        while (tokenizer.hasMoreTokens()) {
            tokens[i++] = tokenizer.nextToken();
        }
        return tokens;
    }
}
