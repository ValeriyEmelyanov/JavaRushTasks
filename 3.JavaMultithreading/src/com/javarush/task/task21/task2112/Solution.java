package com.javarush.task.task21.task2112;

import java.util.HashSet;
import java.util.Objects;

public class Solution {
    public static void main(String[] args) {
        DBConnectionManager dbConnectionManager = new DBConnectionManager();
        try (FakeConnection fakeConnection = dbConnectionManager.getFakeConnection()) {
            System.out.println("Entering body of try block.");
            fakeConnection.usefulOperation();
            fakeConnection.unsupportedOperation();
        } catch (Exception e) {
        }
//        HashSet<MyClass> set = new HashSet<>();
//        MyClass mc1 = new MyClass(2, 3);
//        set.add(mc1);
//        System.out.println(set.contains(mc1));
//        set.forEach(myClass -> System.out.println(myClass + " "));
//        mc1.i = 77;
//        System.out.println(set.contains(mc1));
//        set.forEach(myClass -> System.out.println(myClass + " "));
    }

//    static class MyClass {
//        public int i, j;
//
//        public MyClass(int i, int j) {
//            this.i = i;
//            this.j = j;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            MyClass myClass = (MyClass) o;
//            return i == myClass.i &&
//                    j == myClass.j;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(i, j);
//        }
//
//        @Override
//        public String toString() {
//            return String.format("[%d, %d]", i, j);
//        }
//    }
}
