package com.javarush.task.task19.task1916;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = reader.readLine();
        String filename2 = reader.readLine();
        reader.close();
        //String filename1 = "D:\\Валера\\test1.txt";
        //String filename2 = "D:\\Валера\\test2.txt";

        BufferedReader reader1 = new BufferedReader(new FileReader(filename1));
        BufferedReader reader2 = new BufferedReader(new FileReader(filename2));

        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();

        String line;
        while ((line = reader1.readLine()) != null) {
            list1.add(line);
        }
        reader1.close();
        while ((line = reader2.readLine()) != null) {
            list2.add(line);
        }
        reader2.close();

        int i = 0, j = 0;
        while (i < list1.size() || j < list2.size()) {
            if (i < list1.size() && j < list2.size() && list1.get(i).equals(list2.get(j))) {
                lines.add(new LineItem(Type.SAME, list1.get(i)));
                i++;
                j++;
            } else if (i + 1 < list1.size() && j < list2.size() && list1.get(i + 1).equals(list2.get(j))) {
                lines.add(new LineItem(Type.REMOVED, list1.get(i)));
                lines.add(new LineItem(Type.SAME, list1.get(i + 1)));
                i += 2;
                j++;
            } else if (i < list1.size() && j + 1 < list2.size() && list1.get(i).equals(list2.get(j + 1))) {
                lines.add(new LineItem(Type.ADDED, list2.get(j)));
                lines.add(new LineItem(Type.SAME, list1.get(i)));
                i++;
                j += 2;
            } else if (i < list1.size() && !(j < list2.size())) {
                lines.add(new LineItem(Type.REMOVED, list1.get(i)));
                i++;
            } else if (!(i < list1.size()) && j < list2.size()) {
                lines.add(new LineItem(Type.ADDED, list2.get(j)));
                j++;
            } else {
                i++;
                j++;
            }
        }

        //for (LineItem lineItem : lines) {
        //    System.out.println(lineItem.toString());
        //}

    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

        //@Override
        //public String toString() {
        //    return type + " " + line;
        //}
    }
}
