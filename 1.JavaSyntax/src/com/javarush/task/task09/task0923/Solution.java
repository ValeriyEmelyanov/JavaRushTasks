package com.javarush.task.task09.task0923;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Гласные и согласные
*/

public class Solution {
    public static char[] vowels = new char[]{'а', 'я', 'у', 'ю', 'и', 'ы', 'э', 'е', 'о', 'ё'};

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();

        ArrayList<Character> vowelsArr = new ArrayList<Character>();
        ArrayList<Character> consonantArr = new ArrayList<Character>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (isVowel(c))
                vowelsArr.add(c);
            else if (Character.isSpaceChar(c)) {
                continue;
            } else {
                consonantArr.add(c);
            }

        }

        for (char c : vowelsArr) {
            System.out.print(c + " ");
        }
        System.out.println();
        for (char c : consonantArr) {
            System.out.print(c + " ");
        }
    }

    // метод проверяет, гласная ли буква
    public static boolean isVowel(char c) {
        c = Character.toLowerCase(c);  // приводим символ в нижний регистр - от заглавных к строчным буквам

        for (char d : vowels)   // ищем среди массива гласных
        {
            if (c == d)
                return true;
        }
        return false;
    }
}