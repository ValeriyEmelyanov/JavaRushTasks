package com.javarush.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static com.javarush.task.task34.task3410.model.Model.FIELD_CELL_SIZE;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        // Вычислим номер нового уровня - после 60 начинаем все с начала
        if (level > 60) {
            level = level % 60;
            if (level == 0) {
                level = 60;
            }
        }

        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = new Player(FIELD_CELL_SIZE / 2, FIELD_CELL_SIZE / 2);

        // Читаем файл
        try (BufferedReader reader = Files.newBufferedReader(levels)) {

            String line = null;

            while ((line = reader.readLine()) != null) {
                if (!line.equals("Maze: " + level)) {
                    continue;
                }

                for (int i = 0; i < 6 && (line = reader.readLine()) != null; i++) {}
                
                int x = FIELD_CELL_SIZE / 2;
                int y = FIELD_CELL_SIZE / 2;

                while ((line = reader.readLine()) != null) {
                    if (line.contains("**********")) {
                        break;
                    }

                    // Цикл по символам строки - создаем игровые объекты
                    char[] chars = line.toCharArray();
                    for (int i = 0; i < chars.length; i++) {
                        switch (chars[i]) {
                            case 'X':
                                // X - стена
                                walls.add(new Wall(x, y));
                                break;
                            case '*':
                                // * - ящик
                                boxes.add(new Box(x, y));
                                break;
                            case '.':
                                // . - дом
                                homes.add(new Home(x, y));
                                break;
                            case '&':
                                // & - ящик который стоит в доме
                                boxes.add(new Box(x, y));
                                homes.add(new Home(x, y));
                                break;
                            case '@':
                                // @ - игрок
                                player = new Player(x, y);
                        }
                        x += FIELD_CELL_SIZE;
                    }

                    x = FIELD_CELL_SIZE / 2;
                    y += FIELD_CELL_SIZE;
                }

                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}
