package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students;
    protected int age;
    protected String name;


    public University(String name, int age) {
        this.name = name;
        this.age = age;
        students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student student : students) {
            if (student.getAverageGrade() == averageGrade) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student candidat = null;
        double maxAverageGrade = Double.MIN_VALUE;
        for (Student student : students) {
            if (student.getAverageGrade() > maxAverageGrade) {
                candidat = student;
                maxAverageGrade = student.getAverageGrade();
            }
        }
        return candidat;
    }

    public Student getStudentWithMinAverageGrade() {
        Student candidat = null;
        double minAverageGrade = Double.MAX_VALUE;
        for (Student student : students) {
            if (student.getAverageGrade() < minAverageGrade) {
                candidat = student;
                minAverageGrade = student.getAverageGrade();
            }
        }
        return candidat;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}