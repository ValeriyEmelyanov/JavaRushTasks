package com.javarush.task.task20.task2006;

import java.io.Serializable;

public class Asset implements Serializable {
    public Asset(String name) {
        this.name = name;
    }

    public Asset(String name, double price) {
        this.name = name;
        this.price = price;
    }

    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ":" + price;
    }
}
