package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 1, 1, 0},
                {0, 1, 0, 1, 1},
                {0, 1, 1, 1, 0},
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1}
        };

        System.out.println(maxSquare(matrix));
    }

    public static int maxSquare(int[][] matrix) {
        int max = Math.max(
                Math.max(matrix[matrix[0].length - 1][ matrix.length - 1],
                matrix[matrix[0].length - 1][ matrix.length - 2]),
                matrix[matrix[0].length - 2][ matrix.length - 1]);

        for (int i = matrix[0].length - 2; i >= 0; i--) {
            for (int j = matrix.length - 2; j >= 0; j--) {
                if (matrix[i][j] != 0) {
                    matrix[i][j] = Math.min(Math.min(matrix[i][j + 1], matrix[i + 1][j]), matrix[i + 1][j + 1]) + 1;
                    if (matrix[i][j] > max) {
                        max = matrix[i][j];
                    }
                }
            }
        }

        return max * max;
    }
}
