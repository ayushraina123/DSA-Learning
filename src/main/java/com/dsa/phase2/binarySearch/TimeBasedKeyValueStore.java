package com.dsa.phase2.binarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasedKeyValueStore {
}

class TimeMap {

    Map<String, List<MapValue>> map;
    List<MapValue> keys;

    public TimeMap() {
        map = new HashMap<>();
        keys = new ArrayList<>();
    }

    public void set(String key, String value, int timestamp) {
        MapValue mapValue = new MapValue(value, timestamp);
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(mapValue);
    }

    public String get(String key, int timestamp) {
        List<MapValue> temp = map.get(key);

        if (null == temp || temp.isEmpty())
            return "";

        int left = 0;
        int right = temp.size() - 1;
        int mid = left + (right - left) / 2;

        while (left <= right) {
            if (temp.get(mid).getTimestamp() == timestamp) {
                return temp.get(mid).getValue();
            } else if (temp.get(mid).getTimestamp() < timestamp) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            mid = left + (right - left) / 2;
        }

        if (right >= 0) {
            return temp.get(right).getValue();
        }

        return "";
    }
}

class MapValue {
    String value;
    int timestamp;

    public MapValue(String value, int timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getValue() {
        return this.value;
    }

    public int getTimestamp() {
        return this.timestamp;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */