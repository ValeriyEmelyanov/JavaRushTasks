package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;
import java.nio.charset.Charset;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) throws Exception {
        // -c Шорты с лампасами 2000.00 20
        //if (args.length < 4 || !args[0].equals("-c")) {
        //    return;
        //}

        // Обработать входящие аргументы
        StringBuilder sb = new StringBuilder();
        String productName = "";
        String price = "";
        String quantity = "";
        if (args.length == 2) {
            productName = args[1];
        } else if (args.length > 2) {
            for (int i = 1; i < args.length - 2; i++) {
                sb.append(args[i]);
                sb.append(" ");
            }
            productName = sb.toString();
        }
        if (args.length >= 3) {
            price = args[args.length - 2];
        }
        if (args.length >= 4) {
            quantity = args[args.length - 1];
        }

        // Ввести с консоли имя файла
        //String filename = "D:\\Валера\\testProd.txt";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();

        if (args.length > 0 && args[0].equals("-c")) {
            // Получить максимальный id
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String line;
            int maxId = 0;
            int id;
            while ((line = in.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                id = getid(line);
                if (id > maxId) {
                    maxId = id;
                }
            }
            in.close();

            // Запись в конец файла
//            String newLine = String.format(Locale.ENGLISH, "%-8s%-30.30s%-8.2f%-4s", ++maxId, productName, Double.parseDouble(price), quantity);
            sb = new StringBuilder().append(
                    String.format("%-8s", ++maxId)).append(
                    String.format("%-30.30s", productName)).append(
                    String.format(Locale.ENGLISH, "%-8.2f", Double.parseDouble(price))).append(
                    String.format("%-4s", quantity));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename, true), "Cp1251"));
            out.newLine();
            out.write(sb.toString());
            out.close();
        }
    }

//    private static String repeatSpace(int len) {
//        return new String(new char[len]).replace("\0", " ");
//    }

    private static int getid(String line) {
        String sNum = line.substring(0, 8).trim();
        if (sNum.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(sNum);
    }

}
