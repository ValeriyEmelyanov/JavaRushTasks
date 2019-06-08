package com.javarush.task.task18.task1825;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TreeSet<String> set = new TreeSet<String>();
        while (true) {
            // D:\Валера\Lion.avi.part1
            String filename = reader.readLine();
            if (filename != null && filename.equals("end")) {
                break;
            }
            set.add(filename);
        }

        if (set.size() < 1) {
            return;
        }

        String filename = set.first().replace(".part1", "");
        FileOutputStream target = new FileOutputStream(filename);
        for (String fn : set) {
            FileInputStream source = new FileInputStream(fn);
            byte[] buffer = new byte[source.available()];
            while (source.available() > 0) {
                int len = source.read(buffer);
                target.write(buffer, 0, len);
            }
            source.close();
        }
        target.close();
    }
}
