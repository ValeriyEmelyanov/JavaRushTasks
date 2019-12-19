package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {

        Cook cook1 = new Cook("Amigo");
        cook1.setQueue(orderQueue);
        Cook cook2 = new Cook("Diego");
        cook2.setQueue(orderQueue);

        StatisticManager statisticManager = StatisticManager.getInstance();

        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);

        int tabletCount = 5;
        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < tabletCount; i++) {
            Tablet tablet = new Tablet(i + 1);
            tablet.setQueue(orderQueue);
            tablets.add(tablet);
        }

        Thread thread1 = new Thread(cook1);
        thread1.start();
        Thread thread2 = new Thread(cook2);
        thread2.start();

        Runnable generator = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread randThread = new Thread(generator);
        randThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        randThread.interrupt();

        try {
            randThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.interrupt();
        thread2.interrupt();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
