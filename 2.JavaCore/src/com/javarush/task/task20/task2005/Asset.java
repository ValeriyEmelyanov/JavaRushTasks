package com.javarush.task.task20.task2005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Asset {
    public Asset(String name) {
        this.name = name;
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

    public void save(PrintWriter printWriter) {
        printWriter.println(String.format("%s,%f", name, price));
        printWriter.flush();
    }

    public void load(BufferedReader reader) throws IOException {
        String[] fields = reader.readLine().split(",");
        name = fields[0];
        price = Double.parseDouble(fields[1]);
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
}
