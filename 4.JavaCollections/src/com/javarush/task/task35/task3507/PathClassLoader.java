package com.javarush.task.task35.task3507;

import java.io.*;

public class PathClassLoader extends ClassLoader {
    private String pathToBin;

    public PathClassLoader(ClassLoader parent, String pathToBin) {
        super(parent);
        this.pathToBin = pathToBin;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            byte[] bytes = fetchClassFromFS(pathToBin + className + ".class");
            return defineClass(null, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            return super.findClass(className);
        }

    }

    private byte[] fetchClassFromFS(String fileName) throws IOException {
        try (InputStream is = new FileInputStream(fileName)) {
            long fileSize = new File(fileName).length();

            if (fileSize > Integer.MAX_VALUE) {
                throw new IOException("Too big file!");
            }

            byte[] bytes = new byte[(int) fileSize];

            int offset = 0;
            int readed;
            while (offset < bytes.length && (readed = is.read(bytes, offset, bytes.length - offset)) > 0) {
                offset += readed;
            }

            return bytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FileNotFoundException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }
    }
}
