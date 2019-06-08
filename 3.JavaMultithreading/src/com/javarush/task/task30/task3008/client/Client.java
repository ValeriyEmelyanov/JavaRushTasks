package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Вводите адреса сервера:");
        String serverAdress = ConsoleHelper.readString();
        return serverAdress;
    }

    protected int getServerPort() {
        ConsoleHelper.writeMessage("Введите порт сервера:");
        int serverPort = ConsoleHelper.readInt();
        return serverPort;
    }

    protected String getUserName() {
        ConsoleHelper.writeMessage("Введите имя пользователя:");
        String userName = ConsoleHelper.readString();
        return userName;
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Ошибка отправки сообщения.");
            clientConnected = false;
        }
    }

    /**
     * Что делает метод Client.run():
     * 1. Создает и запускает поток socketThread для принятия сообщений.
     * socketThread устанавливает общее с основным потоком соединение с сервером.
     * setDaemon(true) чтобы поток не завершался.
     * 2. Основной поток ждет wait() пока socketThread установит соединение с сервером и пошлет оповещение.
     * 3. Если соединение установлено if (clientConnected) - идет дальше.
     * 4. Запускаем цикл while (clientConnected) по отправке сообщений с клиента.
     */
    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();

        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Произошла ошибкаво время работы клиента!");
                clientConnected = false;
                return;
            }
        }

        if (clientConnected) {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
        } else {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
            return;
        }

        String text;
        while (clientConnected) {
            text = ConsoleHelper.readString();
            if ("exit".equals(text)) {
                break;
            }
            if (shouldSendTextFromConsole()) {
                sendTextMessage(text);
            }
        }
    }

    /**
     * Внутренний класс, который отвечает за поток,
     * устанавливающий соединение с сервером и читающий сообщения сервера.
     */
    public class SocketThread extends Thread {
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage(String.format("Пользователь %s присоединился к чату.", userName));
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage(String.format("Пользователь с %s покинул чат.", userName));
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        /**
         * Реализует рукопожатие с сервером
         */
        protected void clientHandshake() throws IOException, ClassNotFoundException {
            Message message;
            do {
                message = Client.this.connection.receive();
                if (message.getType() == MessageType.NAME_REQUEST) {
                    Client.this.connection.send(new Message(MessageType.USER_NAME, getUserName()));
                } else if (message.getType() == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            } while (message.getType() == MessageType.NAME_REQUEST);
        }

        /**
         * Реализует главный цикл обработки сообщений сервера
         */
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            MessageType messageType;
            do {
                Message message = Client.this.connection.receive();
                messageType = message.getType();
                if (messageType == MessageType.TEXT) {
                    processIncomingMessage(message.getData());
                } else if (messageType == MessageType.USER_ADDED) {
                    informAboutAddingNewUser(message.getData());
                } else if (messageType == MessageType.USER_REMOVED) {
                    informAboutDeletingNewUser(message.getData());
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            } while (messageType == MessageType.TEXT ||
                    messageType == MessageType.USER_ADDED ||
                    messageType == MessageType.USER_REMOVED);
        }

        @Override
        public void run() {
            String serverAdress = getServerAddress();
            int port = getServerPort();
            try {
                Socket socket = new Socket(serverAdress, port);
                Client.this.connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException | ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
