package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream systemConcole = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        testString.printSomething();

        System.setOut(systemConcole);

        String result = outputStream.toString().replaceAll("\\r|\\n", "");
        //String result = outputStream.toString();
        String[] arr = result.replaceAll("\\s", "").split("\\W");
        if (arr.length < 2) {
            return;
        }
        int[] numbers = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            numbers[i] = Integer.parseInt(arr[i]);
        }
        String operation = "";
        Pattern pattern = Pattern.compile("[+]|[-]|[*]");
        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) {
            operation = matcher.group();
        }
        int operResult = 0;
        switch (operation) {
            case "+":
                operResult = numbers[0] + numbers[1];
                break;
            case "-":
                operResult = numbers[0] - numbers[1];
                break;
            case "*":
                operResult = numbers[0] * numbers[1];
                break;
        }

        System.out.println(result + operResult);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
            //System.out.print("3 + 6 = ");
        }
    }
}

