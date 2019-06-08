package com.javarush.task.task32.task3204;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {

        char[] pswChars = new char[8];

        // Чила от 0 до 9: 48 - 57
        // Положить в массиd число
        pswChars[0] = (char) getRndInRange(48, 57);

        // Латинские буквы верхнего регистра от A до Z: 65 - 90
        // Положить в массив заглавную букву латинского алфавита
        pswChars[1] = (char) getRndInRange(65, 90);

        // Латинские буквы нижнего регистра от a до z: 97 - 122
        // Положить в массив строчную букву латинского алфавита
        pswChars[2] = (char) getRndInRange(97, 122);

        // заполнить остальные элементы массива
        int var;
        for (int i = 3; i < pswChars.length; i++) {
            // Что будем ложить (0 -  число, 1 - заглавую букву, 2 - строчную);
            var = getRndInRange(2);
            switch (var) {
                case 0:
                    pswChars[i] = (char) getRndInRange(48, 57);
                    break;
                case 1:
                    pswChars[i] = (char) getRndInRange(65, 90);
                    break;
                case 2:
                    pswChars[i] = (char) getRndInRange(97, 122);
                    break;
            }
        }

        // Перемешать массив
        mixArray(pswChars);

        // в ByteArrayOutputStream
        String password = new String(pswChars);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            baos.write(password.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return baos;
    }

    public static int getRndInRange(int min, int max) {
        max = max - min + 1;
        return (int) (Math.random() * max) + min;
    }

    public static int getRndInRange(int max) {
        return getRndInRange(0, max);
    }

    public static void mixArray(char[] chars) {
        int limit = chars.length - 1;
        int rndIndex;
        char temp;
        for (int i = 0; i < chars.length; i++) {
            rndIndex = getRndInRange(limit);
            if (rndIndex == i) {
                continue;
            }

            temp = chars[i];
            chars[i] = chars[rndIndex];
            chars[rndIndex] = temp;
        }
    }
}