package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnemyFleet {
    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 10;
    private static final int STEP = ShapeMatrix.ENEMY.length + 1;

    private Direction direction = Direction.RIGHT;

    private List<EnemyShip> ships;

    public EnemyFleet() {
        createShips();
    }

    private void createShips() {
        ships = new ArrayList<EnemyShip>();

        for (int y = 0; y < ROWS_COUNT; y++) {
            for (int x = 0; x < COLUMNS_COUNT; x++) {
                ships.add(new EnemyShip(x * STEP, y * STEP + 12));
            }

        }

        ships.add(new Boss(STEP * COLUMNS_COUNT / 2 - ShapeMatrix.BOSS_ANIMATION_FIRST.length / 2 - 1, 5));
    }

    public void draw(Game game) {
        for (EnemyShip enemyShip : ships) {
            enemyShip.draw(game);
        }
    }

    private double getLeftBorder() {
        double leftBorder = Double.MAX_VALUE;
        for (EnemyShip enemyShip : ships) {
            leftBorder = Double.min(leftBorder, enemyShip.x);
        }
        return leftBorder;
    }

    private double getRightBorder() {
        double rightBorder = Double.MIN_VALUE;
        for (EnemyShip enemyShip : ships) {
            rightBorder = Double.max(rightBorder, enemyShip.x + enemyShip.width);
        }
        return rightBorder;
    }

    private double getSpeed() {
        return Double.min(2.0, 3.0 / ships.size());
    }

    public void move() {
        if (ships.size() == 0) {
            return;
        }

        boolean directionChanged = false;
        if (direction == Direction.LEFT && getLeftBorder() < 0) {
            direction = Direction.RIGHT;
            directionChanged = true;
        } else if (direction == Direction.RIGHT && getRightBorder() > SpaceInvadersGame.WIDTH) {
            direction = Direction.LEFT;
            directionChanged = true;
        }

        double speed = getSpeed();
        Direction currentDirection = directionChanged ? Direction.DOWN : direction;
        for (EnemyShip enemyShip : ships) {
            enemyShip.move(currentDirection, speed);
        }
    }

    public Bullet fire(Game game) {
        if (ships.size() == 0) {
            return null;
        }

        if (game.getRandomNumber(100 / SpaceInvadersGame.COMPLEXITY) > 0) {
            return null;
        }

        return ships.get(game.getRandomNumber(ships.size())).fire();
    }

    public int verifyHit(List<Bullet> bullets) {
        if (bullets.size() == 0) {
            return 0;
        }

        int score = 0;
        for (EnemyShip enemyShip : ships) {
            if (!enemyShip.isAlive) {
                continue;
            }
            for (Bullet bullet : bullets) {
                if (!bullet.isAlive) {
                    continue;
                }
                if (enemyShip.isCollision(bullet)) {
                    enemyShip.kill();
                    bullet.kill();
                    score +=enemyShip.score;
                }
            }
        }
        return score;
    }

    public void deleteHiddenShips() {
        Iterator<EnemyShip> iterator = ships.iterator();
        while (iterator.hasNext()) {
            EnemyShip enemyShip = iterator.next();
            if (!enemyShip.isVisible()) {
                iterator.remove();
            }
        }
    }

    public double getBottomBorder() {
        double bottom = 0;
        for (EnemyShip enemyShip : ships) {
            bottom = Math.max(bottom, enemyShip.y + enemyShip.height);
        }
        return bottom;
    }

    public int getShipsCount() {
        return ships.size();
    }
}
