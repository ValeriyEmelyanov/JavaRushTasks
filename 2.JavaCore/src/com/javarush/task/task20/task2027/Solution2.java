package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
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
        System.out.println("Variant 2");
        for (Word word : list) {
            System.out.println(word);
        }
        /*
        Ожидаемый результат
        home - (5, 3) - (2, 0)
        same - (1, 1) - (4, 1)
         */
    }

    interface Coordinator {
        boolean checkLimit(int x, int y, int lenWord, int limitX, int limitY);
        default int getX(int x, int k) {return  x;}
        default int getY(int y, int k) {return  y;}
        default int getEndX(int x, int lenWord) {return x;}
        default int getEndY(int y, int lenWord) {return y;}
    }

    static Coordinator[] coordinators = {
            // Север
            new Coordinator() {
                @Override
                public boolean checkLimit(int x, int y, int lenWord, int limitX, int limitY) {
                    return y - (lenWord - 1) >= 0;
                }

                @Override
                public int getY(int y, int k) {
                    return y - k;
                }

                @Override
                public int getEndY(int y, int lenWord) {
                    return y - (lenWord - 1);
                }
            },
            // Северо-Восток
            new Coordinator() {
                @Override
                public boolean checkLimit(int x, int y, int lenWord, int limitX, int limitY) {
                    return y  - (lenWord - 1) >= 0 && x + (lenWord - 1) <= limitX;
                }

                @Override
                public int getX(int x, int k) {
                    return x + k;
                }

                @Override
                public int getY(int y, int k) {
                    return y - k;
                }

                @Override
                public int getEndX(int x, int lenWord) {
                    return x + (lenWord - 1);
                }

                @Override
                public int getEndY(int y, int lenWord) {
                    return y - (lenWord - 1);
                }
            },
            // Восток
            new Coordinator() {
                @Override
                public boolean checkLimit(int x, int y, int lenWord, int limitX, int limitY) {
                    return x + (lenWord - 1) <= limitX;
                }

                @Override
                public int getX(int x, int k) {
                    return x + k;
                }

                @Override
                public int getEndX(int x, int lenWord) {
                    return x + (lenWord - 1);
                }
            },
            // Юго-Восток
            new Coordinator() {
                @Override
                public boolean checkLimit(int x, int y, int lenWord, int limitX, int limitY) {
                    return y  + (lenWord - 1) <= limitY && x + (lenWord - 1) <= limitX;
                }

                @Override
                public int getX(int x, int k) {
                    return x + k;
                }

                @Override
                public int getY(int y, int k) {
                    return y + k;
                }

                @Override
                public int getEndX(int x, int lenWord) {
                    return x + (lenWord - 1);
                }

                @Override
                public int getEndY(int y, int lenWord) {
                    return y + (lenWord - 1);
                }
            },
            // Юг
            new Coordinator() {
                @Override
                public boolean checkLimit(int x, int y, int lenWord, int limitX, int limitY) {
                    return y  + (lenWord - 1) <= limitY;
                }

                @Override
                public int getY(int y, int k) {
                    return y + k;
                }

                @Override
                public int getEndY(int y, int lenWord) {
                    return y + (lenWord - 1);
                }
            },
            // Юго-Запад
            new Coordinator() {
                @Override
                public boolean checkLimit(int x, int y, int lenWord, int limitX, int limitY) {
                    return y  + (lenWord - 1) <= limitY && x - (lenWord - 1) >= 0;
                }

                @Override
                public int getX(int x, int k) {
                    return x - k;
                }

                @Override
                public int getY(int y, int k) {
                    return y + k;
                }

                @Override
                public int getEndX(int x, int lenWord) {
                    return x - (lenWord - 1);
                }

                @Override
                public int getEndY(int y, int lenWord) {
                    return y - (lenWord - 1);
                }
            },
            // Запад
            new Coordinator() {
                @Override
                public boolean checkLimit(int x, int y, int lenWord, int limitX, int limitY) {
                    return x - (lenWord - 1) >= 0;
                }

                @Override
                public int getX(int x, int k) {
                    return x - k;
                }

                @Override
                public int getEndX(int x, int lenWord) {
                    return x - (lenWord - 1);
                }
            },
            //Северо-Запад
            new Coordinator() {
                @Override
                public boolean checkLimit(int x, int y, int lenWord, int limitX, int limitY) {
                    return y  - (lenWord - 1) >= 0 && x - (lenWord - 1) >= 0;
                }

                @Override
                public int getX(int x, int k) {
                    return x - k;
                }

                @Override
                public int getY(int y, int k) {
                    return y - k;
                }

                @Override
                public int getEndX(int x, int lenWord) {
                    return x - (lenWord - 1);
                }

                @Override
                public int getEndY(int y, int lenWord) {
                    return y - (lenWord - 1);
                }
            }
    };

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

        for (Coordinator coordinator : coordinators) {
            if (coordinator.checkLimit(x, y, lenWord, limitX, limitY)) {
                flag = true;
                for (int k = 1; k < lenWord; k++) {
                    if (chars[k] != (char) crossword[coordinator.getY(y, k)][coordinator.getX(x, k)]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    Word result = new Word(word);
                    result.setStartPoint(x, y);
                    result.setEndPoint(coordinator.getEndX(x, lenWord), coordinator.getEndY(y, lenWord));
                    list.add(result);
                }
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
