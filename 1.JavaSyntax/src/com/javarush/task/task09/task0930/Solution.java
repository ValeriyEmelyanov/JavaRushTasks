package com.javarush.task.task09.task0930;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Задача по алгоритмам
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            String s = reader.readLine();
            if (s.isEmpty()) break;
            list.add(s);
        }

//        list.add("55");
//        list.add("aa");
//        list.add("44");
//        list.add("zz");
//        list.add("78");
//        list.add("bb");
//        list.add("1");
//        list.add("12");
//        list.add("34");
//        list.add("af");

        String[] array = list.toArray(new String[0]);
        sort(array);

        for (String x : array) {
            System.out.println(x);
        }
    }

    public static void sort(String[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (isNumber(array[j])) {
                    // Число

                    // Найти следующее число
                    int next = -1;
                    for (int k = j + 1; k <= i; k++) {
                        if (isNumber(array[k])) {
                            next = k;
                            break;
                        }
                    }

                    // Если сравнивать не с чем
                    if (next == -1) continue;

                    // Сравнить со следующим
//                    if (isGreaterThan(array[next], array[j])) {
                    if (Integer.parseInt(array[next]) > Integer.parseInt(array[j])) {
                        String tmp = array[j];
                        array[j] = array[next];
                        array[next] = tmp;
                    }
                } else {
                    // Строка

                    // Если сравнивать не с чем
                    int next = -1;
                    for (int k = j + 1; k <= i; k++) {
                        if (!isNumber(array[k])) {
                            next = k;
                            break;
                        }
                    }

                    // Достигли края?
                    if (next == -1) continue;

                    // Сравнить со следующим
                    if (isGreaterThan(array[j], array[next])) {
                        String tmp = array[j];
                        array[j] = array[next];
                        array[next] = tmp;
                    }
                }
            }
        }
    }

    // Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }


    // Переданная строка - это число?
    public static boolean isNumber(String s) {
        if (s.length() == 0) return false;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((i != 0 && c == '-') // Строка содержит '-'
                    || (!Character.isDigit(c) && c != '-') // или не цифра и не начинается с '-'
                    || (chars.length == 1 && c == '-')) // или одиночный '-'
            {
                return false;
            }
        }
        return true;
    }
}
