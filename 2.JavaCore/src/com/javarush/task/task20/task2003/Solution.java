package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        //String fileName = "D:\\Валера\\test8.txt";

        FileInputStream in = new FileInputStream(fileName);
        load(in);
        in.close();
    }

    public void save(OutputStream outputStream) throws IOException {
        //implement this method - реализуйте этот метод
        /*
        PrintStream printStream = new PrintStream(outputStream);
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            printStream.println(String.format("%s:%s",
                    entry.getKey().replace(" ", "\\ "),
                    entry.getValue().replace("\\", "\\\\")
            ));
        }
        */
        Properties props = new Properties();
        //for (Map.Entry<String,String> entry : properties.entrySet()) {
        //    props.put(entry.getKey(), entry.getValue());
        //}
        props.putAll(properties);
        props.store(outputStream, null);
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        /*
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty() || line.startsWith("!") || line.startsWith("#")) {
                continue;
            }
            String[] pair = line.split("\\s*[=:]\\s*");
            properties.put(
                    pair[0].replace("\\ ", " "),
                    pair[1].replace("\\\\", "\\")
            );
        }
        */
        Properties props = new Properties();
        props.load(inputStream);
        //Enumeration<?> enumeration = props.propertyNames();
        //while (enumeration.hasMoreElements()) {
        //    String key = (String) enumeration.nextElement();
        //    String value = props.getProperty(key);
        //    properties.put(key, value);
        //}
        properties.putAll((Map) props);
    }

    public static void main(String[] args) throws Exception {
        /*
        int sw = 2;

        if (sw == 1) {
            properties.put("first\\ name", "Ivan");
            properties.put("last name", "Ivanov");
            properties.put("email", "some@somethind.com");
            properties.put("phone", "22-33-44");


            FileOutputStream outputStream = new FileOutputStream("D:\\Валера\\test8.txt");
            new Solution().save(outputStream);
            outputStream.close();
        }

        if (sw == 2) {
            new Solution().fillInPropertiesMap();
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }
        */
    }
    
}
