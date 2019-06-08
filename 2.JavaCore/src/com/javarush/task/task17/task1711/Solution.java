package com.javarush.task.task17.task1711;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws Exception {
        //start here - начни тут
        switch (args[0]) {
            case "-c":
                // -c Миронов м 15/04/1990 Миронова ж 25/04/1997
                synchronized (allPeople) {
                    String name;
                    String sSex;
                    Date bd;
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    for (int i = 0; (i + 3) < args.length; i += 3) {
                        name = args[1 + i];
                        sSex = args[2 + i];
                        bd = df.parse(args[3 + i]);
                        if (sSex.equals("м")) {
                            Solution.allPeople.add(Person.createMale(name, bd));
                        } else if (sSex.equals("ж")) {
                            Solution.allPeople.add(Person.createFemale(name, bd));
                        } else {
                            throw new Exception("Неверно переданы параметры");
                        }
                        System.out.println(Solution.allPeople.size() - 1);
                    }
                }
                break;
            case "-u":
                // -u 0 Миронов м 15/04/1990 1 Миронова ж 25/04/1997
                synchronized (allPeople) {
                    int id;
                    String name;
                    String sSex;
                    Sex sex;
                    Date bd;
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    for (int i = 0; (i + 4) < args.length; i += 4) {
                        id = Integer.parseInt(args[1 + i]);
                        name = args[2 + i];
                        sSex = args[3 + i];
                        bd = df.parse(args[4 + i]);
                        if (sSex.equals("м")) {
                            sex = Sex.MALE;
                        } else if (sSex.equals("ж")) {
                            sex = Sex.FEMALE;
                        } else {
                            throw new Exception("Неверно указан пол!");
                        }
                        Person person = Solution.allPeople.get(id);
                        if (!person.getName().equals(name)) {
                            person.setName(name);
                        }
                        if (person.getSex() != sex) {
                            person.setSex(sex);
                        }
                        if (person.getBirthDate() != bd) {
                            person.setBirthDate(bd);
                        }
                    }
                }
                break;
            case "-d":
                // -d 0 1
                synchronized (allPeople) {
                    for (int i = 0; (i + 1) < args.length; i++) {
                        Person person = Solution.allPeople.get(Integer.parseInt(args[1 + i]));
                        person.setName(null);
                        person.setSex(null);
                        person.setBirthDate(null);
                    }
                }
                break;
            case "-i":
                // -i 0 1
                synchronized (allPeople) {
                    for (int i = 0; (i + 1) < args.length; i++) {
                        System.out.println(allPeople.get(Integer.parseInt(args[1 + i])));
                    }
                }
//            default: {
//                throw new Exception("Неверно переданы параметры"); }
        }
        //System.out.println(Solution.allPeople);
    }
}
