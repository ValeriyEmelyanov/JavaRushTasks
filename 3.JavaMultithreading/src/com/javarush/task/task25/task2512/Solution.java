package com.javarush.task.task25.task2512;

/* 
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e != null) {
            uncaughtException(t, e.getCause());
            t.interrupt();
            System.out.println(String.format("%s: %s",
                    e.getClass().getName(),
                    e.getMessage()
            ));
        }
    }

    public static void main(String[] args) {
//        throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
        new Solution().uncaughtException(Thread.currentThread(),
                new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }
}
