package com.javarush.task.task08.task0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* 
Модернизация ПО
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // List of addresses
//        List<String> addresses = new ArrayList<>();
        HashMap<String, String> addresses = new HashMap<String, String>();
        while (true) {
//            String family = reader.readLine();
//            if (family.isEmpty()) break;
//            addresses.add(family);
            String city = reader.readLine();
            if (city.isEmpty()) break;

            String family = reader.readLine();
            addresses.put(city, family);
        }

        // Read the house number
//        int houseNumber = Integer.parseInt(reader.readLine());
        String city = reader.readLine();

//        if (0 <= houseNumber && houseNumber < addresses.size()) {
//            String familyName = addresses.get(houseNumber);
//            System.out.println(familyName);
//        }
        String family = addresses.get(city);
        if (family != null) {
            System.out.println(family);
        }

    }
}
