package com.javarush.task.task20.task2013;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Externalizable Person
*/
public class Solution {
    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public Person() {
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            //out.writeChars(firstName);
            //out.writeChars(lastName);
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeInt(age);
            out.writeObject(father);
            out.writeObject(mother);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            //firstName = in.readLine();
            //lastName = in.readLine();
            firstName = (String) in.readObject();
            lastName = (String) in.readObject();
            age = in.readInt();
            father = (Person) in.readObject();
            mother = (Person) in.readObject();
            children = (List) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        Person pap = new Person("Петр", "Савельев", 32);
        Person mam = new Person("Ирина", "Кудрявцева", 30);
        Person son = new Person("Коля", "Савельев", 10);

        ArrayList<Person> children = new ArrayList<>();
        children.add(son);
        pap.setChildren(children);
        mam.setChildren(children);

        son.setFather(pap);
        son.setMother(mam);

        String fileName = "D:\\Валера\\test8.txt";
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(pap);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        Person some1 = (Person) in.readObject();

        out.writeObject(son);
        out.flush();

        Person some2 = (Person) in.readObject();

        out.close();
        in.close();
    }
}
