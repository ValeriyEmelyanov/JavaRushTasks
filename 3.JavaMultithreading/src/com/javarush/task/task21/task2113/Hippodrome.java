package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    public static Hippodrome game;
    private List<Horse> horses;

    public static void main(String[] args) {
        game = new Hippodrome(new ArrayList<Horse>());
        List<Horse> horses = game.getHorses();
        horses.add(new Horse("Lucky", 3., 0.));
        horses.add(new Horse("Rookie", 3., 0.));
        horses.add(new Horse("Wind", 3., 0.));
        game.run();
        game.printWinner();
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run() {
        for (int i = 1; i <= 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }

    public void move() {
        horses.forEach(horse -> horse.move());
    }

    public void print() {
        horses.forEach(horse -> horse.print());
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        Horse winner = null;
        double maxDistance = 0;
        double distance;
        for (Horse horse : horses) {
            distance = horse.getDistance();
            if (distance > maxDistance) {
                maxDistance = distance;
                winner = horse;
            }
        }
        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
