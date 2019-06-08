package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.List;

@XmlRootElement
public class Shop {
    public Goods goods;
    public int count;
    public double profit;
    public String[] secretData;

    public static class Goods {
        public List<String> names;
    }

    @Override
    public String toString() {
        return "Goods: " + goods.names.toString() +
                ", count " + count +
                ", profit " + profit +
                ", secret data: " + Arrays.toString(secretData);
    }
}
