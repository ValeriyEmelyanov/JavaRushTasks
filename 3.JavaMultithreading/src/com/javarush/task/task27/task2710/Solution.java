package com.javarush.task.task27.task2710;

/* 
Расставьте wait-notify
*/

/**
 * Мы вызываем блок синхронизации на объекте mail,
 * этим мы захватили мютекс,
 * потом вызываем mail.wait() этим мы усыпляем нить и освобождаем мютекс объекта mail,
 * эта нить будет спать пока ее не разбудят.
 * Потом в другой нити происходит нужное нам событие,
 * мы синхронизируемся на объекте mail, у объекта mail вызываем mail.notify(); или mail.notifyAll();
 * тут мы говорим спящей нити, захвати мютекс объекта mail и продолжи выполнение задачи.
 */
public class Solution {
    public static void main(String[] args) {
        Mail mail = new Mail();
        Thread server = new Thread(new MailServer(mail));
        Thread amigo = new Thread(new Person(mail));

        server.start();
        amigo.start();
    }
}
