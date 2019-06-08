package com.javarush.task.task06.task0602;

/* 
Пустые кошки, пустые псы
*/

public class Cat {
    static int catCount = 0;

    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();
        System.out.println("Cat count:" + Cat.catCount);
        cat = null;
        dog = null;
        System.out.println("Cat count:" + Cat.catCount);
    }
    public Cat() {
        System.out.println("A Cat was created");
        catCount++;
    }
    protected void finalize () throws Throwable {
        System.out.println("A Cat was destroyed");
        catCount--;
    }
}

class Dog {
    public Dog() {
        System.out.println("A Dog was created");
    }
    protected void finalize() throws Throwable {
        System.out.println("A Dog was destroyed");
    }
}
