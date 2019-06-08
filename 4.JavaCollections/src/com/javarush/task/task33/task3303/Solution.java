package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //FileReader fileReader = new FileReader(fileName);
        //T object = mapper.readValue(fileReader, clazz);
        File file = new File(fileName);
        T object = mapper.readValue(file, clazz);
        return object;
//        return mapper.readValue(file, clazz);
    }

    public static void main(String[] args) {

    }
}
