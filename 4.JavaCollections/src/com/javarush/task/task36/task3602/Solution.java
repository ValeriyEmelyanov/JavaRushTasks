package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {

        Class result = null;

        Class[] classes = Collections.class.getDeclaredClasses();
        for (Class clazz : classes) {
            if (List.class.isAssignableFrom(clazz)) {
                int modifiers = clazz.getModifiers();
                if (Modifier.isPrivate(modifiers) && Modifier.isStatic(modifiers)) {
                    Method method = null;
                    Constructor constructor = null;
                    try {
                        method = clazz.getDeclaredMethod("get", int.class);
                        method.setAccessible(true);
                        constructor = clazz.getDeclaredConstructor();
                        constructor.setAccessible(true);
                    } catch (NoSuchMethodException e) {
                        continue;
                    }
                    try {
                        method.invoke(constructor.newInstance(), 0);
                    } catch (IllegalAccessException | InstantiationException ignored) {
                    } catch (InvocationTargetException e) {
                        if (e.getCause().toString().contains("IndexOutOfBoundsException")) {
                            return clazz;
                        }
                    }
                }
            }
        }

        return null;
    }
}
