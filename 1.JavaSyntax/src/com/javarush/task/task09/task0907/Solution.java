package com.javarush.task.task09.task0907;

/* 
Исключение при работе с числами
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        try {

        int a = 42 / 0;

        } catch (ArithmeticException e) {
//            System.out.println(e);
//            System.out.println(e.getClass());
//            System.out.println(e.getClass().getName());
//            System.out.println(e.getCause());
//            System.out.println(e.getLocalizedMessage());
//            System.out.println(e.getMessage());
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
