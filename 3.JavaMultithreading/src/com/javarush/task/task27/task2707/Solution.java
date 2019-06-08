package com.javarush.task.task27.task2707;

/* 
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isLockOrderNormal(final Solution solution, final Object o1, final Object o2) throws Exception {
        //do something here
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o1) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {}
                    synchronized (o2) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {}
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> solution.someMethodWithSynchronizedBlocks(o1, o2));

        thread1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {}
        thread2.start();

        while (!(thread1.getState() == Thread.State.TERMINATED && thread2.getState() == Thread.State.TERMINATED) &&
                !(thread1.getState() == Thread.State.BLOCKED && thread2.getState() == Thread.State.BLOCKED)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
        }

        if (!(thread1.getState() == Thread.State.BLOCKED && thread2.getState() == Thread.State.BLOCKED)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isLockOrderNormal(solution, o1, o2));
    }
}
