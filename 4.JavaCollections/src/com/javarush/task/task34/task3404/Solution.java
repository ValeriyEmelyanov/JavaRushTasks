package com.javarush.task.task34.task3404;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
    }

    public void recurse(final String expression, int countOperation) {
        //implement
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        try {
            double result = (Double) engine.eval("2*(-5+1.5*4)+28");
            result = Math.toRadians(result);
            result = Math.sin(result);
            System.out.println(result);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public Solution() {
        //don't delete
    }
}
