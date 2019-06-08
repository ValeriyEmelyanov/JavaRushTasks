package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        //Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(1) + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> result = new HashSet<>();

        // Привести путь к приемлемому виду
        String sep = System.getProperty("file.separator");
        String pathToClasses = pathToAnimals;
        if (!pathToClasses.endsWith(sep)) {
            pathToClasses = pathToClasses.concat(sep);
        }

        try {
            pathToClasses = URLDecoder.decode(pathToClasses, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return result;
        }

        // Наш специфический загрузчик классов
        PathClassLoader loader = new PathClassLoader(ClassLoader.getSystemClassLoader(), pathToClasses);

        // Получить список файлов и обработать
        File folder = new File(pathToClasses);

        for (String fileName : Objects.requireNonNull(folder.list())) {
            if (fileName == null || !fileName.endsWith(".class")) {
                continue;
            }

            try {
                String className = fileName.split(".class")[0];
                Class clazz = loader.loadClass(className);

                // Проверим, имплементирует класс интерфейс Animal
                if (!Animal.class.isAssignableFrom(clazz)) {
                    continue;
                }

                // Попытаемся получить публичный конструктор без параметров
                // в случае неудачи улетим в исключение NoSuchMethodException
                Constructor constructor = clazz.getConstructor();

                // Все удачно, добавляем объект в коллекцию

                Animal animal = (Animal) clazz.newInstance();
                result.add(animal);

            } catch (ClassNotFoundException
                    | NoSuchMethodException
                    | IllegalAccessException
                    | InstantiationException ignored) {
            }
        }

        return result;
    }
}
