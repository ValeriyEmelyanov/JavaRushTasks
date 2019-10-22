package com.javarush.task.task36.task3604;

/* 
Разбираемся в красно-черном дереве
*/
public class Solution {
    public static void main(String[] args) {

        RedBlackTree tree = new RedBlackTree();

        System.out.println(tree.isEmpty());

        tree.insert(10);
        tree.insert(12);
        tree.insert(3);
        tree.insert(40);
        tree.insert(35);

    }
}
