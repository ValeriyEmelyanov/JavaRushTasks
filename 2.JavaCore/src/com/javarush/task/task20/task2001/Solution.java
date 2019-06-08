package com.javarush.task.task20.task2001;

import com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Читаем и пишем в файл: Human
*/
public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            //File your_file_name = File.createTempFile("D:\\Валера\\test8.txt", null);
            File your_file_name = new File("D:\\Валера\\test8.txt");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home", 999_999.99), new Asset("car", 2999.99));
            ivanov.save(outputStream);
            outputStream.flush();
            outputStream.close();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            inputStream.close();
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            System.out.println(ivanov.equals(somePerson));

        } catch (IOException e) {
            e.printStackTrace();
//            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class Human {
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public void save(OutputStream outputStream) throws Exception {
            if (name == null || name.isEmpty()) {
                throw new Exception("Поле name не может быть пустым!");
            }

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(name);
            writer.newLine();
            writer.write(String.valueOf(assets.size()));
            writer.newLine();
            if (assets.size() > 0) {
                for (Asset asset : assets) {
                    asset.save(writer);
                }
            }

            writer.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            name = reader.readLine();
            int size = Integer.parseInt(reader.readLine());
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    Asset asset = new Asset("", 0);
                    asset.load(reader);
                    assets.add(asset);
                }
            }
        }
    }
}
