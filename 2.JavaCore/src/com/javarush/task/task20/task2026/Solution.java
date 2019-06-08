package com.javarush.task.task20.task2026;

import com.sun.org.apache.bcel.internal.generic.LSTORE;

import java.util.ArrayList;
import java.util.List;

/*
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        //System.out.println(a1[2][1]);
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        byte[][] a3 = new byte[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };
        byte[][] a4 = new byte[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        byte[][] a5 = new byte[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        byte[][] a6 = new byte[][]{
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");

        int count3 = getRectangleCount(a3);
        System.out.println("count = " + count3 + ". Должно быть 1");
        int count4 = getRectangleCount(a4);
        System.out.println("count = " + count4 + ". Должно быть 0");
        int count5 = getRectangleCount(a5);
        System.out.println("count = " + count5 + ". Должно быть 4");
        int count6 = getRectangleCount(a6);
        System.out.println("count = " + count6 + ". Должно быть 1");
    }

    public static int getRectangleCount(byte[][] a) {

        List<Rectangle> rectangles = new ArrayList<>();

        int len = a.length;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (a[i][j] == 1 && !contains(i, j, rectangles)) {
                    addNewRectangle(i, j, rectangles, a);
                }
            }
        }

        return rectangles.size();
    }

    private static class Rectangle {
        int top, left, bottom, right;

        public Rectangle(int top, int left, int bottom, int right) {
            this.top = top;
            this.left = left;
            this.bottom = bottom;
            this.right = right;
        }

        public boolean contains(int y, int x) {
            if (x >= left && x <= right && y >= top && y <= bottom) {
                return true;
            }
            return false;
        }
    }

    private static boolean contains(int y, int x, List<Rectangle> rectangles) {
        for (Rectangle rectangle : rectangles) {
            if (rectangle.contains(y, x)) {
                return true;
            }
        }
        return false;
    }

    private static void addNewRectangle(int y, int x, List<Rectangle> rectangles, byte[][] arr) {
        int len = arr.length;

        int bottom = y;
        if (y < len - 1) {
            for (int i = y; i < len; i++) {
                if (arr[i][x] == 1) {
                    bottom = i;
                } else {
                    break;
                }
            }
        }

        int right = x;
        if (x < len - 1) {
            for (int j = x; j < len; j++) {
                if (arr[y][j] == 1) {
                    right = j;
                } else {
                    break;
                }
            }
        }

        rectangles.add(new Rectangle(y, x, bottom, right));
    }
}
