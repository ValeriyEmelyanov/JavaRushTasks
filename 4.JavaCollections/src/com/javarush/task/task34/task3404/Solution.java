package com.javarush.task.task34.task3404;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
//        solution.recurse("0.3333", 0); //expected output 0.5 6
//        solution.recurse("sin(30)", 0); //expected output 0.5 6
//        solution.recurse("-sin(30)+1", 0); //expected output 0.5 6
//        solution.recurse("sin(15 + 15)", 0); //expected output 0.5 6
//        solution.recurse("sin(-15 + 45)", 0); //expected output 0.5 6
//        solution.recurse("sin(10 + 10 + 10)", 0); //expected output 0.5 6
//        solution.recurse("sin(10 + (10 + 10))", 0); //expected output 0.5 6
//        solution.recurse("sin(40 + (10 - 20))", 0); //expected output 0.5 6

        String s;
//        s = "tan(45)";
//        System.out.print(s + " expected output 1 1 actually ");
//        solution.recurse(s, 0);
//        System.out.print(s + " expected output 1 1 actually ");
//        solution.recurse(s, 0);
//        s = "tan(-45)";
//        System.out.print(s + " expected output -1 2 actually ");
//        solution.recurse(s, 0);
//        s = "0.305";
//        System.out.print(s + " expected output 0.3 0 actually ");
//        solution.recurse(s, 0);
//        s = "0.3051";
//        System.out.print(s + " expected output 0.31 0 actually ");
//        solution.recurse(s, 0);
//        s = "(0.3051)";
//        System.out.print(s + " expected output 0.31 0 actually ");
//        solution.recurse(s, 0);
//        s = "1+(1+(1+1)*(1+1))*(1+1)+1";
//        System.out.print(s + " expected output 12 8 actually ");
//        solution.recurse(s, 0);
//        s = "tan(44+sin(89-cos(180)^2))";
//        System.out.print(s + " expected output 1 6 actually ");
//        solution.recurse(s, 0);
//        s = "-2+(-2+(-2)-2*(2+2))";
//        System.out.print(s + " expected output -14 8 actually ");
//        solution.recurse(s, 0);
//        s = "sin(80+(2+(1+1))*(1+1)+2)";
//        System.out.print(s + " expected output 1 7 actually ");
//        solution.recurse(s, 0);
//        s = "1+4/2/2+2^2+2*2-2^(2-1+1)";
//        System.out.print(s + " expected output 6 11 actually ");
//        solution.recurse(s, 0);
//        s = "10-2^(2-1+1)";
//        System.out.print(s + " expected output 6 4 actually ");
//        solution.recurse(s, 0);
//        s = "2^10+2^(5+5)";
//        System.out.print(s + " expected output 2048 4 actually ");
//        solution.recurse(s, 0);
//        s = "1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1";
//        System.out.print(s + " expected output 72.96 8 actually ");
//        solution.recurse(s, 0);
//        s = "0.000025+0.000012";
//        System.out.print(s + " expected output 0 1 actually ");
//        solution.recurse(s, 0);
//        s = "-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)";
//        System.out.print(s + " expected output -3 16 actually ");
//        solution.recurse(s, 0);
//        s = "cos(3 + 19*3)";
//        System.out.print(s + " expected output 0.5 3 actually ");
//        solution.recurse(s, 0);
//        s = "2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)";
//        System.out.print(s + " expected output 8302231.36 14 actually ");
//        solution.recurse(s, 0);
//        s = "(-1 + (-2))";
//        System.out.print(s + " expected output -3 3 actually ");
//        solution.recurse(s, 0);
//        s = "-sin(2*(-5+1.5*4)+28)";
//        System.out.print(s + " expected output -0.5 7 actually ");
//        solution.recurse(s, 0);
//        s = "sin(100)-sin(100)";
//        System.out.print(s + " expected output 0 3 actually ");
//        solution.recurse(s, 0);
//        s = "-(-22+22*2)";
//        System.out.print(s + " expected output -22 4 actually ");
//        solution.recurse(s, 0);
//        s = "-2^(-2)";
//        System.out.print(s + " expected output -0.25 3 actually ");
//        solution.recurse(s, 0);
//        s = "-(-2^(-2))+2+(-(-2^(-2)))";
//        System.out.print(s + " expected output 2.5 10 actually ");
//        solution.recurse(s, 0);
//        s = "(-2)*(-2)";
//        System.out.print(s + " expected output 4 3 actually ");
//        solution.recurse(s, 0);
//        s = "(-2)/(-2)";
//        System.out.print(s + " expected output 1 3 actually ");
//        solution.recurse(s, 0);
//        s = "sin(-30)";
//        System.out.print(s + " expected output -0.5 2 actually ");
//        solution.recurse(s, 0);
//        s = "cos(-30)";
//        System.out.print(s + " expected output 0.87 2 actually ");
//        solution.recurse(s, 0);
//        s = "tan(-30)";
//        System.out.print(s + " expected output -0.58 2 actually ");
//        solution.recurse(s, 0);
//        s = "2+8*(9/4-1.5)^(1+1)";
//        System.out.print(s + " expected output 6.5 6 actually ");
//        solution.recurse(s, 0);
//        s = "0.005 ";
//        System.out.print(s + " expected output 0.01 0 actually ");
//        solution.recurse(s, 0);
//        s = "0.0049 ";
//        System.out.print(s + " expected output 0 0 actually ");
//        solution.recurse(s, 0);
//        s = "0+0.304";
//        System.out.print(s + " expected output 0.3 1 actually ");
//        solution.recurse(s, 0);
    }

    public void recurse(final String expression, int countOperation) {
        //implement
        NumberFormat frmt = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));

        // Сразу посчитаем операции
        if (countOperation == 0) {
            countOperation = countOperations(expression);
        }

        // Если остался только вывод на консоль
        if (expression.matches("-?\\d+\\.?\\d*") || expression.matches("-?\\d+\\.?\\d*e-?\\d+")) {
            if (expression.equals("-0")) {
                System.out.println(String.format("%s %d", 0, countOperation));
            }
            System.out.println(String.format("%s %d", frmt.format(Double.parseDouble(expression)), countOperation));
            return;
        }

        // Удалить все пробелы и перевести в нижний регистр
        String exp = expression.replaceAll("\\s+", "").toLowerCase();

        // Вычислить тригонометрическую функцию
        Pattern pattern = null;
        Matcher matcher = null;
        if (exp.matches(".*(sin|cos|tan)\\((-?\\d+\\.?\\d*\\)).*")) {
            // Решение тригонометрической функции
            pattern = Pattern.compile("(sin|cos|tan)\\((-?\\d+\\.?\\d*)\\)");
            matcher = pattern.matcher(exp);
            if (matcher.find()) {
                String func = matcher.group(0);
                String funcName = matcher.group(1);
                String val = matcher.group(2);
                double funcResult = 0d;
                double degrees = Double.valueOf(val);
                double radians = Math.toRadians(degrees);
                switch (funcName) {
                    case "sin":
                        funcResult = Math.sin(radians);
                        break;
                    case "cos":
                        funcResult = Math.cos(radians);
                        break;
                    case "tan":
                        funcResult = Math.tan(radians);
                }
                recurse(exp.replace(func, frmt.format(funcResult)), countOperation);
            }
        } else if (exp.matches(".*\\(-?\\d+\\.?\\d*\\).*")) {
            // Убираем скобки вокруг числа
            pattern = Pattern.compile("\\((-?\\d+\\.?\\d*)\\)");
            matcher = pattern.matcher(exp);
            if (matcher.find()) {
                String all = matcher.group(0);
                String found = matcher.group(1);
                recurse(exp.replaceFirst(Pattern.quote(all), found), countOperation);
            }
        } else if (exp.matches(".*\\d+\\.?\\d*[*/^]-?\\d+\\.?\\d*.*")) {
            // Решение операци: умножение, деление, возведение в степень
            Pattern patternHi = Pattern.compile("((\\d+\\.?\\d*)(\\^)(-?\\d+\\.?\\d*))");
            Matcher matcherHi = patternHi.matcher(exp);
            pattern = Pattern.compile("((-?\\d+\\.?\\d*)([*/])(-?\\d+\\.?\\d*))");
            matcher = pattern.matcher(exp);
            // По приоритету операций
            if (matcherHi.find()) {
                String all = matcherHi.group(1);
                double result = calcArithmetic(matcherHi);
                recurse(exp.replaceFirst(Pattern.quote(all), frmt.format(result)), countOperation);
            } else if (matcher.find()) {
                String all = matcher.group(1);
                double result = calcArithmetic(matcher);
                //recurse(exp.replaceFirst(Pattern.quote(all), frmt.format(result)), countOperation);
                recurse(exp.replaceFirst(Pattern.quote(all), String.valueOf(result)), countOperation);
            }
        } else if (exp.matches(".*\\(-?\\d+\\.?\\d*([+\\-]-?\\d+\\.?\\d*)+\\).*")) {
            // Решение операций сложение и вычитание в скобках множественное
            pattern = Pattern.compile("\\(-?\\d+\\.?\\d*([+\\-]-?\\d+\\.?\\d*)+\\)");
            matcher = pattern.matcher(exp);
            if (matcher.find()) {
                String secondary = matcher.group(0);
                pattern = Pattern.compile("((-?\\d+\\.?\\d*)([+\\-])(-?\\d+\\.?\\d*))");
                matcher = pattern.matcher(secondary);
                if (matcher.find()) {
                    String all = matcher.group(1);
                    double result = calcArithmetic(matcher);
                    String tmp  = secondary.replaceFirst(Pattern.quote(all), frmt.format(result));
                    recurse(exp.replaceFirst(Pattern.quote(secondary), tmp), countOperation);
                }
            }
        } else if (exp.matches(".*-?\\d+\\.?\\d*[+\\-]-?\\d+\\.?\\d*.*")) {
            // Решение операций сложение и вычитание
            pattern = Pattern.compile("((-?\\d+\\.?\\d*)([+\\-])(-?\\d+\\.?\\d*))");
            matcher = pattern.matcher(exp);
            if (matcher.find()) {
                String all = matcher.group(1);
                double result = calcArithmetic(matcher);
                recurse(exp.replaceFirst(Pattern.quote(all), frmt.format(result)), countOperation);
            }
        }
    }

    private double calcArithmetic(Matcher matcher) {
        String operand1 = matcher.group(2);
        String operation = matcher.group(3);
        String operand2 = matcher.group(4);
        double num1 = Double.valueOf(operand1);
        double num2 = Double.valueOf(operand2);
        switch (operation) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            case "^":
                return Math.pow(num1, num2);
        }
        return 0L;
    }

    private int countOperations(String s) {

        int countOperation = 0;

        int sin = s.toLowerCase().split("sin").length - 1;
        int cos = s.toLowerCase().split("cos").length - 1;
        int tan = s.toLowerCase().split("tan").length - 1;
        int pow = s.toLowerCase().split("\\^").length - 1;
        int plus = s.toLowerCase().split("\\+").length - 1;
        int minus = s.toLowerCase().split("-").length - 1;
        int multy = s.toLowerCase().split("\\*").length - 1;
        int division = s.toLowerCase().split("/").length - 1;

        if (sin > 0)
            countOperation += sin;
        if (cos > 0)
            countOperation += cos;
        if (tan > 0)
            countOperation += tan;
        if (pow > 0)
            countOperation += pow;
        if (plus > 0)
            countOperation += plus;
        if (minus > 0)
            countOperation += minus;
        if (multy > 0)
            countOperation += multy;
        if (division > 0)
            countOperation += division;

        return countOperation;
    }

    public Solution() {
        //don't delete
    }
}
