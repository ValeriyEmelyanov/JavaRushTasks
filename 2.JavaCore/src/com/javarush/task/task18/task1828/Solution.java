package com.javarush.task.task18.task1828;

/*
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) throws Exception {
        // -u 19847992 Шорты с синими лампасами 2001 23
        // -u 19847991 Трико 2001 23
        // -d 19847988
        if (args.length < 2) {
            System.out.println("Не заданы параметры");
            return;
        }

        // Получить из входящих параметров id
        String sId = args[1];

        // Ввести с консоли имя файла
        //String filename = "D:\\Валера\\testProd.txt";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();

        // Считать файл в список
        ArrayList<String> list = new ArrayList<String>();
//        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "Cp1251"));
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
        String line;
        int lineIndex = -1;
        while ((line = in.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }
            list.add(line);
            // Запомним сроку с нужным id
            if (line.substring(0, 8).trim().equals(sId)) {
                lineIndex = list.size() - 1;
            }
        }
        in.close();

        // Обработать ключ
        switch (args[0]) {
            case "-u":
                // Обработать входящие параметры товара
                StringBuilder sb = new StringBuilder();
                String productName = "";
                String price = "";
                String quantity = "";
                if (args.length == 3) {
                    productName = args[2];
                } else if (args.length > 3) {
                    for (int i = 2; i < args.length - 2; i++) {
                        sb.append(args[i]);
                        sb.append(" ");
                    }
                    productName = sb.toString();
                }
                if (args.length >= 4) {
                    price = args[args.length - 2];
                }
                if (args.length >= 5) {
                    quantity = args[args.length - 1];
                }

                // Обновить инфо о товаре
                sb = new StringBuilder().append(
                        String.format("%-8s", sId)).append(
                        String.format("%-30.30s", productName)).append(
                        String.format(Locale.ENGLISH, "%-8.2f", Double.parseDouble(price))).append(
                        String.format("%-4s", quantity));
                if (lineIndex != -1) {
                    list.set(lineIndex, sb.toString());
//                    list.set(lineIndex,
//                            String.format("%-8s%-30.30s%-8.2f%-4s",
//                                    sId, productName, Double.parseDouble(price), quantity));
                }
                //} else {
                //    list.add(sb.toString());
                //}
                break;
            case "-d":
                if (lineIndex != -1) {
                    list.remove(lineIndex);
                }
                break;
            default:
                System.out.println("Неправильный ключ операции");
                break;
        }

        // Записать список в файл
        //BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "Cp1251"));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));
        for (String str : list) {
            out.write(str);
            out.newLine();
        }
        out.close();
    }
}
