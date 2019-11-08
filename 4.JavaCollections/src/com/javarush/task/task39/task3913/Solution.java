package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
        System.out.println("IPQuery interface:");
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getIPsForUser("Vasya Pupkin", null, new Date()));
        System.out.println(logParser.getIPsForEvent(Event.WRITE_MESSAGE, null, new Date()));
        System.out.println(logParser.getIPsForStatus(Status.OK, null, new Date()));
        System.out.println();
        System.out.println("UserQuery interface:");
        System.out.println(logParser.getAllUsers());
        System.out.println(logParser.getNumberOfUsers(null, null));
        System.out.println(logParser.getNumberOfUserEvents("Amigo", null, new Date()));
        System.out.println(logParser.getUsersForIP("127.0.0.1", null, null));
        System.out.println(logParser.getUsersForIP("146.34.15.5", null, null));
        System.out.println(logParser.getLoggedUsers(null, new Date()));
        System.out.println(logParser.getDownloadedPluginUsers(null, new Date()));
        System.out.println(logParser.getWroteMessageUsers(null, new Date()));
        System.out.println(logParser.getSolvedTaskUsers(null, new Date()));
        System.out.println(logParser.getSolvedTaskUsers(null, new Date(), 18));
        System.out.println(logParser.getSolvedTaskUsers(null, new Date(), 999));
        System.out.println(logParser.getDoneTaskUsers(null, new Date()));
        System.out.println(logParser.getDoneTaskUsers(null, null));
        System.out.println(logParser.getDoneTaskUsers(null, null, 15));

        System.out.println(logParser.execute("get ip"));
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\""));
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
        System.out.println(logParser.execute("get ip for user = \"Vasya Pupkin\" and date between \"30.08.2012 16:08:39\" and \"30.08.2012 16:08:41\""));

        System.out.println(logParser.execute("get date for event = \"DONE_TASK\" and date between \"30.08.2012 16:08:39\" and \"30.08.2012 16:08:41\""));

    }
}