package com.javarush.task.task29.task2907;

import java.math.BigDecimal;
import java.math.MathContext;

/* 
Этот странный BigDecimal
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getValue(1.1d, 1.2d));
    }

    public static BigDecimal getValue(double v1, double v2) {
        //return new BigDecimal(v1).add(new BigDecimal(v2));
        //return BigDecimal.valueOf(v1).add(BigDecimal.valueOf(v2)); // +!
        //return new BigDecimal(v1, MathContext.DECIMAL32).add(new BigDecimal(v2, MathContext.DECIMAL32));
        return new BigDecimal(String.valueOf(v1)).add(new BigDecimal(String.valueOf(v2)));
    }
}