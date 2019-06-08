package com.javarush.task.task27.task2708;

import java.util.HashSet;
import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;

import static java.util.concurrent.ConcurrentHashMap.newKeySet;

public class RealEstate {

    private final Set<Apartment> allApartments;
    private final Set<Apartment> activeApartments;

    public RealEstate() {
        allApartments = new HashSet();
        activeApartments = new HashSet();
//        allApartments = ConcurrentHashMap.newKeySet();
//        activeApartments = ConcurrentHashMap.newKeySet();

        //add some data
        allApartments.add(new Apartment(this));
        allApartments.add(new Apartment(this));
        allApartments.add(new Apartment(this));
        allApartments.add(new Apartment(this));
        allApartments.add(new Apartment(this));
        allApartments.add(new Apartment(this));
    }

    public Set<Apartment> getAllApartments() {
        return allApartments;
    }

    //public synchronized void up(Apartment apartment) {
    public void up(Apartment apartment) {
        activeApartments.add(apartment);
    }

    //public synchronized void revalidate() {
    public void revalidate() {
        activeApartments.clear();
        for (Apartment apartment : allApartments) {
            boolean randomValue = Math.random() * 2 % 2 == 0;
            synchronized (activeApartments) {
                apartment.revalidate(randomValue);
            }
        }
    }
}
