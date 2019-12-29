package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

public class Game2048 extends Game {
    private static final int SIDE = 4;
    private int[][] gameField;
    //private int[][] gameField = {
    //        {2, 4, 8, 16},
    //        {32, 64, 128, 256},
    //        {512, 1024, 2048, 0},
    //        {2, 4, 8, 16}
    //};
    private boolean isGameStopped;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }

    private void createGame() {
        gameField = new int[SIDE][SIDE];
        createNewNumber();
        createNewNumber();
        score = 0;
        setScore(score);
    }

    private void drawScene() {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                setCellColoredNumber(j, i, gameField[i][j]);
            }
        }
    }

    private void createNewNumber() {
        if (getMaxTileValue() == 2048) {
            win();
            return;
        }

        int x;
        int y;
        while (true) {
            x = getRandomNumber(SIDE);
            y = getRandomNumber(SIDE);
            if (gameField[y][x] == 0) {
                break;
            }
        }
        gameField[y][x] = (getRandomNumber(10) == 9) ? 4 : 2;
    }

    private Color getColorByValue(int value) {
        switch (value) {
            case 2:
                return Color.values()[115];
            case 4:
                return Color.values()[25];
            case 8:
                return Color.values()[8];
            case 16:
                return Color.values()[95];
            case 32:
                return Color.values()[46];
            case 64:
                return Color.values()[6];
            case 128:
                return Color.values()[38];
            case 256:
                return Color.values()[64];
            case 512:
                return Color.values()[18];
            case 1024:
                return Color.values()[56];
            case 2048:
                return Color.values()[27];
            default:
                return Color.LIGHTGRAY;
        }
    }

    private void setCellColoredNumber(int x, int y, int value) {
        setCellValueEx(x, y, getColorByValue(value), value == 0 ? "" : String.valueOf(value));
    }

    private boolean compressRow(int[] row) {
        boolean changed = false;
        for (int i = 0; i < row.length - 1; i++) {
            for (int j = 0; j < row.length - 1 - i; j++) {
                if (row[j] == 0 && row[j + 1] != 0) {
                    int tmp = row[j];
                    row[j] = row[j + 1];
                    row[j + 1] = tmp;
                    changed = true;
                }
            }
        }
        return changed;
    }

    private boolean mergeRow(int[] row) {
        boolean changed = false;
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] == 0) {
                continue;
            }
            if (row[i] == row[i + 1]) {
                row[i] *= 2;
                score += row[i];
                row[++i] = 0;
                changed = true;
                setScore(score);
            }
        }
        return changed;
    }

    @Override
    public void onKeyPress(Key key) {
        if (key == Key.SPACE) {
            isGameStopped = false;
            createGame();
            drawScene();
            return;
        }

        if (isGameStopped) {
            return;
        }

        if (!canUserMove()) {
            gameOver();
            return;
        }

        switch (key) {
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            default:
                return;
        }
        drawScene();
    }

    private void moveLeft() {
        boolean shifted = false;
        for (int i = 0; i < SIDE; i++) {
            int[] row = gameField[i];
            shifted |= compressRow(row);
            shifted |= mergeRow(row);
            shifted |= compressRow(row);
        }
        if (shifted) {
            createNewNumber();
        }
    }

    private void moveRight() {
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    private void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }

    private void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private void rotateClockwise() {
        int[][] newGameField = new int[SIDE][SIDE];
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                newGameField[j][SIDE - 1 - i] = gameField[i][j];
            }
        }
        gameField = newGameField;
    }

    private int getMaxTileValue() {
        int maxValue = 0;
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                maxValue = Math.max(maxValue, gameField[j][i]);
            }
        }
        return maxValue;
    }

    private  void win() {
        isGameStopped = true;
        showMessageDialog(Color.ORANGE, " You won!!! ", Color.DARKBLUE, 60);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.BLUE, " Game is over ", Color.AQUAMARINE, 60);
    }

    private boolean canUserMove() {
        // Проверка, что есть нулевой элемент
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if (gameField[j][i] == 0) {
                    return true;
                }
            }
        }
        // Проверка, что есть соседние одинаковые элементы по горихонтали
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE - 1; j++) {
                if (gameField[j][i] == gameField[j + 1][i]) {
                    return true;
                }
            }
        }
        // Проверка, что есть соседние одинаковые элементы по вертикали
        for (int i = 0; i < SIDE - 1; i++) {
            for (int j = 0; j < SIDE; j++) {
                if (gameField[j][i] == gameField[j][i + 1]) {
                    return true;
                }
            }
        }
        return false;
    }
}
