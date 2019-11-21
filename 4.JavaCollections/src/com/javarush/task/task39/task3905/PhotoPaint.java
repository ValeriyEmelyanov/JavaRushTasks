package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        if (image == null || image.length < 1) {
            return false;
        }

        if (x < 0 || y < 0 || y >= image.length) {
            return false;
        }

        if (image[y] == null || x >= image[y].length
                || desiredColor == null
                || image[y][x] == null || image[y][x] == desiredColor) {
            return false;
        }

        Color oldColor = image[y][x];
        image[y][x] = desiredColor;

        if (x - 1 >= 0 && image[y][x - 1] == oldColor) {
            paintFill(image, x - 1, y, desiredColor);
        }
        if (x + 1 < image[y].length && image[y][x + 1] == oldColor) {
            paintFill(image, x + 1, y, desiredColor);
        }
        if (y - 1 >= 0 && image[y - 1][x] == oldColor) {
            paintFill(image, x, y - 1, desiredColor);
        }
        if (y + 1 < image.length && image[y + 1][x] == oldColor) {
            paintFill(image, x, y + 1, desiredColor);
        }

        return true;
    }
}
