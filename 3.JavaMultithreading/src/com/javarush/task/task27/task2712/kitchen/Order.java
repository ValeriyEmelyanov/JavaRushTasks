package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    @Override
    public String toString() {
        if (dishes.size() == 0) {
            return "";
        }

        return String.format("Your order: %s of %s",
                dishes, tablet);

    }

    /**
     * Время выполнения заказа в минутах
     */
    public int getTotalCookingTime() {
        int duration = 0;
        for (Dish dish : dishes) {
            duration += dish.getDuration();
        }
        return duration;
    }

    public boolean isEmpty() {
        return dishes.size() == 0;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    protected void initDishes() throws IOException {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public Tablet getTablet() {
        return tablet;
    }
}
