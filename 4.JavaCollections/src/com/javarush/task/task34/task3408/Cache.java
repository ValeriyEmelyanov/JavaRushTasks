package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
//        V value = cache.get(key);
//        if (value != null) {
//            return value;
//        }
//
//        value = clazz.newInstance();
//        Field fieldKey = clazz.getDeclaredField("myKey");
//        fieldKey.setAccessible(true);
//        fieldKey.set(value, key);
//        cache.put(key, value);
//        return value;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        Constructor constructor = clazz.getConstructor(key.getClass());
        V obj = (V) constructor.newInstance(key);
        cache.put(key, obj);
        return obj;
    }

    public boolean put(V obj) {
        //TODO add your code here
        Class<V> clazz = (Class<V>) obj.getClass();
        try {
            Method method = clazz.getDeclaredMethod("getKey");
            method.setAccessible(true);
            cache.put((K) method.invoke(obj), obj);
            return true;
        } catch (Exception e) {}
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

        return false;
    }

    public int size() {
        return cache.size();
    }
}
