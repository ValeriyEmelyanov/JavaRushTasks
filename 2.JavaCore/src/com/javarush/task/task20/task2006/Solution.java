package com.javarush.task.task20.task2006;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Как сериализовать?
*/
public class Solution {
    public static class Human implements Serializable {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(name + " [");
            for (Asset asset : assets) {
                sb.append(asset).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");

            return sb.toString();
        }

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Human human = new Human("Lavrentyev", new Asset("house", 7000));
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("D:\\Валера\\test8.txt"));
        outputStream.writeObject(human);
        outputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("D:\\Валера\\test8.txt"));
        Human theSameHuman = (Human) inputStream.readObject();
        inputStream.close();

        System.out.println(theSameHuman);
    }
}
