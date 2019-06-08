package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);;
    }

    public static String readString() throws IOException {
        String line = READER.readLine();
        return line;
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        //writeMessage("Dishes list:");
        writeMessage(Dish.allDishesToString());
        
        List<Dish> dishes = new ArrayList<>();
        
        while (true) {
            writeMessage("Enter dish or exit:");
            String line = READER.readLine();
            
            if ("exit".equals(line)) {
                break;
            }
            
            Dish dish;
            try {
                dish = Dish.valueOf(line);
            } catch (IllegalArgumentException e) {
                writeMessage("Invalid input!");
                continue;
            }
            dishes.add(dish);
        }
        
        return dishes;
    }

}
