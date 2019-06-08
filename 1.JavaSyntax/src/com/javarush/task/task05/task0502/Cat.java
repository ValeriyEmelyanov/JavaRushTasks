package com.javarush.task.task05.task0502;

/* 
Реализовать метод fight
*/

public class Cat {
    public int age;
    public int weight;
    public int strength;

    public Cat() {
    }

    public boolean fight(Cat anotherCat) {
        if (strength > anotherCat.strength)
            return true;
        else if (strength < anotherCat.strength)
            return false;
        else if (age > 3 && age < 7 && (anotherCat.age < 3 || anotherCat.age > 7))
            return true;
        else if (age < 3 || age > 7 && (anotherCat.age > 3 && anotherCat.age < 7))
            return false;
        else if (weight > anotherCat.weight)
            return true;
        else if (weight < anotherCat.weight)
            return false;
        else
            return (hashCode() > anotherCat.hashCode());
    }

    public static void main(String[] args) {
//        Cat cat1 = new Cat();
//        cat1.strength = 10;
//        cat1.age = 5;
//        cat1.weight = 7;
//        Cat cat2 = new Cat();
//        cat2.strength = 10;
//        cat2.age = 2;
//        cat2.weight = 7;
//        System.out.println(cat1.fight(cat2));
//        System.out.println(cat2.fight(cat1));
    }
}
