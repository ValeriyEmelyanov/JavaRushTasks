package com.javarush.task.task18.task1814;

import java.io.FileInputStream;
import java.io.IOException;

/* 
UnsupportedFileName
*/

public class TxtInputStream extends FileInputStream {

    public TxtInputStream(String fileName) throws IOException, UnsupportedFileNameException {
        super(fileName);

        int len = fileName.length();
        if (len < 4 || !fileName.substring(len - 4).equals(".txt")) {
            super.close();
            throw new UnsupportedFileNameException();
        }
    }

    public static void main(String[] args) throws Exception {
//        TxtInputStream txtInputStream = new TxtInputStream("d://Валера//test1.txt");
//        txtInputStream.close();
    }
}

