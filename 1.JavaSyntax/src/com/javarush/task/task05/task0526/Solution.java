package com.javarush.task.task05.task0526;

/* 
Мужчина и женщина
*/

public class Solution {
    public static void main(String[] args) {
        Man man1 = new Man("Иван", 21, "Краснодар, ул. Красная, д.1");
        Man man2 = new Man("Всеволод", 41, "Йошкар-Ола, ул. Йывана Кырля, д.18, кв. 5");
        System.out.println(man1.toString());
        System.out.println(man2.toString());
        Woman woman1 = new Woman("Инна", 23, "Калиниград, ул. Школьная, д.20, кв.66");
        Woman woman2 = new Woman("Жанна", 24, "Можайск, ул. Фестивальная, д.3");
        System.out.println(woman1.toString());
        System.out.println(woman2.toString());
    }

    public static class Human {
        protected String name;
        protected int age;
        protected String address;

        public Human(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        @Override
        public String toString() {
            return name + " " + age + " " + address;
        }
    }

    public static class Man extends Human {
        public Man(String name, int age, String address) {
            super(name, age, address);
        }
    }

    public static class Woman extends Human {
        public Woman(String name, int age, String address) {
            super(name, age, address);
        }
    }

}
