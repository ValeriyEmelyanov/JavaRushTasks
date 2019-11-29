package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new TreeMap<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        denominations.put(denomination, denominations.getOrDefault(denomination, 0) + count);
    }

    public int getTotalAmount() {
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                continue;
            }
            sum += entry.getKey() * entry.getValue();
        }
        return sum;
    }

    public boolean hasMoney() {
        if (denominations.size() > 0 && getTotalAmount() > 0) {
            return true;
        }
        return false;
    }

    // Денег достаточно для выдачи
    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    // Выдать деньги
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> result = new HashMap<>();

        int leftover = expectedAmount;

        // Жадным способом подобрать купюры - максимальное количество банкнот высшего номинала
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            int count = leftover / entry.getKey();
            if (count == 0) {
                continue;
            }
            count = Math.min(count, entry.getValue());

            result.put(entry.getKey(), count);
            leftover -= entry.getKey() * count;

            if (leftover == 0) {
                break;
            }
        }

        if (leftover > 0) {
            throw new NotEnoughMoneyException();
        }

        // Убавить количество купюр в банкомате
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            if (denominations.get(entry.getKey()).equals(entry.getValue())) {
                denominations.remove(entry.getKey());
            } else {
                denominations.put(entry.getKey(), denominations.get(entry.getKey()) - entry.getValue());
            }
        }

        return result;
    }
}
