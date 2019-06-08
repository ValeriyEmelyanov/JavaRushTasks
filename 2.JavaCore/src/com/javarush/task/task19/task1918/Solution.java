package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) throws IOException {

        if (args.length < 1) {
            System.out.println("Необходимо передать параметр");
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        //String fileName = "D:\\Валера\\test3.txt";

        FileReader fileReader = new FileReader(fileName);
        char[] cbuf = new char[100];
        int len = 0;
        StringBuilder sb = new StringBuilder();
        while (fileReader.ready()) {
            len = fileReader.read(cbuf);
            sb.append(cbuf, 0, len);
        }
        fileReader.close();

        String text = sb.toString().replaceAll("[\\n\\r]", "");

        ArrayList<Item> itemList = new ArrayList<>();
        String tagStart = "<" + args[0];
        String tagEnd = "</" + args[0] + ">";
        int lenStart = tagStart.length();
        int lenEnd = tagEnd.length();
        int start = 0;
        int end;
        while (true) {

            if (start >= text.length()) {
                break;
            }

            end = text.indexOf(tagEnd, start) + lenEnd;
            start = text.indexOf(tagStart, start);

            if (start > 0 && start < end) {
                itemList.add(new Item(start));
                start += lenStart;
            } else if (start > 0 && start == end) {
                Item.setEnd(end, itemList);
                itemList.add(new Item(start));
                start = start + lenStart;
            } else if (end > 0) {
                Item.setEnd(end, itemList);
                start = Item.maxEnd(itemList);
            } else {
                break;
            }
        }

        //for (Item item : itemList) {
        //    System.out.println(item);
        //}

        ArrayList<String> tagList = new ArrayList<>();
        for (Item item : itemList) {
            tagList.add(text.substring(item.start, item.end));
        }

        for (String tag : tagList) {
            System.out.println(tag);
        }

    }

    private static class Item {
        public int start;
        public int end;
        public boolean filled;

        public Item(int start) {
            this.start = start;
        }

        @Override
        public String toString() {
            return String.format("%d - %d %s", start, end, filled ? "Ok" : "-");
        }

        public static void setEnd(int end, ArrayList<Item> itemList) {
            for (int i = itemList.size() - 1; i >= 0 ; i--) {
                Item item = itemList.get(i);
                if (!item.filled && item.start < end) {
                    item.end = end;
                    item.filled = true;
                    break;
                }
            }
        }

        private static int maxEnd(ArrayList<Item> itemList) {
            int maxEnd = 0;
            for (Item item: itemList) {
                if (item.end > maxEnd) {
                    maxEnd = item.end;
                }
            }
            return maxEnd;
        }

    }

}
