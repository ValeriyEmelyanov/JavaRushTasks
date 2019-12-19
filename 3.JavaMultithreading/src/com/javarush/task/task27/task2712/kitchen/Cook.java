package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private String name;
    private LinkedBlockingQueue<Order> queue;
    private volatile boolean stopped = false;
    private volatile boolean caught = false;

    public Cook(String name) {
        this.name = name;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order) {
        int cookingTimeMin = order.getTotalCookingTime();

        // Регистрация события
        StatisticManager.getInstance().register(
                new CookedOrderEventDataRow(
                        order.getTablet().toString(),
                        name,
                        cookingTimeMin * 60,
                        order.getDishes()));

        ConsoleHelper.writeMessage(
                String.format("Start cooking - %s, cooking time %d min",
                        order.toString(), cookingTimeMin));

        try {
            Thread.sleep(cookingTimeMin * 10);
        } catch (InterruptedException ignored) {
        }

        setChanged();
        notifyObservers(order);
    }

    @Override
    public void run() {
        while (!stopped) {
            try {
                startCookingOrder(queue.take());
            } catch (InterruptedException ignored) {
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
                caught = true;
            }
            if ((!Thread.currentThread().isInterrupted() || caught) && queue.isEmpty()) {
                stopped = true;
            }
        }
    }
}
