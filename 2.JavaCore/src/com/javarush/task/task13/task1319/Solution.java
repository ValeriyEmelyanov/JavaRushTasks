package com.javarush.task.task13.task1319;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.LinkedList;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();

        LinkedList<String> list = new LinkedList<String>();
        String s = "";
        while (!(s.equals("exit"))) {
            s = reader.readLine();
            list.add(s);
        }
        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        while (list.size() > 0) {
            writer.write(list.removeFirst());
            writer.newLine();
        }
        writer.close();
    }
}
