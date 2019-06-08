package com.javarush.task.task31.task3105;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get(args[0]);
        Path zipPath = Paths.get(args[1]);
        Map<String, byte[]> zipMap = new HashMap<>();

        // Периписать содержимое архива в временную мапу
        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipPath))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = zipInputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, len);
                }
                zipMap.put(zipEntry.getName(), outputStream.toByteArray());
                outputStream.close();

                zipEntry = zipInputStream.getNextEntry();
            }
        } catch (IOException e) {
            //e.printStackTrace();
            throw new IOException(e);
        }

        // Записать в архив
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipPath))) {
            // Дописать новый файл
            String newFileName = Paths.get("new", filePath.getFileName().toString()).toString();
            ZipEntry zipEntry = new ZipEntry(newFileName);
            zipOutputStream.putNextEntry(zipEntry);
            Files.copy(filePath, zipOutputStream);
            zipOutputStream.closeEntry();

            // Старое за минусом
            for (Map.Entry<String, byte[]> entry : zipMap.entrySet()) {
                if (entry.getKey().equals(newFileName)) {
                    continue;
                }
                zipOutputStream.putNextEntry(new ZipEntry(entry.getKey()));
                zipOutputStream.write(entry.getValue());
                zipOutputStream.closeEntry();
            }
        } catch (IOException e) {
            //e.printStackTrace();
            throw new IOException(e);
        }
    }
}
