package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    private static int[] enkey = {5, 7, 1, 0, 9};
    private static int ENKEY_LEN = 5;

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Необходимо передать 3 параметра в формате:");
            System.out.println("\t-e fileName fileOutputName");
            System.out.println("или");
            System.out.println("\t-d fileName fileOutputName");
            return;
        }

        String key = args[0];
        String fileName = args[1];
        String fileOutputName = args[2];

        switch (key) {
            case "-e":
                // -e D:\Валера\test1.txt D:\Валера\test1s.txt
                encodeFile(fileName, fileOutputName, true);
                break;
            case "-d":
                // -d D:\Валера\test1s.txt D:\Валера\test1se.txt
                encodeFile(fileName, fileOutputName, false);
                break;
            default:
                System.out.println("Неверный ключ");
                break;
        }
    }

    private static void encodeFile(String fileName, String fileOutputName, boolean isEncoding) throws IOException {
        FileInputStream in = new FileInputStream(fileName);
        FileOutputStream out = new FileOutputStream(fileOutputName);
        byte[] buf = new byte[ENKEY_LEN];
        int len;
        while (in.available() > 0) {
            len = in.read(buf);
            if (isEncoding) {
                encodeBuffer(buf, len);
            } else {
                decodeBuffer(buf, len);
            }
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    private static void encodeBuffer(byte[] buf, int len) {
        int tmp;
        for (int i = 0; i < len; i++) {
            tmp = buf[i] + enkey[i];
            if (tmp > Byte.MAX_VALUE) {
                tmp -= 256;
            }
            buf[i] = (byte) tmp;
        }
    }

    private static void decodeBuffer(byte[] buf, int len) {
        int tmp;
        for (int i = 0; i < len; i++) {
            tmp = buf[i] - enkey[i];
            if (tmp < Byte.MIN_VALUE) {
                tmp += 256;
            }
            buf[i] = (byte) tmp;
        }
    }

}
