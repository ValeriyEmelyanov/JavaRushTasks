package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();

        FileInputStream in = new FileInputStream(filename);
        HashSet<Integer> set = new HashSet<Integer>();
        while (in.available() > 0) {
            set.add(in.read());
        }
        in.close();

        int[] arr = new int[set.size()];
        int i = 0;
        for (Integer b : set) {
            arr[i++] = b;
        }
        Arrays.sort(arr);
        for (int b : arr) {
            System.out.print(b + " ");
        }
    }
}
