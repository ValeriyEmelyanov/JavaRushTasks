package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        Random rand = new Random();
        int dishesCount = rand.nextInt(Dish.values().length);
        dishes = new ArrayList<>();
        for (int i = 0; i < dishesCount ; i++) {
            int index = rand.nextInt(Dish.values().length);
            dishes.add(Dish.values()[index]);
        }
    }
}
