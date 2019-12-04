package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        PrintWriter writer = null;
        BufferedReader bufferedReader = null;

        try {
            Socket client = new Socket(url.getHost(), 80);

            writer = new PrintWriter(client.getOutputStream());
            writer.println(String.format("GET %s HTTP/1.1", url.getPath()));
            writer.println(String.format("Host: %s", url.getHost()));
            writer.println("Connection: close");
            writer.println("");
            writer.flush();

            bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String responseLine;

            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ignored) {}
            }
        }
    }
}