package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println("Is one edit away - abc & ccc ? : " + isOneEditAway("abc", "ccc"));
        System.out.println("Is one edit away - abc & abcd ? : " + isOneEditAway("abc", "abcd"));
        System.out.println("Is one edit away - abce & abcd ? : " + isOneEditAway("abce", "abcd"));
        System.out.println("Is one edit away - wabcd & abcd ? : " + isOneEditAway("wabcd", "abcd"));
        System.out.println("Is one edit away - abrcd & abcd ? : " + isOneEditAway("abrcd", "abcd"));
        System.out.println("Is one edit away -   &   ? : " + isOneEditAway("", ""));
        System.out.println("Is one edit away - a &   ? : " + isOneEditAway("a", ""));
    }

    public static boolean isOneEditAway(String first, String second) {
        // Отсечем очевидные случаи
        if (first == null || second == null) {
            return false;
        }

        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        if (first.equals(second)) {
            return true;
        }

        if (first.isEmpty() || second.isEmpty()) {
            return true;
        }

        // Подготовим массивы символов
        char[] firstChars = first.toCharArray();
        char[] secondChars = second.toCharArray();

        // Если строки равны по длине
        if (firstChars.length == secondChars.length) {
            for (int i = 0; i < firstChars.length; i++) {
                if (firstChars[i] == secondChars[i]) {
                    continue;
                }
                firstChars[i] = secondChars[i];
                return String.valueOf(firstChars).equals(String.valueOf(secondChars));
            }
        }

        // Если длина строк разная
        char[] longChars;
        char[] shortChars;
        String longString;
        String shortString;
        int minLength = Math.min(firstChars.length, secondChars.length);
        if (firstChars.length > secondChars.length) {
            longChars = firstChars;
            shortChars = secondChars;
            longString = first;
            shortString = second;
        } else {
            shortChars = firstChars;
            longChars = secondChars;
            longString = second;
            shortString = first;
        }

        if ( longString.equals(shortString + longChars[minLength])) {
            return true;
        }

        String s = "";
        for (int i = 0; i < minLength; i++) {
            if (shortChars[i] == longChars[i]) {
                continue;
            }
            if (i == 0) {
                s = longChars[i] + String.valueOf(shortChars);
            } else {
                s = String.valueOf(shortChars, 0, i) + longChars[i]
                        + String.valueOf(shortChars, i, minLength - i);
            }
            return longString.equals(s);
        }

        return false;
    }
}
