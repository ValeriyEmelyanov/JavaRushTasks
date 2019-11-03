package com.javarush.games.racer;

import com.javarush.engine.cell.*;
import com.javarush.games.racer.road.RoadManager;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH / 2;
    public static final int ROADSIDE_WIDTH = 14;
    private static final int RACE_GOAL_CARS_COUNT = 40;

    private RoadMarking roadMarking;
    private PlayerCar player;
    private RoadManager roadManager;
    private boolean isGameStopped;
    private FinishLine finishLine;
    private ProgressBar progressBar;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        showGrid(false);
        createGame();
    }

    private void createGame() {
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        roadManager = new RoadManager();
        finishLine = new FinishLine();
        progressBar = new ProgressBar(RACE_GOAL_CARS_COUNT);
        score = 3500;

        drawScene();

        setTurnTimer(40);
        isGameStopped = false;
    }

    private void drawScene() {
        drawField();
        progressBar.draw(this);
        finishLine.draw(this);
        roadMarking.draw(this);
        player.draw(this);
        roadManager.draw(this);
    }

    private void drawField() {
        // Левая бочина
        drawCellXRange(0, ROADSIDE_WIDTH, Color.GREEN);
        // Правая обочина
        drawCellXRange(WIDTH - ROADSIDE_WIDTH, WIDTH, Color.GREEN);
        // Левая полоса
        drawCellXRange(ROADSIDE_WIDTH, CENTER_X, Color.GRAY);
        // Правая полоса
        drawCellXRange(CENTER_X + 1, WIDTH - ROADSIDE_WIDTH, Color.GRAY);
        // Разделительная линия
        drawCellXRange(CENTER_X, CENTER_X + 1, Color.WHITE);
    }

    private void drawCellXRange(int start, int end, Color color) {
        for (int x = start; x < end; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellColor(x, y, color);
            }
        }
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return;
        }
        super.setCellColor(x, y, color);
    }

    private void moveAll() {
        roadMarking.move(player.speed);
        roadManager.move(player.speed);
        finishLine.move(player.speed);
        player.move();
        progressBar.move(roadManager.getPassedCarsCount());
    }

    @Override
    public void onTurn(int step) {
        if (roadManager.getPassedCarsCount() >= RACE_GOAL_CARS_COUNT) {
            finishLine.show();
        }

        if (roadManager.checkCrush(player)) {
            gameOver();
        } else {
            if (finishLine.isCrossed(player)) {
                win();
            } else {
                moveAll();
                roadManager.generateNewRoadObjects(this);
            }
        }

        score -= 5;
        setScore(score);

        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case LEFT:
                player.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                player.setDirection(Direction.RIGHT);
                break;
            case SPACE:
                if (isGameStopped) {
                    createGame();
                }
                break;
            case UP:
                player.speed = 2;
                break;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key){
            case LEFT:
            case RIGHT:
                if (player.getDirection() == Direction.LEFT
                        || player.getDirection() == Direction.RIGHT) {
                    player.setDirection(Direction.NONE);
                }
                break;
            case UP:
                player.speed = 1;
                break;
        }
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.BLUE, "  Game over  ", Color.WHITE, 42);
        stopTurnTimer();
        player.stop();
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.BLUE, "  You won!  ", Color.WHITE, 42);
        stopTurnTimer();
    }
}
