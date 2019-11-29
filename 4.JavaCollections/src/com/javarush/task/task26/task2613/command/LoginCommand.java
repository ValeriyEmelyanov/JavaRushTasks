package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(
            CashMachine.class.getPackage().getName() + ".resources.verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String reqNumber = ConsoleHelper.readString();
            String reqPin = ConsoleHelper.readString();
            if (reqNumber == null || !reqNumber.matches("\\d{12}")
                    || reqPin == null || !reqPin.matches("\\d{4}")) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }

            if (validCreditCards.containsKey(reqNumber)) {
                String pin = validCreditCards.getString(reqNumber);
                if (pin.equals(reqPin)) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), reqNumber));
                    return;
                }
            }
            ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), reqNumber));

            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
        }
    }
}
