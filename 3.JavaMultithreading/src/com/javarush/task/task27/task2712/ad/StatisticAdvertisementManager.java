package com.javarush.task.task27.task2712.ad;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class StatisticAdvertisementManager {
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private static StatisticAdvertisementManager instance = new StatisticAdvertisementManager();

    public static StatisticAdvertisementManager getInstance() {
        return instance;
    }

    private StatisticAdvertisementManager() {
    }

    /**
     * Возвращает список активных (показов > 0) видео или архивных (показов == 0) видео
     * @param active если true, товозвращается список активных видео, в противном случае ахивных
     * @return карта видео и количество показов
     */
    public Map<String, Integer> getVideos(boolean active) {
        Map<String, Integer> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        for (Advertisement adv : advertisementStorage.list()) {
            if ((active && adv.getHits() > 0) || (!active && adv.getHits() == 0)) {
                map.put(adv.getName(), adv.getHits());
            }
        }

        return map;
    }
}
