package com.javarush.task.task15.task1525;

import sun.rmi.runtime.NewThreadAction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Файл в статическом блоке
*/

public class Solution {
    public static List<String> lines = new ArrayList<String>();
    static {
        BufferedReader reader = null;
        try {
            FileInputStream fstream = new FileInputStream(Statics.FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(fstream));
            String s;
            while ((s = reader.readLine()) != null) {
                lines.add(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(lines);
    }
}
