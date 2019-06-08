package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        //String filename1 =  "D:\\Валера\\test11.txt";
        //String filename2 =  "D:\\Валера\\test12.txt";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = reader.readLine();
        String filename2 = reader.readLine();
        reader.close();

        FileReader in = new FileReader(filename1);
        FileWriter out = new FileWriter(filename2);
        int counter = 1;
        while (in.ready()) {
            int data = in.read();
            if (counter % 2 == 0) {
                out.write(data);
            }
            counter++;
        }
        in.close();
        out.close();
    }
}
