package com.javarush.task.task27.task2710;

public class Mail {
    private String text;

    public String getText() {
        if (text == null) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyAll();
    }
}
