package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread thread;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        Thread.State state = null, newState;
        while (state != State.TERMINATED) {
            if ((newState = thread.getState()) != state) {
                state = newState;
                System.out.println(state);
            }
        }
    }
}
