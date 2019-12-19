package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    public void printAdvertisementProfit() {
        Map<Date, Long> sortedMap = new TreeMap<>(Collections.reverseOrder());
        sortedMap.putAll(StatisticManager.getInstance().getSelectedVideoData());
        SimpleDateFormat formater = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        long total = 0;
        for (Map.Entry<Date, Long> entry : sortedMap.entrySet()) {
            ConsoleHelper.writeMessage(
                    String.format(Locale.ENGLISH, "%s - %.2f", formater.format(entry.getKey()), entry.getValue() / 100.0));
            total += entry.getValue();
        }
        if (total > 0) {
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", total / 100.0));
        }
    }

    public void printCookWorkloading() {
        Map<Date, Map<String, Integer>> sortedMap = new TreeMap<>(Collections.reverseOrder());
        sortedMap.putAll(StatisticManager.getInstance().getCookTimeWork());
        SimpleDateFormat formater = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        for (Map.Entry<Date, Map<String, Integer>> dateMapEntry : sortedMap.entrySet()) {
            // Дата
            ConsoleHelper.writeMessage(formater.format(dateMapEntry.getKey()));
            // Статистика по поварам
            for (Map.Entry<String, Integer> entry : dateMapEntry.getValue().entrySet()) {
                ConsoleHelper.writeMessage(
                        String.format("%s - %d min", entry.getKey(), (int) Math.ceil(entry.getValue() / 60.0)));
            }
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet() {
        Map<String, Integer> sortedMap = StatisticAdvertisementManager.getInstance().getVideos(true);

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            ConsoleHelper.writeMessage(String.format("%s - %d", entry.getKey(), entry.getValue()));
        }
    }

    public void printArchivedVideoSet() {
        Map<String, Integer> sortedMap = StatisticAdvertisementManager.getInstance().getVideos(false);

        for (String s : sortedMap.keySet()) {
            ConsoleHelper.writeMessage(s);
        }

    }
}
