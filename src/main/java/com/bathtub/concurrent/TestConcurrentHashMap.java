package com.bathtub.concurrent;

import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentHashMap {

    private void test() {
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        for(int i = 1; i <= 10; i++) {
            concurrentHashMap.put(i, i);
        }
        System.out.println("now concurrentHashMap: " + concurrentHashMap);
        System.out.print("searchKeys(key == 5) 遍历的元素: \t");
        String key01 = concurrentHashMap.searchKeys(10, (key) -> {
            System.out.print(key + "\t");
            if (key == 5) return "我是 key 为 5";
            return null;
        });
        System.out.println();
        System.out.print("searchKeys(key == 11) 遍历的元素: \t");
        String key02 = concurrentHashMap.searchKeys(10, (key) -> {
            System.out.print(key + "\t");
            if (key == 11) return "我是 key 为 11";
            return null;
        });
        System.out.println();
        System.out.println("key01: " + key01 + "   key02: " + key02);
        System.out.println("=================================");
        System.out.print("searchValues(value == 6) 遍历的元素: \t");
        String value01 = concurrentHashMap.searchValues(10, (value) -> {
            System.out.print(value + "\t");
            if (value == 6) return "我是 value 为 6";
            return null;
        });
        System.out.println();
        System.out.print("searchValues(value == 11) 遍历的元素: \t");
        String value02 = concurrentHashMap.searchValues(10, (value) -> {
            System.out.print(value + "\t");
            if (value == 11) return "我是 value 为 11";
            return null;
        });
        System.out.println();
        System.out.println("value01: " + value01 + "  value02: " + value02);
        System.out.println("=================================");
        System.out.print("searchEntries(entry.getKey() == 4 && entry.getValue() == 4) 遍历的元素: \t");
        String entry01 = concurrentHashMap.searchEntries(10, (entry) -> {
            System.out.print(entry + "\t");
            if (entry.getKey() == 4 && entry.getValue() == 4) return "entry.getKey() == 4 && entry.getValue() == 4";
            return null;
        });
        System.out.println();
        System.out.print("searchEntries(entry.getKey() == 4 && entry.getValue() == 5) 遍历的元素: \t");
        String entry02 = concurrentHashMap.searchEntries(10, (entry) -> {
            System.out.print(entry + "\t");
            if (entry.getKey() == 4 && entry.getValue() == 5) return "entry.getKey() == 4 && entry.getValue() == 5";
            return null;
        });
        System.out.println();
        System.out.print("searchEntries(entry.getKey() == 11 && entry.getValue() == 11) 遍历的元素: \t");
        String entry03 = concurrentHashMap.searchEntries(10, (entry) -> {
            System.out.print(entry + "\t");
            if (entry.getKey() == 11 && entry.getValue() == 11) return "entry.getKey() == 11 && entry.getValue() == 11";
            return null;
        });
        System.out.println();
        System.out.println("entry01: " + entry01 + "  entry02: " + entry02 + " entry03: " + entry03);
        System.out.println("=================================");

        System.out.print("search(value == 5) 遍历的元素: \t");
        String map01 = concurrentHashMap.search(10, (key, value) -> {
            System.out.print(key + " = " + value + "\t");
            if (value == 5) return "I am search map(value == 5)";
            return null;
        });
        System.out.println();
        System.out.println("mapp01: " + map01);
    }

    public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>(1000);
    }
}
