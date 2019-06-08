package com.javarush.task.task05.task0517;

/* 
Конструируем котиков
*/

public class Cat {
    private  String name;
    private int age;
    private int weight;
    private String address;
    private String color;

    public Cat(String name) {
        this.name = name;
        this.age = getDefaultAge();
        this.weight = getDefaultWeight();
        this.color = getDefaultColor();
    }

    public Cat(String name, int weight, int age) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = getDefaultColor();
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
        this.weight = getDefaultWeight();
        this.color = getDefaultColor();
    }

    public Cat(int weight, String color) {
        this.age = getDefaultAge();
        this.weight = weight;
        this.color = color;
    }

    public Cat(int weight, String color, String address) {
        this.age = getDefaultAge();
        this.weight = weight;
        this.color = color;
        this.address = address;
    }

    public int getDefaultAge() {
        return 5;
    }

    public int getDefaultWeight() {
        return 5;
    }

    public String getDefaultColor() {
        return "Gray";
    }

    public static void main(String[] args) {

    }
}
