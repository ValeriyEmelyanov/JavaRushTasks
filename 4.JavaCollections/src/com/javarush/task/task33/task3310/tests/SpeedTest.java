package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date start = new Date();

        for (String s : strings) {
            ids.add(shortener.getId(s));
        }

        return (new Date()).getTime() - start.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date start = new Date();

        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }


        return (new Date()).getTime() - start.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> ids1 = new HashSet<>();
        Set<Long> ids2 = new HashSet<>();

        long getTimeIds1 = getTimeToGetIds(shortener1, origStrings, ids1);
        long getTimeIds2 = getTimeToGetIds(shortener2, origStrings, ids2);

        Assert.assertTrue(getTimeIds1 > getTimeIds2);

        Set<String> newStrings1 = new HashSet<>();
        Set<String> newStrings2 = new HashSet<>();

        long getTimeStrings1 = getTimeToGetStrings(shortener1, ids1, newStrings1);
        long getTimeStrings2 = getTimeToGetStrings(shortener2, ids2, newStrings2);

        Assert.assertEquals(getTimeStrings1, getTimeStrings2, 30);
    }
}
