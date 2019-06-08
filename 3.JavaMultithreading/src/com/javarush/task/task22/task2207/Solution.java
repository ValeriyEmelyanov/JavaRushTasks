package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        //String fileName = "D:\\Валера\\test9.txt";

        StringBuilder sb = new StringBuilder();

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName))) {
            int len;
            char[] buf = new char[1024];
            while (isr.ready()) {
                len = isr.read(buf);
                sb.append(buf, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] words = sb.toString().replaceAll("\\n", " ").split("\\s");

        for (int i = 0; i < words.length - 1; i++) {
            if (words[i] == null || words[i].isEmpty()) {
                continue;
            }
            for (int j = i + 1; j < words.length; j++) {
                if (words[j] == null || words[j].isEmpty()) {
                    continue;
                }
                if (words[i].equals(new StringBuilder(words[j]).reverse().toString())) {
                    result.add(new Pair(words[i], words[j]));
                    words[j] = null;
                    break;
                }
            }
        }

        //for (String word : words) {
        //    System.out.println(word);
        //}
        //System.out.println("==");

        //result.forEach(pair -> System.out.println(pair));
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
