package com.javarush.task.task19.task1902;

/* 
Адаптер
*/

import java.io.FileOutputStream;
import java.io.IOException;

public class AdapterFileOutputStream implements AmigoStringWriter {
    private FileOutputStream fileOutputStream;

    public AdapterFileOutputStream(FileOutputStream fileOutputStream) {
        this.fileOutputStream = fileOutputStream;
    }

    @Override
    public void flush() throws IOException {
        fileOutputStream.flush();
    }

    @Override
    public void writeString(String str) throws IOException {
        fileOutputStream.write(str.getBytes());
    }

    @Override
    public void close() throws IOException {
        fileOutputStream.close();
    }

    public static void main(String[] args) throws IOException {
        //migoStringWriter writer = new AdapterFileOutputStream(new FileOutputStream("D:\\Валера\\test9.txt"));
        //writer.writeString("That's very good!");
        //writer.flush();
        //writer.close();
    }


}

