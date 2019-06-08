package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Solution {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("В качестве аргумента необходимо передать имя файла.");
            return;
        }

        BufferedReader reader = null;
        int spaceCount = 0;
        int symbolCount = 0;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
            String line;
            final char SPACE = 32;
            while ((line = reader.readLine()) != null) {
                char[] chars = line.toCharArray();
                symbolCount += chars.length;

                for (char c : chars) {
                    if (c == SPACE) {
                        spaceCount ++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        double result = (double) spaceCount / symbolCount * 100;
        DecimalFormat df = new DecimalFormat("#.##");
        //System.out.println(result);
        System.out.println(df.format(result));
//        System.out.println(new BigDecimal(result).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
    }
}
