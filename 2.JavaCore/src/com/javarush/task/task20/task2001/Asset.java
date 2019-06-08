package com.javarush.task.task20.task2001;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Asset {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Asset asset = (Asset) o;

        if (Double.compare(asset.price, price) != 0) return false;
        return name != null ? name.equals(asset.name) : asset.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public void save(BufferedWriter writer) throws IOException {
        writer.write(String.format("%s:%.2f", name, price));
        writer.newLine();
        writer.flush();
    }

    public void load(BufferedReader reader) throws  IOException {
        String[] fields = reader.readLine().split(":");
        name = fields[0];
        price = Double.parseDouble(fields[1].replaceAll(",", "\\."));
    }
}
