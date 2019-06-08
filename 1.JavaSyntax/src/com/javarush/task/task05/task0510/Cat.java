package com.javarush.task.task05.task0510;

/* 
Кошкоинициация
*/

public class Cat {
    private String name;
    private int age;
    private int weight;
    private String address;
    private String color;

    public void initialize(String name) {
        this.name = name;
        this.age = getDefaultAge();
        this.weight = getDefaultWeight();
        this.color = getDefaultColor();
    }

    public void initialize(String name, int weight, int age) {
        this.name = name;
        this.weight = weight;
        this.age = age;
        this.color = getDefaultColor();
    }

    public void initialize(String name, int age) {
        this.name = name;
        this.weight = getDefaultWeight();
        this.age = age;
        this.color = getDefaultColor();
    }

    public void initialize(int weight, String color) {
        this.age = getDefaultAge();
        this.weight = weight;
        this.color = color;
    }

    public void initialize(int weight, String color, String address) {
        this.age = getDefaultAge();
        this.weight = weight;
        this.color = color;
        this.address = address;
    }

    public static int getDefaultAge() {
        return 5;
    }

    public static int getDefaultWeight() {
        return  5;
    }

    public static String getDefaultColor() {
        return "Gray";
    }

    public static void main(String[] args) {

    }
}
