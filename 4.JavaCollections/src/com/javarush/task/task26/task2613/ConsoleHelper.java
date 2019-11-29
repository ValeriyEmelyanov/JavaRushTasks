package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(
            CashMachine.class.getPackage().getName() + ".resources.common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String line = null;
        try {
            line = bis.readLine();
            if (line != null && line.toLowerCase().contains("exit")) {
                throw new InterruptOperationException();
            }
        } catch (IOException e) {
            return null;
        }
        return line;
    }

    // Считать код валюты
    public static String askCurrencyCode() throws InterruptOperationException {
        while (true){
            writeMessage(res.getString("choose.currency.code"));
            String currencyCode = readString();
            if (currencyCode == null || currencyCode.length() != 3 || !currencyCode.matches("[a-zA-Z]{3}")) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            return currencyCode.toUpperCase();
        }
    }

    // Считать номинал и количество банкнот
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String line;
        while (true) {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            line = readString();
            if (line == null || !line.matches("\\d+\\s\\d+")) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            return line.split("\\s");
        }
    }

    // Спросить у пользователя операцию
    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            ConsoleHelper.writeMessage(res.getString("choose.operation"));
            for (int i = 1; i < Operation.values().length; i++) {
                ConsoleHelper.writeMessage(String.format("%d - %s",
                        i,
                        res.getString("operation." + Operation.values()[i])));
            }

            String line = ConsoleHelper.readString();

            try {
                return Operation.getAllowableOperationByOrdinal(Integer.valueOf(line));
            } catch (Exception e) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
}
