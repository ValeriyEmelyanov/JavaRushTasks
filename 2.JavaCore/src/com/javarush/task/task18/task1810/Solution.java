package com.javarush.task.task18.task1810;

/* 
DownloadException
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws DownloadException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename;
        FileInputStream in = null;
        try {
            while (true) {
                filename = reader.readLine();
                in = new FileInputStream(filename);
                if (in.available() < 1000) {
                    throw new DownloadException();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class DownloadException extends Exception {

    }
}
