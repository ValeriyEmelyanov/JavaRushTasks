package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();

//        BufferedReader fileReader = new BufferedReader(new FileReader(filename));
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        ArrayList<Integer> list = new ArrayList<Integer>();
        Integer i;
        String line;
//        while ((line = fileReader.readLine()) != null) {
//            i = Integer.parseInt(line);
//            if (i % 2 == 0)
//                list.add(i);
//        }
//        fileReader.close();
//        fis.read()
        while ((line = bufReader.readLine()) != null) {
            i = Integer.parseInt(line);
            if (i % 2 == 0)
                list.add(i);
        }
        bufReader.close();

        Collections.sort(list);
        for (Integer num : list) {
            System.out.println(num);
        }
    }
}
