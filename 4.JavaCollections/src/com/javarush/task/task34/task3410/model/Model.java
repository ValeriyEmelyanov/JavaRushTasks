package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Paths;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader;

    {
        try {
            levelLoader = new LevelLoader(
                    Paths.get(
                            URLDecoder.decode(
                                    getClass().getResource("../res/levels.txt").getPath(), "UTF-8")
                                    .substring(1)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();

        if (checkWallCollision(player, direction)) {
            return;
        }

        if (checkBoxCollisionAndMoveIfAvaliable(direction)) {
            return;
        }

        moveMovable(player, direction);
        checkCompletion();
    }

    private void moveMovable(Movable movable, Direction direction) {
        int x = 0;
        int y = 0;
        switch (direction) {
            case LEFT:
                x = -1;
                break;
            case RIGHT:
                x = 1;
                break;
            case UP:
                y = -1;
                break;
            case  DOWN:
                y = 1;
        }

        movable.move(x * FIELD_CELL_SIZE, y * FIELD_CELL_SIZE);
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (GameObject wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkBoxCollision(CollisionObject gameObject, Direction direction) {
        for (GameObject box : gameObjects.getBoxes()) {
            if (gameObject.isCollision(box, direction)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction) {
        Player player = gameObjects.getPlayer();

        for (Box box : gameObjects.getBoxes()) {

            if (player.isCollision(box, direction)) {
                if (checkWallCollision((CollisionObject) box, direction)
                        || checkBoxCollision((CollisionObject) box, direction)) {
                    return true;
                }
                moveMovable(box, direction);
            }
        }

        return false;
    }

    public void checkCompletion() {
        int okCount = 0;
        for (GameObject home : gameObjects.getHomes()) {
            for (GameObject box : gameObjects.getBoxes()) {
                if (home.getX() == box.getX() && home.getY() == box.getY()) {
                    okCount++;
                    break;
                }
            }
        }

        if (okCount == gameObjects.getHomes().size()) {
            eventListener.levelCompleted(currentLevel);
        }
    }
}
