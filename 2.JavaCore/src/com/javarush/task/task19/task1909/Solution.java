package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = reader.readLine();
        String filename2 = reader.readLine();
        reader.close();
        //String filename1 = "D:\\Валера\\test1.txt";
        //String filename2 = "D:\\Валера\\test1_.txt";

        BufferedReader in = new BufferedReader(new FileReader(filename1));
        BufferedWriter out = new BufferedWriter(new FileWriter(filename2));
        String line;
        while ((line = in.readLine()) != null) {
            out.write(line.replaceAll("\\.", "!"));
            if (in.ready()) {
                out.newLine();
            }
        }
        in.close();
        out.close();
    }
}
