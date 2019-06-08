package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {

        if (args.length < 1) {
            System.out.println("Необхлдимо задать параметр id");
            return;
        }

        //int id = Integer.parseInt(args[0]);
        String id = args[0] + " ";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        //String filename = "D:\\Валера\\test2.txt";

        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        String line;
        int index;
        while ((line = in.readLine()) != null) {
            index = line.indexOf(" ");
            //if (id.equals(line.substring(0, index))) {
            if (line.startsWith(id)) {
                System.out.println(line);
                break;
            }
        }
        in.close();
    }
}
