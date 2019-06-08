package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        //Integer[] array = {5, 10, 2, 7, 15, 30};
        //sort(array);
        //Arrays.stream(array).forEach(x -> System.out.println(x));
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here

        // Cslculate median
        Arrays.sort(array);
        final double median;
        int index = array.length / 2;
        if (array.length % 2 == 0) {
            median = (array[index - 1] + array[index]) / 2.0;
        } else {
            median = array[index];
        }

        // Create comparator
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1 - median) == Math.abs(o2 - median) ? Integer.compare(o1, o2) :
                        ((Math.abs(o1 - median) > Math.abs(o2 - median) ? 1 : -1));
            }
        };

        // Finish sort
        Arrays.sort(array, comparator);

        return array;
    }
}
