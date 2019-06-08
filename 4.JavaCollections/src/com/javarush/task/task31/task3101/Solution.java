package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {
        // D:\Валера\ЯЯ D:\Валера\test_result.txt
        String path = args[0];
        String resultFileAbsolutePath = args[1];

        // Получить список файлов
        List<File> list = new ArrayList<>();
        File folder = new File(path);
        if (folder.exists() && folder.isDirectory()) {
            getListFiles(folder, list);
        }

        // Отсортировать по имени
        Collections.sort(list, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        // Переименовать
        File resultFile = new File(resultFileAbsolutePath);
        if (FileUtils.isExist(resultFile)) {
            FileUtils.renameFile(resultFile, new File(resultFile.getParent() + "/" + "allFilesContent.txt"));
        } else {
            resultFile = new File(resultFile.getParent() + "/" + "allFilesContent.txt");
        }

        // Записать файлы в результирующий файл
        try (FileOutputStream fos = new FileOutputStream(resultFile)) {
            for (File file : list) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    byte[] buf = new byte[1024];
                    int len = 0;
                    while ((len = fis.read(buf)) > 0) {
                        fos.write(buf, 0, len);
                    }
                } catch (IOException e) {
                    //e.printStackTrace();
                }
                fos.write("\n".getBytes());
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }

    }

    private static void getListFiles(File folder, List<File> list) {
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                if (file.length() <= 50) {
                    list.add(file);
                }
            } else if (file.isDirectory()) {
                getListFiles(file, list);
            }
        }
    }
}
