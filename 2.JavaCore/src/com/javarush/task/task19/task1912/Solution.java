package com.javarush.task.task19.task1912;

/* 
Ридер обертка 2
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        // Запомнил
        PrintStream console = System.out;

        // Подменил
        ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutput);
        System.setOut(printStream);

        // Вывести
        testString.printSomething();

        // Обработать
        String result = byteArrayOutput.toString().replaceAll("te", "??");

        // Восстановил
        System.setOut(console);

        // Вывеси в консоль
        System.out.println(result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
