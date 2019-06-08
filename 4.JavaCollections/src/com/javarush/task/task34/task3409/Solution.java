package com.javarush.task.task34.task3409;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/*
Настраиваем логгер
*/
public class Solution {
    public static void main(String args[]) throws IOException {
        String logProperties = "4.JavaCollections/src/" + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/log4j.properties";
        Path path = Paths.get(logProperties).toAbsolutePath();

        List<String> lines = new ArrayList<>();
        Map<String, String> map = new TreeMap<>();

        try (InputStream is = new FileInputStream(path.toFile())) {
            Properties properties = new Properties();
            properties.load(is);

            for (String name : properties.stringPropertyNames()) {
                map.put(name, properties.getProperty(name));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        map.put("log4j.appender.file.threshold", "WARN");
        map.put("log4j.appender.file", "org.apache.log4j.RollingFileAppender");
        map.put("log4j.appender.file.MaxFileSize", "5MB");
        map.put("log4j.appender.file.File", "D:\\\\log\\\\runApp.log");
        map.put("log4j.appender.file.MaxBackupIndex", "6");

        map.put("log4j.appender.stdout.threshold", "ERROR");

        map.put("log4j.rootLogger", "WARN, file, stdout");

        try (InputStream is = new FileInputStream(path.toFile());
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches(".+=.+")) {
                    String[] pair = line.split("=");
                    if (map.containsKey(pair[0])) {
                        line = String.format("%s=%s", pair[0], map.get(pair[0]));
                    }
                }
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try (OutputStream os = new FileOutputStream(path.toFile());
            PrintWriter writer = new PrintWriter(os)) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
