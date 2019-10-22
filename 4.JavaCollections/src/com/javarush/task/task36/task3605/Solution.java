package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String filename = args[0];

        TreeSet<Character> treeSet = new TreeSet<>();

        try (FileReader fileReader = new FileReader(filename)) {
            while (fileReader.ready()) {
                int data = fileReader.read();
                if (Character.isAlphabetic(data)) {
                    treeSet.add((char) (Character.toLowerCase(data)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        treeSet.stream().limit(5).forEach(System.out::print);
    }
}
