package com.javarush.task.task05.task0505;

/* 
Кошачья бойня
*/

public class Solution {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Barsik", 4, 4, 10);
        Cat cat2 = new Cat("Mursik", 5, 5, 10);
        Cat cat3 = new Cat("Vaska", 5, 5, 11);
        System.out.println(cat1.name + " vs " + cat2.name + ": win " + (cat1.fight(cat2) ? cat1.name : cat2.name));
        System.out.println(cat1.name + " vs " + cat3.name + ": win " + (cat1.fight(cat3) ? cat1.name : cat3.name));
        System.out.println(cat2.name + " vs " + cat3.name + ": win " + (cat2.fight(cat3) ? cat2.name : cat3.name));
    }

    public static class Cat {
        protected String name;
        protected int age;
        protected int weight;
        protected int strength;

        public Cat(String name, int age, int weight, int strength) {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.strength = strength;
        }

        public boolean fight(Cat anotherCat) {
            int ageAdvantage = this.age > anotherCat.age ? 1 : 0;
            int weightAdvantage = this.weight > anotherCat.weight ? 1 : 0;
            int strengthAdvantage = this.strength > anotherCat.strength ? 1 : 0;

            int score = ageAdvantage + weightAdvantage + strengthAdvantage;
            return score > 2; // return score > 2 ? true : false;
        }
    }
}
