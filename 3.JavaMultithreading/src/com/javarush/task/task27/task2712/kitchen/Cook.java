package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order) arg;
        int cookingTimeMin = order.getTotalCookingTime();

        // Регистрация события
        StatisticManager.getInstance().register(
                new CookedOrderEventDataRow(o.toString(), name, cookingTimeMin * 60, order.getDishes())
        );

        ConsoleHelper.writeMessage(
                String.format("Start cooking - %s, cooking time %dmin",
                        order.toString(), cookingTimeMin)
        );

        setChanged();
        notifyObservers(arg);
    }
}
