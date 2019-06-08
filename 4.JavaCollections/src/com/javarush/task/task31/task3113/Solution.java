package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        String pathString = null;
        Path path = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            pathString = reader.readLine();
            path = Paths.get(pathString);
        } catch (Exception e) {
            //e.printStackTrace();
        }

        if (!Files.isDirectory(path)) {
            System.out.printf("%s - не папка%n", pathString);
            return;
        }

        StatisticFileVisitor fileVisitor = new StatisticFileVisitor();
        Files.walkFileTree(path, fileVisitor);

        System.out.printf("Всего папок - %d%n", fileVisitor.getFoldersNumber());
        System.out.printf("Всего файлов - %d%n", fileVisitor.getFilesNumber());
        System.out.printf("Общий размер - %d%n", fileVisitor.getTotalSize());
    }
}
