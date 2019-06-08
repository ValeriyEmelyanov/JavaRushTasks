package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = reader.readLine();
        String filename2 = reader.readLine();
        String filename3 = reader.readLine();
        reader.close();

//        File file = new File(filename1);
//        long length1 = file.length();
//        long length2 = length1 / 2 + length1 % 2;

//        FileInputStream in = new FileInputStream(filename1);
//        FileOutputStream out1 = new FileOutputStream(filename2);
//        for (long i = 0; i < length2; i++) {
//            out1.write(in.read());
//        }
//        out1.close();

//        FileOutputStream out2 = new FileOutputStream(filename3);
//        for (long i = length2; i < length1; i++) {
//            out2.write(in.read());
//        }
//        in.close();
//        out2.close();

        FileInputStream file1 = new FileInputStream(filename1);
        byte[] buf = new byte[file1.available()];
        int len = file1.read(buf);
        int len2 = len / 2 + len % 2;
        int len3 = len - len2;
        file1.close();

        FileOutputStream file2 = new FileOutputStream(filename2);
        file2.write(buf, 0, len2);
//        file2.write(buf, 0, (len % 2 == 0) ? len / 2 : len / 2 + 1);
        file2.close();
        FileOutputStream file3 = new FileOutputStream(filename3);
        file3.write(buf, len2, len3);
//        file3.write(buf, (len % 2 == 0) ? len / 2 : len / 2 + 1, len / 2);
        file3.close();
    }
}
