package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {

        //if (args.length < 1) {
        //    System.out.println("incorrect");
        //    return;
        //}

        try {
            String digit = args[0];
            //String digit = "1f00";
            if (digit.length() > 255 || !digit.matches("\\w+")) {
                System.out.println("incorrect");
            } else {

                boolean done = false;
                for (int i = 2; i <= 36; i++) {
                    try {
                        BigInteger tmp = new BigInteger(digit, i);
                        System.out.println(i);
                        done = true;
                        break;
                    } catch (Exception e) {
                    }
                }

                if (!done) {
                    System.out.println("incorrect");
                }
            }
        } catch (Exception e) {}
    }
}