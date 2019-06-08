package com.javarush.task.task28.task2802;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/
public class Solution {

    public static void main(String[] args) {
        class EmulatorThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulatorThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulatorThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }

    public static class AmigoThreadFactory implements ThreadFactory {
        private static AtomicInteger factoryCounter = new AtomicInteger(0);
        private int id;
        private AtomicInteger threadCounter = new AtomicInteger(0);

        public AmigoThreadFactory() {
            id = factoryCounter.incrementAndGet();
        }

        @Override
        public Thread newThread(Runnable r) {
            String threadName = String.format("%s-pool-%s-thread-%s",
                    Thread.currentThread().getThreadGroup().getName(),
                    id,
                    threadCounter.incrementAndGet()
            );
            Thread thread = new Thread(r);
            thread.setDaemon(false);
            thread.setPriority((Thread.NORM_PRIORITY));
            thread.setName(threadName);
            return thread;
        }
    }
}
