package com.javarush.task.task19.task1904;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        //Scanner scanner = new Scanner(new FileInputStream("D:\\Валера\\test21.txt"), "Cp1251");
        //while (scanner.hasNextLine()) {
        //    String data = scanner.nextLine();
        //    System.out.println(data);
        //}

        //PersonScanner personScanner = new PersonScannerAdapter(
        //        new Scanner(new FileInputStream("D:\\Валера\\test21.txt"), "Cp1251"));
        //Person person = personScanner.read();
        //personScanner.close();
        //System.out.println(person);
    }

    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {

            Person person = null;

            if (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                //System.out.println(line);

                //String[] arr = line.split("\\s+");
                String[] arr = line.split(" ");

                if (arr.length < 6) {
                    throw new IOException("Неверный формат строки");
                }

                Date bithDate = null;
                try {
                    SimpleDateFormat df = new SimpleDateFormat("dd MM yyyy");
                    bithDate = df.parse(String.format("%s %s %s", arr[3], arr[4], arr[5]));
                } catch (ParseException e) {
                    //e.printStackTrace();
                    throw new IOException("Неверный формат даты");
                }

                person = new Person(arr[1], arr[2], arr[0], bithDate);
            }

            return person;
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
