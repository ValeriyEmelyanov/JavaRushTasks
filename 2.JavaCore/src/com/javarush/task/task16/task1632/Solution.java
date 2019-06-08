package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new SpecThreadInfinity());
        threads.add(new SpecThreadInterrupted());
        threads.add(new SpecThreadHurrah());
        threads.add(new SpecThreadMessage());
        threads.add(new SpecThreadReader());

    }

    public static void main(String[] args) {
    }
}