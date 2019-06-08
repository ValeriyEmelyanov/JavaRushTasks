package com.javarush.task.task30.task3001;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/*
Конвертер систем счислений
*/
public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumberSystemType._10, "6");
        Number result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumberSystemType._16, "6df");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
        System.out.println(result);    //expected 3337

        number = new Number(NumberSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result);    //expected abcdefabcdef
    }

    public static Number convertNumberToOtherNumberSystem(Number number, NumberSystem expectedNumberSystem)
            throws NumberFormatException {
        // Проверка валидности
        if (!number.isValid()) {
            throw new NumberFormatException();
        }

        // Соответствие символа и числа (значения)
        Map<Character, Integer> charMap = new HashMap<>(16);
        charMap.put('0', 0);
        charMap.put('1', 1);
        charMap.put('2', 2);
        charMap.put('3', 3);
        charMap.put('4', 4);
        charMap.put('5', 5);
        charMap.put('6', 6);
        charMap.put('7', 7);
        charMap.put('8', 8);
        charMap.put('9', 9);
        charMap.put('A', 10);
        charMap.put('B', 11);
        charMap.put('C', 12);
        charMap.put('D', 13);
        charMap.put('E', 14);
        charMap.put('F', 15);

        // Перевод в десятичную систему
        /*
        long value10 = 0;
        if (number.getNumberSystem() == NumberSystemType._10) {
            value10 = Long.valueOf(number.getDigit());
        } else {
            char[] chars = number.getDigit().toUpperCase().toCharArray();
            int base = number.getNumberSystem().getNumberSystemIntValue();
            for (int i = 0; i < chars.length; i++) {
                value10 += charMap.get(chars[i]) * pow(base, chars.length - 1 - i);
            }
        }
        */
        BigInteger value10 = null;
        if (number.getNumberSystem() == NumberSystemType._10) {
            value10 = new BigInteger(number.getDigit());
        } else {
            value10 = new BigInteger("0");
            char[] chars = number.getDigit().toUpperCase().toCharArray();
            int base = number.getNumberSystem().getNumberSystemIntValue();
            for (int i = 0; i < chars.length; i++) {
                value10 = value10.add(
                        new BigInteger(String.valueOf(charMap.get(chars[i]))).multiply(
                                new BigInteger(String.valueOf(base)).pow(chars.length - 1 - i)
                        )
                );
            }
        }

        // Соотвествие числа и символа
        Map<Integer, Character> digitMap = new HashMap<>();
        digitMap.put(0, '0');
        digitMap.put(1, '1');
        digitMap.put(2, '2');
        digitMap.put(3, '3');
        digitMap.put(4, '4');
        digitMap.put(5, '5');
        digitMap.put(6, '6');
        digitMap.put(7, '7');
        digitMap.put(8, '8');
        digitMap.put(9, '9');
        digitMap.put(10, 'a');
        digitMap.put(11, 'b');
        digitMap.put(12, 'c');
        digitMap.put(13, 'd');
        digitMap.put(14, 'e');
        digitMap.put(15, 'f');

        // Перевод в заданную систему
        /*
        int newBase = expectedNumberSystem.getNumberSystemIntValue();
        String digit = "";
        do {
            int tmp = (int) (value10 % newBase);
            digit = digitMap.get(tmp) + digit;
        }while ((value10 /= newBase) > 0);
        */
        StringBuilder digit = new StringBuilder();
        BigInteger newBase = new BigInteger(String.valueOf(expectedNumberSystem.getNumberSystemIntValue()));
        BigInteger[] tmp = {value10, null};
        BigInteger big0 = new BigInteger("0");
        do {
            tmp = tmp[0].divideAndRemainder(newBase);
            digit = digit.insert(0, digitMap.get(tmp[1].intValue()));
        }while (tmp[0].compareTo(big0) > 0);

        return new Number(expectedNumberSystem, digit.toString());
    }

    /*
    private static long pow(int base, int exponent) {
        if (exponent == 0) return 1;

        long result = 1;
        for (int i = 1; i <= exponent; i++) {
            result *= base;
        }
        return result;
    }
    */
}
