package com.javarush.task.task16.task1632;

public class SpecThreadMessage extends Thread implements Message {
    @Override
    public void run() {
        while (!isInterrupted()) {
            //
        }
    }

    @Override
    public void showWarning() {
        interrupt();
    }
}
