package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("В параметры должно быть передано 2 имени файлов.");
            return;
        }

        FileReader in = new FileReader(args[0]);
        char[] cbuf = new char[64];
        int len;
        StringBuilder stringBuilder = new StringBuilder();
        while (in.ready()) {
            len = in.read(cbuf);
            stringBuilder.append(cbuf, 0, len);
        }
        in.close();

        FileWriter out = new FileWriter(args[1]);
        //Pattern pattern = Pattern.compile("[a-zA-Zа-яА-Я]+\\d+[a-zA-Zа-яА-Я0-9]*|[a-zA-Zа-яА-Я0-9]*\\d+[a-zA-Zа-яА-Я]+");
        Pattern pattern = Pattern.compile("\\S+\\d+\\S*|\\S*\\d+\\S+");
        Matcher matcher = pattern.matcher(stringBuilder);
        while (matcher.find()) {
            out.write(matcher.group() + " ");
        }
        out.close();
    }
}
