package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sURL = reader.readLine();
        reader.close();
//        String sURL = "http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo";
//        String sURL = "http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo";

        ArrayList<String> list = new ArrayList<String>();
        int start = sURL.indexOf("?");
        int end, nextEq, nextAnd;
        String parametr;
        String objValue = null;
        if (start > -1) {
            start += 1;
            while (true) {
                nextEq = sURL.indexOf("=", start);
                nextAnd = sURL.indexOf("&", start);

                if (nextEq == -1 && nextAnd == -1) break;
                if (nextEq == -1 && nextAnd > -1) end = nextAnd;
                else if (nextEq > -1 && nextAnd == -1) end = nextEq;
                else end = Math.min(nextEq, nextAnd);
                parametr = sURL.substring(start, end);
                list.add(parametr);

                if (parametr != null && parametr.equals("obj") && nextEq != -1) {
                    if (nextAnd != -1)
                        objValue = sURL.substring(++nextEq, nextAnd);
                    else
                        objValue = sURL.substring(++nextEq);
                }

                if (nextAnd == -1 || nextAnd == sURL.length() - 1) break;

                start = ++nextAnd;
            }
        }

        for (String s : list) {
            System.out.print(s + " ");
        }
        System.out.println();

        if (objValue != null) {
            try {
                alert(Double.parseDouble(objValue));
            } catch (NumberFormatException e) {
                alert(objValue);
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
