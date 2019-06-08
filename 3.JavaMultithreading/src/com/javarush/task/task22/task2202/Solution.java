package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) throws RuntimeException {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        System.out.println(getPartOfString("Амиго и Диего лучшие друзья!"));
        //System.out.println(getPartOfString("JavaRush - лучший"));
    }

    public static String getPartOfString(String string) throws RuntimeException {
        if (string == null || string.isEmpty()) {
            throw new TooShortStringException();
        }

        int firstSpace = 0;
        int currentSpace = 0;
        for (int i = 0; i <= 4; i++) {
            currentSpace = string.indexOf(' ', currentSpace) + 1;
            if (currentSpace == 0 && i < 4) {
                throw new TooShortStringException();
            }
            if (i == 0) {
                firstSpace = currentSpace;
            }
        }

        return (currentSpace > 0) ?
                string.substring(firstSpace, currentSpace).trim() :
                string.substring(firstSpace);
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
