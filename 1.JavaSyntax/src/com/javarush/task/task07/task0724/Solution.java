package com.javarush.task.task07.task0724;

/* 
Семейная перепись
*/

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        Human granddad1 = new Human("Иван", true, 52);
        Human grandma1 = new Human("Клавдия", false, 50);
        Human granddad2 = new Human("Петр", true, 53);
        Human grandma2 = new Human("Мария", false, 49);
        Human papa = new Human("Александр", true, 30, granddad1, grandma1);
        Human mama = new Human("Наталия", false, 26, granddad2, grandma2);
        Human child1 = new Human("Мила", false, 11, papa, mama);
        Human child2 = new Human("Матвей", true, 9, papa, mama);
        Human child3 = new Human("Глеб", false, 6, papa, mama);

        ArrayList<Human> family = new ArrayList<>();
        family.add(granddad1);
        family.add(grandma1);
        family.add(granddad2);
        family.add(grandma2);
        family.add(papa);
        family.add(mama);
        family.add(child1);
        family.add(child2);
        family.add(child3);
        for (Human h : family) {
            System.out.println(h);
        }
    }

    public static class Human {
        private String name;
        private boolean sex;
        private int age;
        private Human father;
        private Human mother;

        public Human(String name, boolean sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public Human(String name, boolean sex, int age, Human father, Human mother) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }
}