package com.javarush.task.task20.task2025;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Алгоритмы-числа
*/
public class Solution {
    public static long[] getNumbers(long N) {
        int[] digits = new int[20];
        int digLen = 1;
        int index;

        long[] digPows = {0,1,2,3,4,5,6,7,8,9};
        long sum;
        boolean changedDigLen = false;
        long sum2 = 0;
        List<Long> list = new ArrayList<>();

        for (long i = 1; i < N; i++) {

            // Собираем цифры числа
            index = 0;
            digits[0]++;
            while (digits[index] > 9) {
                digits[index] = 0;
                ++digits[++index];
                changedDigLen = true;
            }

            // длина числа
            if (index + 1 > digLen) {
                digLen = index + 1;
                // при увеличении длины числа учеличиваем степень цифр
                for (int j = 0; j < digPows.length; j++) {
                    if (j <= 1) continue;
                    digPows[j] *= j;
                }
            }

            // Сумма степеней цифр
            sum = 0;
            if (changedDigLen) {
                // Полный пересчет
                sum2 = 0;
                for (int j = 1; j < digLen; j++) {
                    sum2 += digPows[digits[j]];
                }
                changedDigLen = false;
            }
            sum = sum2 + digPows[digits[0]];

            // Проверка
            if (sum == i) {
                //System.out.println(i);
                list.add(i);
            }
        }

        // Переложить в массив
        long[] result = new long[list.size()];
        int ind = 0;
        for (Long aLong : list) {
            result[ind++] = aLong;
        }

        return result;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        long[] numbers = getNumbers(Integer.MAX_VALUE);

        long finish = System.currentTimeMillis();
        System.out.println("Time: " + (finish - start) / 1000 + " s");

        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
    }
}

//    public static long[] getNumbers(long N) {
//        if (N <= 0) {
//            return new long[0];
//        }
//
//        long[] armstrongNum = {
//                1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407, 1634, 8208, 9474,
//                54748, 92727, 93084, 548834, 1741725, 4210818, 9800817, 9926315,
//                24678050, 24678051, 88593477, 146511208, 472335975, 534494836,
//                912985153, 4679307774L, 32164049650L, 32164049651L, 40028394225L,
//                42678290603L, 44708635679L, 49388550606L, 82693916578L,
//                94204591914L, 28116440335967L, 4338281769391370L,
//                4338281769391371L, 21897142587612075L, 35641594208964132L,
//                35875699062250035L, 1517841543307505039L, 3289582984443187032L,
//                4498128791164624869L, 4929273885928088826L};
//
//        long[] workResult = new long[50];
//        int len = 0;
//        for (long num : armstrongNum) {
//            if (num < N) {
//                workResult[len++] = num;
//            }
//        }
//
//        long[] result = new long[len];
//        for (int i = 0; i < len; i++) {
//            result[i] = workResult[i];
//        }
//
//        return result;
//    }
