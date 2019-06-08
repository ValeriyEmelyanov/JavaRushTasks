package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BotClient extends Client {
    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return String.format("date_bot_%d", (int) (Math.random() * 100));
    }

    public class BotSocketThread extends Client.SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage(
                    "Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message == null || !message.contains(": ")) {
                return;
            }
            int index = message.indexOf(":");
            if (index < message.length() - 1) {
                String userName = message.substring(0, index).trim();
                String request = message.substring(index + 1).trim();
                Map<String, String> formats = new HashMap() {
                    {
                        put("дата", "d.MM.YYYY");
                        put("день", "d");
                        put("месяц", "MMMM");
                        put("год", "YYYY");
                        put("время", "H:mm:ss");
                        put("час", "H");
                        put("минуты", "m");
                        put("секунды", "s");
                    }
                };
                if (formats.containsKey(request)) {
                    sendTextMessage(String.format(
                            "Информация для %s: %s",
                            userName,
                            new SimpleDateFormat(formats.get(request)).format(Calendar.getInstance().getTime())
                    ));
                }
            }
        }
    }

    public static void main(String[] args) {
        new BotClient().run();
    }
}
