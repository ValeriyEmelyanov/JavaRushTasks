package com.javarush.task.task22.task2209;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
Составить цепочку слов
*/
public class Solution {

    static class Word {
        public char firstChar;
        public char lastChar;
        public int index;
        public String text;
        public List<Word> nextWords;

        public Word(char firstChar, char lastChar, int index, String text) {
            this.firstChar = firstChar;
            this.lastChar = lastChar;
            this.index = index;
            this.text = text;
            this.nextWords = new ArrayList<Word>();
        }

        @Override
        public String toString() {
            return String.format("%s [%d]", text, index);
        }
    }

    static class Result {
        public List<Word> list;

        public Result() {
            list = new ArrayList<>();
        }

        public Result(Result result) {
            list = new ArrayList<>();
            result.list.forEach(word -> this.list.add(word));
        }

        public void addWord(Word word) {
            list.add(word);
        }

        public int size() {
            return list.size();
        }

        public boolean contains(Word word) {
            return list.contains(word);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Word word : list) {
                sb.append(word.text).append(' ');
            }
            return sb.toString();
        }
    }

    static class ResultContainer {
        public Result bestResult;
        public int maxChainLen;
        public boolean isStop;
    }

    public static void main(String[] args) {
        String fileName = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //String fileName = "D:\\Валера\\test10.txt";

        StringBuilder sb = new StringBuilder();
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName))) {
            int len;
            char[] chars = new char[1024];
            while (reader.ready()) {
                len = reader.read(chars);
                sb.append(chars, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] words = sb.toString().split("\\s");

        //final String input = "Киев Нью-Йорк Амстердам Вена Мельбурн";
        //final String input = "трам Нет труД муд доМ мандарин март";
        //String[] words = input.split("\\s");

        Arrays.sort(words);

        // Результат
        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder sb = new StringBuilder();
        if (words == null || words.length == 0) {
            return sb;
        }
        if (words.length == 1) {
            return  sb.append(words[0]);
        }

        // Заполнить список слов, первые и последние буквы в вернем регистре
        List<Word> wordsList = new ArrayList<>();
        String tmp;
        for (int i = 0; i < words.length; i++) {
            tmp = words[i].toUpperCase();
            wordsList.add(new Word(tmp.charAt(0), tmp.charAt(tmp.length() - 1), i, words[i]));
        }

        // Заполнить возможные продолжения
        for (Word w : wordsList) {
            for (Word w1 : wordsList) {
                if (w == w1) {
                    continue;
                }
                if (w.lastChar == w1.firstChar) {
                    w.nextWords.add(w1);
                }
            }
        }

        // Поехали
        ResultContainer container = new ResultContainer();
        container.maxChainLen = words.length;
        container.isStop = false;
        addNext(wordsList, new Result(), container);

        //return container.bestResult.stringBuilder();

        for (Word w : container.bestResult.list) {
            sb.append(w.text).append(' ');
        }
        return sb;
    }

    static void addNext(List<Word> nextWords, Result result, ResultContainer container) {
        if (nextWords.size() == 0) {
            // значит закончили
            if (container.bestResult == null || result.size() > container.bestResult.size()) {
                container.bestResult = result;
            }
            return;
        }

        for (Word word1 : nextWords) {
            // Проверяем, что слово, которое собираемся добавить, нет в результате
            if (result.contains(word1)) {
                continue;
            }

            // Добавляем
            Result local;
            if (nextWords.size() == 1) {
                local = result;
            } else {
                local = new Result(result);
            }
            local.addWord(word1);

            // А может уже все?
            if (local.size() == container.maxChainLen) {
                container.bestResult = local;
                container.isStop = true;
                return;
            }

            // Поехали дальше
            addNext(word1.nextWords, local, container);
            if (container.isStop) {
                return;
            }
        }

        // запомнить лучший результат (может оказаться, что все уже были в составе)
        if (container.bestResult == null || result.size() > container.bestResult.size()) {
            container.bestResult = result;
        }
    }
}
