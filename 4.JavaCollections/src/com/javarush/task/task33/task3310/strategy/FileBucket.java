package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
            return 0L;
        }
    }

    public void putEntry(Entry entry) {
        try (OutputStream outputStream = Files.newOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
             ) {
            objectOutputStream.writeObject(entry);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry() {
        if (getFileSize() <= 0) {
            return null;
        }

        Entry entry = null;

        try (InputStream inputStream = Files.newInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
             ) {
            entry = (Entry) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            ExceptionHandler.log(e);
        }

        return entry;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }
}
