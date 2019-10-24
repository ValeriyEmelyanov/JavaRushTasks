package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int size = 0;
        for (List<V> vs : map.values()) {
            size += vs.size();
        }
        return size;
    }

    @Override
    public V put(K key, V value) {
        if (repeatCount <= 0) {
            return null;
        }

        if (map.containsKey(key)) {
            List<V> vList = map.get(key);
            V returnV = null;
            if (vList.size() > 0) {
                returnV = vList.get(vList.size() - 1);
            }
            if (vList.size() == repeatCount) {
                vList.remove(0);
            }
            vList.add(value);
            map.put(key, vList);
            return returnV;
        } else {
            List<V> vList = new ArrayList<>();
            vList.add(value);
            map.put(key, vList);
            return null;
        }
    }

    @Override
    public V remove(Object key) {
        if (!map.containsKey(key)) {
            return null;
        }

        List<V> vList = map.get(key);

        if (vList.size() == 0) {
            map.remove(key);
            return null;
        }

        V returnV = vList.get(0);
        vList.remove(0);
        if (vList.size() == 0) {
            map.remove(key);
        }
        return returnV;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        List<V> vResult = new ArrayList<>();

        for (List<V> vList : map.values()) {
            vResult.addAll(vList);
        }

        return vResult;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (List<V> vList : map.values()) {
            if (vList.contains(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}