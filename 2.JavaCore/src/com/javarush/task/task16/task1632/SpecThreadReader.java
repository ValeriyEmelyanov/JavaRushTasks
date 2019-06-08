package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SpecThreadReader extends Thread {
    @Override
    public void run() {
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(System.in));
        String sNum;
        int sum = 0;
        try {
            while (true) {
                sNum = reader.readLine();
                if (sNum.equals("N")) break;
                sum += Integer.parseInt(sNum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sum);
    }
}
