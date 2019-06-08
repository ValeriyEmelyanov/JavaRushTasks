package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        int port = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ConsoleHelper.writeMessage("Cервер запущен.");
            while (true) {
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            SocketAddress socketAddress = socket.getRemoteSocketAddress();
            ConsoleHelper.writeMessage(String.format(
                    "Установлено новое соединение с удаленным адресом %s", socketAddress));
            String userName = null;
            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage(String.format(
                        "Произошла ошибка при обмене данными с удаленным адресом %s", socketAddress));
            } finally {
                if (userName != null) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
                ConsoleHelper.writeMessage(String.format(
                        "Cоединение с удаленным адресом %s закрыто", socketAddress));
            }
        }

        /**
         * Реализует рукопожатие с клиентом
         * @return Имя нового клиента
         */
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message answer = null;
            String userName;
            do {
                do {
                    connection.send(new Message(MessageType.NAME_REQUEST));
                    answer = connection.receive();
                //} while (answer == null || answer.getType() != MessageType.USER_NAME);
                } while (answer.getType() != MessageType.USER_NAME);
                userName = answer.getData();
            } while (userName == null || userName.isEmpty() || connectionMap.containsKey(userName));
            //} while (userName.isEmpty() || connectionMap.containsKey(userName));

            connectionMap.put(userName, connection);
            connection.send(new Message(MessageType.NAME_ACCEPTED));

            return userName;
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> connectionEntry : connectionMap.entrySet()) {
                String name = connectionEntry.getKey();
                if (userName.equals(name)) {
                    continue;
                }
                connection.send(new Message(MessageType.USER_ADDED, name));
            }
        }

        /**
         * Запускает цикл обработки сообщений
         */
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while (true) {
                Message answer = connection.receive();
                if (answer != null && answer.getType() == MessageType.TEXT) {
                    String text = String.format("%s: %s", userName, answer.getData());
                    sendBroadcastMessage(new Message(MessageType.TEXT, text));
                } else {
                    ConsoleHelper.writeMessage("Ошибочное сообщение!");
                }
            }
        }
    }

    /**
     * Отправялет всем пользователям сообщение
     */
    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> connectionEntry : connectionMap.entrySet()) {
            try {
                connectionEntry.getValue().send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage(
                        String.format("Не удалось отправить сообщение клиенту %s.", connectionEntry.getKey()));
            }
        }

    }
}
