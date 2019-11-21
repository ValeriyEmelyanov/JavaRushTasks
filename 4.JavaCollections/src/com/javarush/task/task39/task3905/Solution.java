package com.javarush.task.task39.task3905;

/* 
Залей меня полностью
*/

public class Solution {
    public static void main(String[] args) {
        Color[][] image = {
                {Color.BLUE, Color.BLUE, Color.BLUE},
                {Color.BLUE, Color.BLUE, Color.BLUE},
                {Color.BLUE, Color.BLUE, Color.BLUE}
        };

        boolean result = (new PhotoPaint()).paintFill(image, 1, 1, Color.GREEN);
        System.out.println(result);
    }
}
