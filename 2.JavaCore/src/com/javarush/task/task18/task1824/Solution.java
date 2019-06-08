package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean doExit = false;
        while (!doExit) {
            // D:\Валера\test1.txt
            String filename = reader.readLine();
            FileInputStream in = null;
            try {
                in = new FileInputStream(filename);
            } catch (FileNotFoundException ex) {
                //ex.printStackTrace();
                System.out.println(filename);
                doExit = true;
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
