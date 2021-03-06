package com.javarush.task.task24.task2412;

import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Знания - сила!
*/
public class Solution {
    public static void main(String[] args) {
        List<Stock> stocks = getStocks();
        sort(stocks);
        Date actualDate = new Date();
        printStocks(stocks, actualDate);
    }

    public static void printStocks(List<Stock> stocks, Date actualDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        double[] filelimits = {0d, actualDate.getTime()};
        String[] filepart = {"change {4}", "open {2} and last {3}"};

        ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
        //Format[] testFormats = {null, dateFormat, fileform};
        Format[] testFormats = {null, null, dateFormat, fileform};
        MessageFormat pattform = new MessageFormat("{0}   {1} | {5} {6}");
        pattform.setFormats(testFormats);

        for (Stock stock : stocks) {
            String name = ((String) stock.get("name"));
            String symbol = (String) stock.get("symbol");
            double open = !stock.containsKey("open") ? 0 : ((double) stock.get("open"));
            double last = !stock.containsKey("last") ? 0 : ((double) stock.get("last"));
            double change = !stock.containsKey("change") ? 0 : ((double) stock.get("change"));
            Date date = (Date) stock.get("date");
            Object[] testArgs = {name, symbol, open, last, change, date, date.getTime()};
            System.out.println(pattform.format(testArgs));
        }
    }

    public static void sort(List<Stock> list) {
        list.sort(new Comparator<Stock>() {
            public int compare(Stock stock1, Stock stock2) {
                // name sort
                String name1 = ((String) stock1.get("name"));
                String name2 = ((String) stock2.get("name"));
                int result = name1.compareTo(name2);
                if (result != 0) {
                    return result;
                }
                // date sort
                Date date1 = dateTrim((Date) stock1.get("date"));
                Date date2 = dateTrim((Date) stock2.get("date"));
                //Date date1 = (Date) stock1.get("date");
                //Date date2 = (Date) stock2.get("date");
                result = date1.compareTo(date2);
                if (result != 0) {
                    return -result;
                }
                // change sort
                double change1 = 0;
                if (stock1.containsKey("change")) {
                    change1 = (double) stock1.get("change");
                } else {
                    double open1 = !stock1.containsKey("open") ? 0 : ((double) stock1.get("open"));
                    double last1 = !stock1.containsKey("last") ? 0 : ((double) stock1.get("last"));
                    change1 = last1 - open1;
                }
                double change2 = 0;
                if (stock2.containsKey("change")) {
                    change2 = (double) stock2.get("change");
                } else {
                    double open2 = !stock2.containsKey("open") ? 0 : ((double) stock2.get("open"));
                    double last2 = !stock2.containsKey("last") ? 0 : ((double) stock2.get("last"));
                    change2 = last2 - open2;
                }
                //return (change1 == change2) ? 0 : ((change1 > change2) ? -1 : 1);
                return Double.compare(change2, change1);
            }
        });
    }

    private static Date dateTrim(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        return calendar.getTime();
    }

    public static class Stock extends HashMap<String, Object> {
        public Stock(String name, String symbol, double open, double last) {
            put("name", name);
            put("symbol", symbol);
            put("open", open);
            put("last", last);
            put("date", getRandomDate(2020));
        }

        public Stock(String name, String symbol, double change, Date date) {
            put("name", name);
            put("symbol", symbol);
            put("date", date);
            put("change", change);
        }
    }

    public static List<Stock> getStocks() {
        List<Stock> stocks = new ArrayList<>();

        stocks.add(new Stock("Fake Apple Inc.", "AAPL", 125.64, 123.43));
        //stocks.add(new Stock("Fake Apple Inc.", "AAPL", 128.00, 123.43));
        stocks.add(new Stock("Fake Cisco Systems, Inc.", "CSCO", 25.84, 26.3));
        stocks.add(new Stock("Fake Google Inc.", "GOOG", 516.2, 512.6));
        stocks.add(new Stock("Fake Intel Corporation", "INTC", 21.36, 21.53));
        stocks.add(new Stock("Fake Level 3 Communications, Inc.", "LVLT", 5.55, 5.54));
        stocks.add(new Stock("Fake Microsoft Corporation", "MSFT", 29.56, 29.72));

        //stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", 135.64, 123.43));
        //stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", .1, new Date()));
        //stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", -.2, new Date()));

        stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", .1, getRandomDate()));
        stocks.add(new Stock("Fake Oracle Corporation", "ORCL", .15, getRandomDate()));
        stocks.add(new Stock("Fake Starbucks Corporation", "SBUX", .03, getRandomDate()));
        stocks.add(new Stock("Fake Yahoo! Inc.", "YHOO", .32, getRandomDate()));
        stocks.add(new Stock("Fake Applied Materials, Inc.", "AMAT", .26, getRandomDate()));
        stocks.add(new Stock("Fake Comcast Corporation", "CMCSA", .5, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));

        return stocks;
    }

    public static Date getRandomDate() {
        return getRandomDate(1970);
    }

    public static Date getRandomDate(int startYear) {
        int year = startYear + (int) (Math.random() * 30);
        int month = (int) (Math.random() * 12);
        int day = (int) (Math.random() * 28);
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTime();
    }
}

