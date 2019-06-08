package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> list = detectAllWords(crossword, "home", "same");
        //List<Word> list = detectAllWords(crossword, "epnu");
        System.out.println("Variant 1");
        for (Word word : list) {
            System.out.println(word);
        }
        /*
        Ожидаемый результат
        home - (5, 3) - (2, 0)
        same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();
        for (int i = 0; i < crossword.length; i++) {
            for (int j = 0; j < crossword[i].length; j++) {
                for (String word : words) {
                    checkWord(crossword, word, list, j, i);
                }
            }
        }
        return list;
    }

    private static void checkWord(int[][] crossword, String word, List<Word> list, int x, int y) {
        char[] chars = word.toCharArray();
        // проверим первую букву
        if ((char) crossword[y][x] != chars[0]) {
            return;
        }

        // необходимо проверить 8 направлений
        int limitY = crossword.length - 1;
        int limitX = crossword[0].length - 1;
        int lenWord = word.length();
        boolean flag;

        // Север
        if (y - (lenWord - 1) >= 0) {
            flag = true;
            for (int k = 1; k < lenWord; k++) {
                if (chars[k] != (char) crossword[y - k][x]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                Word result = new Word(word);
                result.setStartPoint(x, y);
                result.setEndPoint(x, y - (lenWord - 1));
                list.add(result);
            }
        }

        // Северо=Восток
        if (y  - (lenWord - 1) >= 0 && x + (lenWord - 1) <= limitX) {
            flag = true;
            for (int k = 1; k < lenWord; k++) {
                if (chars[k] != (char) crossword[y - k][x + k]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                Word result = new Word(word);
                result.setStartPoint(x, y);
                result.setEndPoint(x + (lenWord - 1), y - (lenWord - 1));
                list.add(result);
            }
        }

        // Восток
        if (x + (lenWord - 1) <= limitX) {
            flag = true;
            for (int k = 1; k < lenWord; k++) {
                if (chars[k] != (char) crossword[y][x + k]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                Word result = new Word(word);
                result.setStartPoint(x, y);
                result.setEndPoint(x + (lenWord - 1), y);
                list.add(result);
            }
        }

        // Юго-Восток
        if (y  + (lenWord - 1) <= limitY && x + (lenWord - 1) <= limitX) {
            flag = true;
            for (int k = 1; k < lenWord; k++) {
                if (chars[k] != (char) crossword[y + k][x + k]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                Word result = new Word(word);
                result.setStartPoint(x, y);
                result.setEndPoint(x + (lenWord - 1), y + (lenWord - 1));
                list.add(result);
            }
        }

        // Юг
        if (y  + (lenWord - 1) <= limitY) {
            flag = true;
            for (int k = 1; k < lenWord; k++) {
                if (chars[k] != (char) crossword[y + k][x]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                Word result = new Word(word);
                result.setStartPoint(x, y);
                result.setEndPoint(x, y + (lenWord - 1));
                list.add(result);
            }
        }

        // Юго-Запад
        if (y  + (lenWord - 1) <= limitY && x - (lenWord - 1) >= 0) {
            flag = true;
            for (int k = 1; k < lenWord; k++) {
                if (chars[k] != (char) crossword[y + k][x - k]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                Word result = new Word(word);
                result.setStartPoint(x, y);
                result.setEndPoint(x - (lenWord - 1), y + (lenWord - 1));
                list.add(result);
            }
        }

        // Запад
        if (x - (lenWord - 1) >= 0) {
            flag = true;
            for (int k = 1; k < lenWord; k++) {
                if (chars[k] != (char) crossword[y][x - k]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                Word result = new Word(word);
                result.setStartPoint(x, y);
                result.setEndPoint(x - (lenWord - 1), y);
                list.add(result);
            }
        }

        // Северо-Запад
        if (y  - (lenWord - 1) >= 0 && x - (lenWord - 1) >= 0) {
            flag = true;
            for (int k = 1; k < lenWord; k++) {
                if (chars[k] != (char) crossword[y - k][x - k]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                Word result = new Word(word);
                result.setStartPoint(x, y);
                result.setEndPoint(x - (lenWord - 1), y - (lenWord - 1));
                list.add(result);
            }
        }
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
