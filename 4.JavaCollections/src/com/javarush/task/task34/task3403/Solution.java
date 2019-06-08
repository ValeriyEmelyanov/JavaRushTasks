package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recurse(int n) {
        if (n <= 1) return;

        int i;
        for (i = 2; i <= n; i++) {
            if (n % i == 0) {
                break;
            }
        }
        System.out.print(i + " ");

        if (i < n ) {
            recurse(n / i);
        }
    }

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        solution.recurse(132);
//    }
}
