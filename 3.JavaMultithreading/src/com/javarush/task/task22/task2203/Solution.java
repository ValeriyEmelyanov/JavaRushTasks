package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null || string.isEmpty()) {
            throw new TooShortStringException();
        }

        int firstTab = 0;
        int currentTab = 0;
        for (int i = 0; i < 2; i++) {
            currentTab = string.indexOf('\t', currentTab) + 1;
            if (currentTab == 0 && i < 2) {
                throw new TooShortStringException();
            }
            if (i == 0) {
                firstTab = currentTab;
            }
        }
        return string.substring(firstTab, currentTab - 1);
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
