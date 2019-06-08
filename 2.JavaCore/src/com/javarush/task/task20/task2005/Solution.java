package com.javarush.task.task20.task2005;

import jdk.nashorn.internal.ir.IfNode;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Очень странные дела
*/

public class Solution {
    private final static String NULL = "null";

    public static void main(String[] args) {
        //исправь outputStream/inputStream в соответствии с путем к твоему реальному файлу
        try {
            //File your_file_name = File.createTempFile("your_file_name", null);
            File your_file_name = new File("D:\\Валера\\test8.txt");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            System.out.println(ivanov.equals(somePerson));
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        @Override
        public boolean equals(Object o) {
            if (this == o) return false;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;

        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return (int) (result * 100);
        }

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter printWriter = new PrintWriter(outputStream);

            if (this.name == null) {
                printWriter.println(NULL);
            } else {
                printWriter.println(this.name);
            }

            if (this.assets == null) {
                printWriter.println(NULL);
            } else {
                printWriter.println(this.assets.size());
                if (this.assets.size() > 0) {
                    for (Asset current : this.assets)
                        // printWriter.println(current.getName());
                        current.save(printWriter);
                }
            }
            printWriter.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String tmp = reader.readLine();
            if (NULL.equals(tmp)) {
                this.name = null;
            } else {
                this.name = tmp;
            }

            tmp = reader.readLine();
            if (NULL.equals(tmp)) {
                this.assets = null;
            } else {
                int size = Integer.parseInt(tmp);
                for (int i = 0; i < size; i++) {
                    Asset asset = new Asset(null);
                    asset.load(reader);
                    this.assets.add(asset);
                }
            }

            String assetName;
            while ((assetName = reader.readLine()) != null)
                this.assets.add(new Asset(assetName));
            reader.close();
        }
    }
}
