package com.javarush.task.task19.task1911;

/* 
Ридер обертка
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        // Запомним системный вывод
        PrintStream systemOut = System.out;

        // Подмена вывода
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOut);
        System.setOut(printStream);

        // Вывод
        testString.printSomething();

        // Преобразование
        String result = byteArrayOut.toString().toUpperCase();

        // Восстановим системный вывод
        System.setOut(systemOut);

        // Вывод результата в консоль
        System.out.println(result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
