package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] solutions = new Solution[2];
        for (int i = 0; i < solutions.length; i++) {
            Solution solution = new Solution();
            InnerClass[] innerClasses = solution.innerClasses;
            for (int j = 0; j < innerClasses.length; j++) {
                innerClasses[j] = solution.new InnerClass();
            }
            solutions[i] = solution;
        }

        return solutions;
    }

    public static void main(String[] args) {

    }
}
