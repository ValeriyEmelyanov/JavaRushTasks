package com.javarush.task.task36.task3611;

import java.util.HashSet;
import java.util.Set;

/* 
Сколько у человека потенциальных друзей?
*/

public class Solution {
    private boolean[][] humanRelationships;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.humanRelationships = generateRelationships();

        Set<Integer> allFriendsAndPotentialFriends = solution.getAllFriendsAndPotentialFriends(4, 2);
        System.out.println(allFriendsAndPotentialFriends);                              // Expected: [0, 1, 2, 3, 5, 7]
        Set<Integer> potentialFriends = solution.removeFriendsFromSet(allFriendsAndPotentialFriends, 4);
        System.out.println(potentialFriends);                                           // Expected: [2, 5, 7]
    }

    public Set<Integer> getAllFriendsAndPotentialFriends(int index, int deep) {
        //напишите тут ваш код
        Set<Integer> result = new HashSet<>();

        if (deep > 0) {
            for (int i = 0; i < humanRelationships.length; i++) {
                if ((i < index)  && (index < humanRelationships.length) && humanRelationships[index][i]) {
                    result.add(i);
                    result.addAll(getAllFriendsAndPotentialFriends(i, deep - 1));
                } else if ((i > index) && humanRelationships[i][index]) {
                    result.add(i);
                    result.addAll(getAllFriendsAndPotentialFriends(i, deep - 1));
                }
            }
        }

        result.remove(index);

        return result;
    }

    /*
    1.  Для нахождения всех друзей человека с индексом n нужно
    сначала пройтись по горизонтальному массиву с индексом n,
    а затем по вертикальному с тем же индексом.
    Например, для человека с индексом 1 горизонтальный массив - это true,true ,
    а вертикальный true,true,false,true,false,false,false.
    Т.е. этот человек дружит с 0, 2 и 4.
    2. Насколько я понял, в условии ошибки нет, просто его нужно прочитать много раз))
     */
    /*
    "int index - это индекс человека в массиве (начиная с нуля);"
    4ка дружит с 0, 1, 3
    0 дружит с 1, 4, 5
    1 дружит с 2, 4
    3ка дружит с 4, 7
    итого 0, 1, 2, 3, 5, 7 (4ку не берем)
     */

    // Remove from the set the people with whom you already have a relationship
    public Set<Integer> removeFriendsFromSet(Set<Integer> set, int index) {
        for (int i = 0; i < humanRelationships.length; i++) {
            if ((i < index) && (index < humanRelationships.length) && humanRelationships[index][i]) {
                set.remove(i);
            } else if ((i > index) && humanRelationships[i][index]) {
                set.remove(i);
            }
        }
        return set;
    }

    // Return test data
    private static boolean[][] generateRelationships() {
        return new boolean[][]{
                {true},                                                                 //0
                {true, true},                                                           //1
                {false, true, true},                                                    //2
                {false, false, false, true},                                            //3
                {true, true, false, true, true},                                        //4
                {true, false, true, false, false, true},                                //5
                {false, false, false, false, false, true, true},                        //6
                {false, false, false, true, false, false, false, true}                  //7
        };
    }
}