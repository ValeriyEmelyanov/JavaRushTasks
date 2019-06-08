package com.javarush.games.minesweeper;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

//Краткая инструкция:
//        > Открыть поле - щелкнуть  левой кнопкой мыши.
//        > Поставить флаг - щелкнуть  правой кнопкой мыши.
//        > Закрыть игру - нажать комбинацию клавиш Alt+F4.
//        > Рестарт игры - щелкнуть  левой кнопкой мыши. Доступно если игра закончена.

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField = 0;
    private int countFlags;
    private static final int mineNum = 6;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";
    private static final int FONTSIZE = 72;
    private boolean isGameStopped = false;
    private int countClosedTiles = SIDE * SIDE;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        int random;
        boolean isMine;
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                isMine = (getRandomNumber(10) == mineNum);
                countMinesOnField = countMinesOnField + (isMine ? 1 : 0);
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.GRAY);
                setCellValue(x, y, "");
            }
        }
        countMineNeighbors();
        countFlags = countMinesOnField;
        //isGameStopped = false;
    }

    private List<GameObject> getNeighbors(GameObject obj) {
        ArrayList<GameObject> list = new ArrayList<GameObject>(8);
        int x, y;
        for (int shiftY = -1; shiftY <= 1; shiftY++) {
            for (int shiftX = -1; shiftX <= 1; shiftX++) {
                x = obj.x + shiftX;
                y = obj.y + shiftY;
                if ((x < 0) || (x > SIDE - 1) || (y < 0) || (y > SIDE - 1) || (x == obj.x && y == obj.y)) {
                    continue;
                }
                list.add(gameField[y][x]);
            }
        }
        return list;
    }

    private void countMineNeighbors() {
        List<GameObject> list;
        GameObject obj;
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                obj = gameField[y][x];
                if (obj.isMine) {
                    continue;
                }
                list = getNeighbors(obj);
                for (GameObject neighbor : list) {
                    obj.countMineNeighbors += (neighbor.isMine ? 1 : 0);
                }
            }
        }
    }

    private void openTile(int x, int y) {
        if (isGameStopped) {
            return;
        }

        GameObject obj = gameField[y][x];
        if (obj.isOpen || obj.isFlag) {
            return;
        }
        if (obj.isMine) {
            setCellValueEx(x, y, Color.RED, MINE);
            gameOver();
            return;
        }

        if(obj.countMineNeighbors > 0) {
            setCellNumber(x, y, obj.countMineNeighbors);
        } else {
            setCellValue(x, y, "");
        }

        setCellColor(x, y, Color.FORESTGREEN);
        obj.isOpen = true;
        score += 5;
        setScore(score);
        if (--countClosedTiles == countMinesOnField) {
            win();
            return;
        }

        if (obj.countMineNeighbors == 0) {
            List<GameObject> list = getNeighbors(obj);
            for (GameObject neighbor : list) {
                if (!neighbor.isOpen) {
                    openTile(neighbor.x, neighbor.y);
                }
            }
        }
    }

    private void markTile(int x, int y) {
        if (isGameStopped) {
            return;
        }

        GameObject obj = gameField[y][x];
        if (obj.isOpen) {
            return;
        }
        if (!obj.isFlag && countFlags == 0) {
            return;
        }

        if (!obj.isFlag) {
            obj.isFlag = true;
            countFlags--;
            setCellValue(x, y, FLAG);
            setCellColor(x, y, Color.ORANGE);
        } else {
            obj.isFlag = false;
            countFlags++;
            setCellValue(x, y, "");
            setCellColor(x, y, Color.GRAY);
        }
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "  Game is over  ", Color.LIGHTYELLOW, FONTSIZE);
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.BLUE, "   You won!!!   ", Color.CYAN, FONTSIZE);
    }

    private void restart() {
        isGameStopped = false;
        countClosedTiles = SIDE * SIDE;
        score = 0;
        countMinesOnField = 0;

        setScore(score);
        createGame();
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        super.onMouseLeftClick(x, y);
        if (isGameStopped) {
            restart();
        } else {
            openTile(x, y);
        }
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        super.onMouseRightClick(x, y);
        markTile(x, y);
    }
}
