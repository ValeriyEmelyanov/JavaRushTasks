package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class StatisticFileVisitor extends SimpleFileVisitor<Path> {
    private int foldersNumber = 0;
    private int filesNumber = 0;
    private int totalSize = 0;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isRegularFile(file)) {
            filesNumber++;
            totalSize += Files.size(file);
        }

        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if (Files.isDirectory(dir)) {
            foldersNumber++;
        }

        return super.preVisitDirectory(dir, attrs);
    }

    public int getFoldersNumber() {
        return foldersNumber - 1;
    }

    public int getFilesNumber() {
        return filesNumber;
    }

    public int getTotalSize() {
        return totalSize;
    }
}
