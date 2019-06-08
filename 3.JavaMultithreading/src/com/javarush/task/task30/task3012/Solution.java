package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        int[] row = {1, 3, 9, 27, 81, 243, 729, 2187};

        int res = number;
        int tail;
        StringBuilder sb = new StringBuilder(number + " =");
        int index = 0;

        while (res > 0) {
            tail = res % 3;
            res  = res / 3;

            if (tail == 1) {
                sb.append(" + ");
                sb.append(row[index]);
            } else if (tail == 2) {
                res += 1;
                sb.append(" - ");
                sb.append(row[index]);
            }

            index++;
        }

        System.out.println(sb);
    }
}