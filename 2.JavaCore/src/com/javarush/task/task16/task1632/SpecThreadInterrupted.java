package com.javarush.task.task16.task1632;

public class SpecThreadInterrupted extends Thread {
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
    }
}
