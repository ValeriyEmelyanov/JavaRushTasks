package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    public static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode)
            throws InterruptOperationException {
        if (currencyCode == null) {
            throw new InterruptOperationException();
        }

        CurrencyManipulator manipulator = map.get(currencyCode.toUpperCase());
        if (manipulator == null) {
            manipulator = new CurrencyManipulator(currencyCode.toUpperCase());
            map.put(currencyCode, manipulator);
        }
        return manipulator;
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }
}
