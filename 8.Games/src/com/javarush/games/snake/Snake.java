package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    private List<GameObject> snakeParts = new ArrayList<>();
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public Snake(int x, int y) {
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public void draw(Game game) {
        Color snakeColor = isAlive ? Color.BLACK : Color.RED;
        boolean first = true;
        for (GameObject part : snakeParts) {
            if (first) {
                game.setCellValueEx(part.x, part.y, Color.NONE, HEAD_SIGN, snakeColor, 75);
                first = false;
            } else {
                game.setCellValueEx(part.x, part.y, Color.NONE, BODY_SIGN, snakeColor, 75);
            }
        }
    }

    public void setDirection(Direction direction) {
        if (direction == Direction.LEFT && this.direction == Direction.RIGHT ||
                direction == Direction.RIGHT && this.direction == Direction.LEFT ||
                direction == Direction.UP && this.direction == Direction.DOWN ||
                direction == Direction.DOWN && this.direction == Direction.UP) {
            return;
        }

        if ((this.direction == Direction.LEFT || this.direction == Direction.RIGHT) &&
                snakeParts.get(0).x == snakeParts.get(1).x) {
            return;
        }

        if ((this.direction == Direction.UP || this.direction == Direction.DOWN) &&
                snakeParts.get(0).y == snakeParts.get(1).y) {
            return;
        }

        this.direction = direction;
    }

    public void move(Apple apple) {
        GameObject newHead = createNewHead();

        if (newHead.x < 0 || newHead.x > SnakeGame.WIDTH - 1
                || newHead.y < 0 || newHead.y > SnakeGame.HEIGHT - 1) {
            isAlive = false;
            return;
        }

        if (checkCollision(newHead)) {
            isAlive = false;
        } else {
            snakeParts.add(0, newHead);
        }

        if (newHead.x == apple.x && newHead.y == apple.y) {
            apple.isAlive = false;
        } else if (isAlive) {
            removeTail();
        }
    }

    public GameObject createNewHead() {
        GameObject head = snakeParts.get(0);
        int headX = head.x;
        int headY = head.y;

        switch (direction) {
            case UP:
                headY--;
                break;
            case RIGHT:
                headX++;
                break;
            case DOWN:
                headY++;
                break;
            case LEFT:
                headX--;
                break;
        }
        return new GameObject(headX, headY);
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }

    public boolean checkCollision(GameObject gameObject) {
        for (GameObject part : snakeParts) {
            if (part.x == gameObject.x && part.y == gameObject.y) {
                return true;
            }
        }
        return  false;
    }

    public int getLength() {
        return snakeParts.size();
    }
}
