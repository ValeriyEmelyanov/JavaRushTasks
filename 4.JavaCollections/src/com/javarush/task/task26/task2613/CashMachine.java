package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

public class CashMachine {
    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName() + ".resources.";

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);

        try {
            CommandExecutor.execute(Operation.LOGIN);

            while (true) {
                Operation operation = ConsoleHelper.askOperation();

                CommandExecutor.execute(operation);

                if (operation == Operation.EXIT) {
                    break;
                }
            }
        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
        }
    }
}
