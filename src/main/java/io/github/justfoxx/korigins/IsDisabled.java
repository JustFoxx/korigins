package io.github.justfoxx.korigins;

import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IsDisabled {
    boolean isDisabled();

    static <A extends IsDisabled, B> void putMap(Map<A, B> map, A key, B value) {
        if (key.isDisabled()) return;
        map.put(key, value);
    }

    static <A, B extends IsDisabled> void putMap(Map<A, B> map, A key, B value) {
        if (value.isDisabled()) return;
        map.put(key, value);
    }

    static <A extends IsDisabled, B extends IsDisabled> void putMap(Map<A, B> map, A key, B value) {
        if (value.isDisabled() || key.isDisabled()) return;
        map.put(key, value);
    }

    static <A extends IsDisabled> void addList(List<A> list, A value) {
        if (value.isDisabled()) return;
        list.add(value);
    }

    static <A extends IsDisabled> List<A> convertList(List<A> list) {
        val newList = new ArrayList<A>();
        for (val v : list) {
            if (v.isDisabled()) continue;
            newList.add(v);
        }
        return newList;
    }

    static <A extends IsDisabled, B> Map<A, B> convertMapA(Map<A, B> map) {
        val newMap = new HashMap<A, B>();
        for (val v : map.entrySet()) {
            if (v.getKey().isDisabled()) continue;
            newMap.put(v.getKey(), v.getValue());
        }
        return newMap;
    }

    static <A extends IsDisabled, B extends IsDisabled> Map<A, B> convertMapAB(Map<A, B> map) {
        val newMap = new HashMap<A, B>();
        for (val v : map.entrySet()) {
            if (v.getKey().isDisabled() || v.getValue().isDisabled()) continue;
            newMap.put(v.getKey(), v.getValue());
        }
        return newMap;
    }

    static <A, B extends IsDisabled> Map<A, B> convertMapB(Map<A, B> map) {
        val newMap = new HashMap<A, B>();
        for (val v : map.entrySet()) {
            if (v.getValue().isDisabled()) continue;
            newMap.put(v.getKey(), v.getValue());
        }
        return newMap;
    }
}
