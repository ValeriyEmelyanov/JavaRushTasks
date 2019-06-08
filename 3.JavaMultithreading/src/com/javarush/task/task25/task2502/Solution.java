package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() throws Exception {
            //init wheels here
            String[] wheelsData = loadWheelNamesFromDB();

            if (Wheel.values().length != wheelsData.length) {
                throw new Exception();
            }

            wheels = new ArrayList<>();
            for (int i = 0; i < wheelsData.length; i++) {
                try {
                    wheels.add(Wheel.valueOf(wheelsData[i]));
                } catch (IllegalArgumentException e) {
                    //e.printStackTrace();
                    throw new Exception();
                }
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) throws Exception {
        Car car = new Car();
    }
}
