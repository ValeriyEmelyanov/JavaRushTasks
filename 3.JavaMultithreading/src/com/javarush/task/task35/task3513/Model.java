package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    int score = 0;    // текущий счет
    int maxTile = 0;  // максимальный вес плитки
    Stack<Tile[][]> previousStates = new Stack<>();
    Stack<Integer> previousScores = new Stack<>();
    boolean isSaveNeeded = true;

    public Model() {
        resetGameTiles();
    }

    /**
     * Перезапускает игру
     */
    public void resetGameTiles() {
        int score = 0;
        int maxTile = 0;
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    /**
     * Добавляет плитку (не пустую).
     */
    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.size() > 0) {
            int index = (int) (emptyTiles.size() * Math.random());
            emptyTiles.get(index).value = (Math.random() < 0.9 ? 2 : 4);
        }
    }

    /**
     * Получает список пустых плиток .
     * @return список пустых плиток.
     */
    private List<Tile> getEmptyTiles() {
        List<Tile> list = new ArrayList<>();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if (gameTiles[i][j].value == 0) {
                    list.add(gameTiles[i][j]);
                }
            }
        }
        return list;
    }

    /**
     * Сжатие плиток, таким образом, чтобы все пустые плитки были справа,
     * т.е. ряд {4, 2, 0, 4} становится рядом {4, 2, 4, 0}
     * @param tiles массив плиток
     * @return возвращает true, если были внесены изменения во входящий массив.
     */
    private boolean compressTiles(Tile[] tiles) {
        boolean changed = false;
        for (int i = tiles.length - 1; i > 0; i--) {
            for(int j = 0 ; j < i ; j++){
                if( tiles[j].value == 0 ) {
                    if (tiles[j].value != tiles[j + 1].value) {
                        changed = true;
                    }
                    int tmp = tiles[j].value;
                    tiles[j].value = tiles[j + 1].value;
                    tiles[j + 1].value = tmp;
                }
            }
        }
        return changed;
    }

    /**
     * Слияние плиток одного номинала,
     * т.е. ряд {4, 4, 2, 0} становится рядом {8, 2, 0, 0}.
     * Обрати внимание, что ряд {4, 4, 4, 4} превратится в {8, 8, 0, 0}, а {4, 4, 4, 0} в {8, 4, 0, 0}.
     * @param tiles массив плиток
     * @return возвращает true, если были внесены изменения во входящий массив.
     */
    private boolean mergeTiles(Tile[] tiles) {
        boolean changed = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value > 0 && tiles[i].value == tiles[i + 1].value) {
                tiles[i].value += tiles[i + 1].value;
                tiles[i + 1].value = 0;

                if (tiles[i].value > maxTile) {
                    maxTile = tiles[i].value;
                }
                score += tiles[i].value;

                changed = true;
            }
        }

        if (changed) {
            compressTiles(tiles);
        }

        return changed;
    }

    /**
     * Перемещает элементы массива gameTiles влево в соответствии с правилами игры
     * и добавлять плитку с помощью метода addTile, если это необходимо.
     */
    public void left() {
        if (isSaveNeeded) {
            saveState(gameTiles);
        }

        boolean changed = false;
        for (int i = 0; i < gameTiles.length; i++) {
            boolean compressed = compressTiles(gameTiles[i]);
            boolean merged = mergeTiles(gameTiles[i]);
            if (compressed || merged) {
                changed = true;
            }
        }

        if (changed) {
            addTile();
        }

        isSaveNeeded = true;
    }

    public void right() {
        saveState(gameTiles);
        isSaveNeeded = false;

        rotate90();
        rotate90();
        left();
        rotate90();
        rotate90();
    }

    public void up() {
        saveState(gameTiles);
        isSaveNeeded = false;

        rotate90();
        rotate90();
        rotate90();
        left();
        rotate90();
    }

    public void down() {
        saveState(gameTiles);
        isSaveNeeded = false;

        rotate90();
        left();
        rotate90();
        rotate90();
        rotate90();
    }

    /**
     * Повернуть массив на 90 градуслв
     */
    private void rotate90() {
        Tile[][] newTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                newTiles[j][FIELD_WIDTH - 1 - i] = gameTiles[i][j];
            }
        }

        gameTiles = newTiles;
    }

    /**
     * Dозвращающий true в случае, если в текущей позиции возможно сделать ход так,
     * чтобы состояние игрового поля изменилось. Иначе - false.
     * @return возвращающий true в случае, если в текущей позиции возможно сделать ход. Иначе - false.
     */
    public boolean canMove() {
        if (getEmptyTiles().size() > 0)
            return true;

        // Проверим наличие одинаковых ячеек по горизонтали
        for (int i = 0; i < gameTiles.length; i++) {
            if (compressTiles(gameTiles[i]))
                return true;
            if (mergeTiles(gameTiles[i]))
                return true;
        }

        // Проверим наличие одинаковых ячеек по вертикали
        Tile[] tiles = new Tile[FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                tiles[j] = gameTiles[j][i];
            }
            if (compressTiles(tiles))
                return true;
            if (mergeTiles(tiles))
                return true;
        }

        return false;
    }

    /**
     * Сохраняет состояние игры.
     * @param tiles игровое поле.
     */
    private void saveState(Tile[][] tiles) {
        Tile[][] copy = new Tile[tiles.length][tiles[0].length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                copy[i][j] = new Tile(tiles[i][j].value);
            }
        }

        previousStates.push(copy);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    /**
     * Откатывае игру на предыдущее состояние.
     */
    public void rollback() {
        if (previousStates.empty() || previousScores.empty())
            return;
        gameTiles = previousStates.pop();
        score     = previousScores.pop();
    }

    /**
     * Делает случайное движение
     */
    public void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    /**
     * @return возвращает true, в случае, если вес плиток в массиве gameTiles отличается
     * от веса плиток в верхнем массиве стека previousStates.
     */
    public boolean hasBoardChanged() {
        return getTilesValue(gameTiles) != getTilesValue(previousStates.peek());
    }

    private int getTilesValue(Tile[][] tiles) {
        int result = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                result += tiles[i][j].value;
            }
        }
        return result;
    }

    /**
     * Возвращает объект, описывающий эффективность переданного хода.
     * @param move - движение на игровом поле.
     * @return возвращает объект типа MoveEfficiency описывающий эффективность переданного хода.
     */
    public MoveEfficiency getMoveEfficiency(Move move) {
        move.move();
        MoveEfficiency efficiency;
        if (hasBoardChanged()) {
            efficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        } else {
            efficiency = new MoveEfficiency(-1, 0, move);
        }
        rollback();
        return efficiency;
    }

    /**
     * Автоход - лучший ход (с наилучшей эффективностью).
     */
    public void autoMove() {
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue<>(4, Collections.reverseOrder());
        //priorityQueue.add(getMoveEfficiency(() -> left()));
        priorityQueue.add(getMoveEfficiency(this::left));
        priorityQueue.add(getMoveEfficiency(this::right));
        priorityQueue.add(getMoveEfficiency(this::up));
        priorityQueue.add(getMoveEfficiency(this::down));
        priorityQueue.poll().getMove().move();
    }
}
