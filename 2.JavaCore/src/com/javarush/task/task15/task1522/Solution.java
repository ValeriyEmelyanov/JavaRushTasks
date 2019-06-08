package com.javarush.task.task15.task1522;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Закрепляем паттерн Singleton
*/

public class Solution {
    public static void main(String[] args) {
//        if (thePlanet != null)
//            System.out.println(thePlanet.getClass().getSimpleName());
    }

    public static Planet thePlanet;

    //add static block here - добавьте статический блок тут
    static {
        readKeyFromConsoleAndInitPlanet();
    }

    public static void readKeyFromConsoleAndInitPlanet() {
        // implement step #5 here - реализуйте задание №5 тут
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String sPlanet = reader.readLine();
            if (sPlanet == null || sPlanet.isEmpty()) {
                thePlanet = null;
            } else if (sPlanet.equals(Planet.EARTH)) {
                thePlanet = Earth.getInstance();
            } else if (sPlanet.equals(Planet.SUN)) {
                thePlanet = Sun.getInstance();
            } else if (sPlanet.equals(Planet.MOON)) {
                thePlanet = Moon.getInstance();
            } else {
                thePlanet = null;
            }
        } catch (IOException e) {
            //e.printStackTrace();
            thePlanet = null;
        }
    }
}
