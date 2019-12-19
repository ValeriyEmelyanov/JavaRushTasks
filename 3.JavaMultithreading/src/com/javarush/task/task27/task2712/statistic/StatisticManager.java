package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager {
    private StatisticStorage statisticStorage = new StatisticStorage();

    private static StatisticManager instance = new StatisticManager();

    private StatisticManager() {
    }

    public static StatisticManager getInstance() {
        return instance;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    /**
     * Достает из хранилища все данные, относящиеся к отображению рекламы,
     * и считает общую прибыль за каждый день
     */
    public Map<Date, Long> getSelectedVideoData() {
        Map<Date, Long> map = new HashMap<>();
        List<EventDataRow> list = statisticStorage.get(EventType.SELECTED_VIDEOS);

        for (EventDataRow eventDataRow : list) {
            VideoSelectedEventDataRow dataRow = (VideoSelectedEventDataRow) eventDataRow;
            Date date = getStartOfDay(dataRow.getDate());
            map.put(date, map.getOrDefault(date, 0L) + dataRow.getAmount());
        }

        return map;
    }

    /**
     * Возвращает рабочее время повара, сгруппировать по дням
     */
    public Map<Date, Map<String, Integer>> getCookTimeWork() {
        Map<Date, Map<String, Integer>> map = new HashMap<>();
        List<EventDataRow> list = statisticStorage.get(EventType.COOKED_ORDER);

        for (EventDataRow eventDataRow : list) {
            CookedOrderEventDataRow dataRow = (CookedOrderEventDataRow) eventDataRow;
            Date date = getStartOfDay(dataRow.getDate());
            String cookName = dataRow.getCookName();
            Map<String, Integer> dayMap = null;
            if (map.containsKey(date)) {
                dayMap = map.get(date);
                dayMap.put(cookName, dayMap.getOrDefault(cookName, 0) + dataRow.getTime());
            } else {
                dayMap = new TreeMap<>();
                dayMap.put(cookName, dataRow.getTime());
            }
            map.put(date, dayMap);
        }

        return map;
    }

    private Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
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

        private List<EventDataRow> get(EventType type){
            return storage.get(type);
        }
    }
}
