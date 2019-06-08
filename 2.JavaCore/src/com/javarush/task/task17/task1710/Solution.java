package com.javarush.task.task17.task1710;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 4 && args[0].equals("-c")) {
            // -c Миронов м 15/04/1990
            String name = args[1];
            String pSex = args[2];
            Date bd = (new SimpleDateFormat("dd/MM/yyyy")).parse(args[3]);

            if (pSex.equals("м")) {
                allPeople.add(Person.createMale(name, bd));
            } else if (pSex.equals("ж")) {
                allPeople.add(Person.createFemale(name, bd));
            } else {
                throw new Exception("Неверно указан пол!");
            }

            System.out.println(allPeople.size() - 1);

        } else if (args.length == 5 && args[0].equals("-u")) {
            // -u 1 Миронов м 15/05/1990
            int id = Integer.parseInt(args[1]);
            String name = args[2];
            String pSex = args[3];
            Date bd = (new SimpleDateFormat("dd/MM/yyyy")).parse(args[4]);

            Sex sex;
            if (pSex.equals("м")) {
                sex = Sex.MALE;
            } else if (pSex.equals("ж")) {
                sex = Sex.FEMALE;
            } else {
                throw new Exception("Неверно указан пол!");
            }
            Person person = allPeople.get(id);
            if (!person.getName().equals(name)) {
                person.setName(name);
            }
            if (person.getSex() != sex) {
                person.setSex(sex);
            }
            if (person.getBirthDate() != bd) {
                person.setBirthDate(bd);
            }
        }  else if (args.length == 2 && args[0].equals("-d")) {
            // -d 1
            int id = Integer.parseInt(args[1]);
            Person person = allPeople.get(id);
            person.setName(null);
            person.setSex(null);
            person.setBirthDate(null);
        }  else if (args.length == 2 && args[0].equals("-i")) {
            // -i 1
            int id = Integer.parseInt(args[1]);
            System.out.println(allPeople.get(id));
        }
        //System.out.println(allPeople);
    }
}
