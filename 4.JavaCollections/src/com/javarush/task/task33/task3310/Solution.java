package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        StorageStrategy storageStrategy = new HashMapStorageStrategy();
        testStrategy(storageStrategy, 1000);

        StorageStrategy storageStrategy2 = new OurHashMapStorageStrategy();
        testStrategy(storageStrategy2, 1000);

        StorageStrategy storageStrategy3 = new FileStorageStrategy();
        testStrategy(storageStrategy3, 1000);

        StorageStrategy storageStrategy4 = new OurHashBiMapStorageStrategy();
        testStrategy(storageStrategy4, 1000);

        StorageStrategy storageStrategy5 = new HashBiMapStorageStrategy();
        testStrategy(storageStrategy5, 1000);

        StorageStrategy storageStrategy6 = new DualHashBidiMapStorageStrategy();
        testStrategy(storageStrategy6, 1000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> result = new HashSet<>();

        for (String s : strings) {
            result.add(shortener.getId(s));
        }

        return result;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> result = new HashSet<>();

        for (Long key : keys) {
            result.add(shortener.getString(key));
        }

        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> strings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date start = new Date();
        Set<Long> ids = getIds(shortener, strings);
        long workTime1 = (new Date()).getTime() - start.getTime();
        Helper.printMessage(String.valueOf(workTime1));

        start = new Date();
        Set<String> retunedStrings = getStrings(shortener, ids);
        long workTime2 = (new Date()).getTime() - start.getTime();
        Helper.printMessage(String.valueOf(workTime2));

        if (strings.equals(retunedStrings)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}
