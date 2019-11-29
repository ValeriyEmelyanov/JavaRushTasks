package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

        String currencyCode = ConsoleHelper.askCurrencyCode();

        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        String line;
        int amount;

        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            while (true) {
                line = ConsoleHelper.readString();
                if (line != null && line.matches("\\d+")) {
                    amount = Integer.valueOf(line);
                    if (manipulator.isAmountAvailable(amount)) {
                        break;
                    } else {
                        ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    }
                } else {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                }
            }

            try {
                Map<Integer, Integer> map = manipulator.withdrawAmount(amount);
                SortedMap<Integer, Integer> dscSorted = new TreeMap<>(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);
                    }
                });
                dscSorted.putAll(map);
                for (Map.Entry<Integer, Integer> entry : dscSorted.entrySet()) {
                    ConsoleHelper.writeMessage(String.format("\t%d - %d", entry.getKey(), entry.getValue()));
                }
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount, currencyCode));
                break;
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }
    }
}
