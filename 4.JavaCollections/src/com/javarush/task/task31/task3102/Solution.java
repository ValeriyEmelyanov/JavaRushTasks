package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        Queue<File> queue = new PriorityQueue<>();

        File rootFolder = new File(root);
        List<String> list = new ArrayList<>();

        if (rootFolder.isDirectory()) {
            List<File> listFiles = Arrays.asList(rootFolder.listFiles());
            Iterator<File> iterator = listFiles.listIterator();

            while (true) {
                if (iterator.hasNext()) {

                    File file = iterator.next();
                    if (file.isFile()) {
                        list.add(file.getAbsolutePath());
                    } else if (file.isDirectory()) {
                        queue.offer(file);
                    }

                } else if (!queue.isEmpty()) {

                    File folder = queue.poll();
                    listFiles = Arrays.asList(folder.listFiles());
                    iterator = listFiles.listIterator();

                } else {
                    break;
                }
            }
        }

        return list;
    }

    public static void main(String[] args) {
//        String root = "D:\\Валера\\ЯЯ";
//        try {
//            List<String> list = getFileTree(root);
//            System.out.println(list);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
