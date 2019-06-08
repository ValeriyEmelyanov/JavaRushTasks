package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws Exception {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String filename1 = reader.readLine();
            String filename2 = reader.readLine();
//        String filename1 = "D:\\Валера\\test1.txt";
//        String filename2 = "D:\\Валера\\test2.txt";
            reader.close();

            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename1)));
            String line;
            while ((line = reader.readLine()) != null){
                Solution.allLines.add(line);
            }
            reader.close();

            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename2)));
            while ((line = reader.readLine()) != null){
                Solution.forRemoveLines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            (new Solution()).joinData();
        } catch (CorruptedDataException e) {
        }
    }

    public void joinData() throws CorruptedDataException {

        if (Solution.allLines.containsAll(Solution.forRemoveLines)) {
            Solution.allLines.removeAll(Solution.forRemoveLines);
            //System.out.println(Solution.allLines);
        } else {
            Solution.allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
