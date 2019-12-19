package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Обрабатывает рекламное видео
 */
public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        if (storage.list().isEmpty()) {
            StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
            throw new NoVideoAvailableException();
        }

        // Подобрать подходящие ролики
        List<Advertisement> adList = storage.list().stream()
                .filter(adv -> adv.getDuration() <= timeSeconds && adv.getHits() > 0)
                .collect(Collectors.toList());

        if (adList.isEmpty()) {
            StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
            throw new NoVideoAvailableException();
        }

        // Отсортировать список роликов
        Collections.sort(adList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement ad1, Advertisement ad2) {
                // Первичная сортировка в порядке уменьшения стоимости показа одного рекламного ролика в копейках
                // (в обратном порядке)
                if (ad1.getAmountPerOneDisplaying() > ad2.getAmountPerOneDisplaying()) {
                    return -1;
                }
                if (ad1.getAmountPerOneDisplaying() < ad2.getAmountPerOneDisplaying()) {
                    return 1;
                }

                // Вторичная сортировка - по увеличению стоимости показа одной секунды рекламного ролика
                // в тысячных частях копейки
                return Long.compare(ad1.getAmountPerOneDisplaying() * 1000 / ad1.getDuration(),
                        ad2.getAmountPerOneDisplaying() * 1000 / ad2.getDuration());

                // Вариант решения
                /*
                if ((ad1.getAmountPerOneDisplaying() - ad2.getAmountPerOneDisplaying()) == 0) {
                    return (int) (ad1.getAmountPerOneDisplaying() * 1000 / ad1.getDuration()
                            - ad2.getAmountPerOneDisplaying() * 1000 / ad2.getDuration());
                } else {
                    return (int) (ad2.getAmountPerOneDisplaying() - ad1.getAmountPerOneDisplaying());
                }
                */
            }
        });

        // Получить список для показа
        AdOrderListGetter adListGetter = new AdOrderListGetter();
        adListGetter.findBestList(adList);
        List<Advertisement> newAdList = adListGetter.getBestList();

        if (newAdList == null || newAdList.isEmpty()) {
            StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
            throw new NoVideoAvailableException();
        }

        // Регистрация события
        //StatisticManager.getInstance().register(
        //        new VideoSelectedEventDataRow(newAdList, adListGetter.getBestAmount(),
        //                adListGetter.getBestTotalDuration())
        //);
        long amount = 0;
        int totalDuration = 0;
        for (Advertisement ad : newAdList) {
            amount += ad.getAmountPerOneDisplaying();
            totalDuration += ad.getDuration();
        }
        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(newAdList, amount, totalDuration));

        // Отобразить
        for (Advertisement ad : newAdList) {
            ConsoleHelper.writeMessage(
                    String.format("%s is displaying... %d, %d",
                            ad.getName(),
                            ad.getAmountPerOneDisplaying(),
                            (int) (((double) ad.getAmountPerOneDisplaying()) / ad.getDuration() * 1000)
                    )
            );
            ad.revalidate();
        }
    }

    private class AdOrderListGetter {
        private List<Advertisement> bestList = null;
        private long bestAmount = 0;
        private int bestTotalDuration = 0;

        public List<Advertisement> getBestList() {
            return bestList;
        }

        public long getBestAmount() {
            return bestAmount;
        }

        public int getBestTotalDuration() {
            return bestTotalDuration;
        }

        private void setBestList(List<Advertisement> adList) {
            // посчитать сумму денег претедента, продолжительность
            long amount = 0;
            int totalDuration = 0;
            for (Advertisement ad : adList) {
                amount += ad.getAmountPerOneDisplaying();
                totalDuration += ad.getDuration();
            }

            if (totalDuration > timeSeconds) {
                return;
            }

            if (bestList == null || isBetter(adList, amount, totalDuration)) {
                bestList = adList;
                bestAmount = amount;
                bestTotalDuration = totalDuration;
            }
        }

        private boolean isBetter(List<Advertisement> adList, long amount, int totalDuration) {
            if (amount != bestAmount) {
                return amount > bestAmount;
            }

            // amount = bestAmount

            if (totalDuration != bestTotalDuration) {
                return totalDuration > bestTotalDuration;
            }

            // totalDuration = bestTotalDuration

            return adList.size() < bestList.size();
        }

        public void findBestList(List<Advertisement> source) {
            if (source.size() == 0) {
                return;
            }

            setBestList(source);

            for (int i = 0; i < source.size(); i++) {
                List<Advertisement> adList = new ArrayList<>(source.size() - 1);
                for (int j = 0; j < source.size(); j++) {
                    if (i == j) continue;
                    adList.add(source.get(j));
                }

                findBestList(adList);
            }
        }
    }
}
