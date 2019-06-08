package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.*;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = null;

        while (true) {
            // D:\Валера\test1.txt
            filename = reader.readLine();
            if (filename != null && filename.equals("exit")) {
                break;
            }

            Thread thread = new ReadThread(filename);
            thread.start();
        }

    }

    public static class ReadThread extends Thread {
        private String filename;

        public ReadThread(String fileName) {
            //implement constructor body
            this.filename = fileName;
        }

        // implement file reading here - реализуйте чтение из файла тут
        @Override
        public void run() {
            super.run();
            FileInputStream in = null;
//            byte data;
//            HashMap<Byte, Integer> map = new HashMap<Byte, Integer>();
            int data;
            int[] bytes = new int[256];
            try {
                in = new FileInputStream(filename);
                while (in.available() > 0) {
//                    data = (byte) in.read();
//                    if (map.containsKey(data)) {
//                        map.put(data, map.get(data) + 1);
//                    } else {
//                        map.put(data, 1);
//                    }
                    data = in.read();
                    bytes[data]++;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

//            if (map.size() < 1) {
//                return;
//            }
//
//            int max = Collections.max(map.values());
//            for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
//                if (entry.getValue() == max) {
//                    resultMap.put(filename, entry.getKey().intValue());
//                    //System.out.println(entry.getKey().toString() + " " + max);
//                    //System.out.println(filename + " " + entry.getKey().intValue());
//                }
//            }

            int max = 0;
            for (int i = 0; i < bytes.length - 1; i++) {
                if (bytes[i] > max) {
                    max = bytes[i];
                }
            }

            for (int i = 0; i < bytes.length - 1; i++) {
                if (bytes[i] == max) {
                    resultMap.put(filename, i);
                    //System.out.println(i + " " + max);
                    //System.out.println(filename + " " + i);
                }
            }
        }
    }
}
