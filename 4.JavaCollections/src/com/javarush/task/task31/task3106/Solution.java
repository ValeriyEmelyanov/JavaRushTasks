package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            return;
        }

        // Взять входные параметры
        String resultFileName = args[0];
        List<Path> fileNameParts = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            fileNameParts.add(Paths.get(args[i]));
        }

        // Упорядочивание частей архива
        Collections.sort(fileNameParts, new Comparator<Path>() {
            @Override
            public int compare(Path path1, Path path2) {
                return path1.getFileName().toString().compareTo(
                        path2.getFileName().toString()
                );
            }
        });

        // Создать входные потоки
        Vector<InputStream> filesParts = new Vector<>();
        for (Path path : fileNameParts) {
            filesParts.add(Files.newInputStream(path));
        }

        // Соберем архив из кусочков
        try (SequenceInputStream sis = new SequenceInputStream(filesParts.elements());
             ZipInputStream zipInputStream = new ZipInputStream(sis);
             FileOutputStream fileOutputStream = new FileOutputStream(resultFileName);
             ) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            if (zipEntry != null) {
                //Files.copy(zipInputStream, Paths.get(resultFileName));
                byte[] buffer = new byte[1024 * 8];
                int len;
                while ((len = zipInputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, len);
                }
            }
            zipInputStream.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
