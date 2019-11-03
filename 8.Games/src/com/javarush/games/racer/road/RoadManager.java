package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoadManager {
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - RacerGame.ROADSIDE_WIDTH;
    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    private static final int PLAYER_CAR_DISTANCE = 12;

    private List<RoadObject> items = new ArrayList<>();
    private int passedCarsCount = 0;

    public int getPassedCarsCount() {
        return passedCarsCount;
    }

    private RoadObject createRoadObject(RoadObjectType type, int x, int y) {
        switch (type) {
            case THORN:
                return new Thorn(x, y);
            case DRUNK_CAR:
                return new MovingCar(x, y);
            default:
                return new Car(type, x, y);
        }
    }

    private void addRoadObject(RoadObjectType type, Game game) {
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);

        RoadObject roadObject = createRoadObject(type, x, y);
        if (roadObject != null && isRoadSpaceFree(roadObject)) {
            items.add(roadObject);
        }
    }

    public void draw(Game game) {
        items.forEach(i -> i.draw(game));
    }

    public void move(int boost) {
        items.forEach(i -> i.move(boost + i.speed, items));
        deletePassedItems();
    }

    private boolean isThornExists() {
        for (RoadObject roadObject : items) {
            if (roadObject.type == RoadObjectType.THORN) {
                return true;
            }
        }
        return false;
    }
    private boolean isMovingCarExists() {
        for (RoadObject roadObject : items) {
            if (roadObject.type == RoadObjectType.DRUNK_CAR) {
                return true;
            }
        }
        return false;
    }

    private void generateThorn(Game game) {
        int num = game.getRandomNumber(100);
        if (num < 10 && !isThornExists()) {
            addRoadObject(RoadObjectType.THORN, game);
        }
    }

    private void generateMovingCar(Game game) {
        int num = game.getRandomNumber(100);
        if (num < 10 && !isMovingCarExists()) {
            addRoadObject(RoadObjectType.DRUNK_CAR, game);
        }
    }

    public void generateNewRoadObjects(Game game) {
        generateThorn(game);
        generateRegularCar(game);
        generateMovingCar(game);
    }

    private void deletePassedItems() {
        List<RoadObject> tmpItems = new ArrayList<>(items);

        for (RoadObject roadObject : tmpItems) {
            if (roadObject.y >= RacerGame.HEIGHT) {
                items.remove(roadObject);
                if (roadObject.type != RoadObjectType.THORN) {
                    passedCarsCount++;
                }
            }
        }
    }

    public boolean checkCrush(PlayerCar player) {
        for (RoadObject roadObject : items) {
            if (roadObject.isCollision(player)) {
                return true;
            }
        }
        return false;
    }

    private void generateRegularCar(Game game) {
        int num = game.getRandomNumber(100);
        int carTypeNumber = game.getRandomNumber(4);
        if (num < 30 && !isThornExists()) {
            addRoadObject(RoadObjectType.values()[carTypeNumber], game);
        }
    }

    private boolean isRoadSpaceFree(RoadObject object) {
        for (RoadObject roadObject : items) {
            if (roadObject.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE)) {
                return false;
            }
        }
        return true;
    }
}
