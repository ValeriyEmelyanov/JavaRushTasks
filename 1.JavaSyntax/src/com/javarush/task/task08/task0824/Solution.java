package com.javarush.task.task08.task0824;

/* 
Собираем семейство
*/

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        ArrayList<Human> family = new ArrayList<Human>();


        Human child1 = new Human("Петя", true, 10);
        Human child2 = new Human("Гриша", true, 7);
        Human child3 = new Human("Лена", false, 4);

        ArrayList<Human> Children = new ArrayList<>();
        Children.add(child1);
        Children.add(child2);
        Children.add(child3);
        Human papa = new Human("Иван", true, 35, Children);
        Human mama = new Human("Ирина", false, 33, Children);

        ArrayList<Human> Children1 = new ArrayList<>();
        Children1.add(papa);
        Human grandDad1 = new Human("Евгений", true, 57, Children1);
        Human grandMam1 = new Human("Клавдия", false, 52, Children1);

        ArrayList<Human> Children2 = new ArrayList<>();
        Children2.add(mama);
        Human grandDad2 = new Human("Всеволод", true, 58, Children2);
        Human grandMam2 = new Human("Мария", false, 52, Children2);

        family.add(grandDad1);
        family.add(grandMam1);
        family.add(grandDad2);
        family.add(grandMam2);
        family.add(papa);
        family.add(mama);
        family.add(child1);
        family.add(child2);
        family.add(child3);

        for (Human human : family) {
            System.out.println(human);
        }

    }

    public static class Human {
        public String name;
        public boolean sex;
        public int age;
        public ArrayList<Human> children;

        public Human(String name, boolean sex, int age, ArrayList<Human> children) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public Human(String name, boolean sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = new ArrayList<Human>();
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0) {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++) {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }
            return text;
        }
    }

}
