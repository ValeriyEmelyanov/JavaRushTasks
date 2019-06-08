package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;

import java.util.*;

public class StatisticManager {
//    private static volatile StatisticManager instance;
    private static StatisticManager instance = new StatisticManager();

    private StatisticManager() {
    }

    public static StatisticManager getInstance() {
        return instance;
    }

//    public static StatisticManager getInstance() {
//        StatisticManager localInstance = instance;
//        if (localInstance == null) {
//            synchronized (StatisticManager.class) {
//                localInstance = instance;
//                if (localInstance == null) {
//                    instance = localInstance = new StatisticManager();
//                }
//            }
//        }
//        return localInstance;
//    }

    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    /**
     * Хранилище статистики событий
     */
    private static class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType eventType : EventType.values()) {
                storage.put(eventType, new ArrayList<EventDataRow>());
            }

        }

        private void put(EventDataRow data) {
            List<EventDataRow> list = storage.get(data.getType());
            list.add(data);
        }
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }
}
