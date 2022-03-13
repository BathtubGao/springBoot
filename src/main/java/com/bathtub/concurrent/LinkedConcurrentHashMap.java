package com.bathtub.concurrent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 链表结构ConcurrentHashMap
 * TODO 维护链表结构后高并发场景下如何保证节点插入更新准确
 */
public class LinkedConcurrentHashMap<K,V> implements ConcurrentMap<K,V> {

    /**
     * 默认容量
     */
    private static final int DEFAULT_CAPACITY = 10000;

    private ConcurrentHashMap<K,V> concurrentHashMap;

    private int capacity;

    public LinkedConcurrentHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public LinkedConcurrentHashMap(int capacity) {
        this.capacity = capacity;
        this.concurrentHashMap = new ConcurrentHashMap<>();
    }

    @Override
    public int size() {
        return concurrentHashMap.size();
    }

    @Override
    public boolean isEmpty() {
        return concurrentHashMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return concurrentHashMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return concurrentHashMap.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return concurrentHashMap.get(key);
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return ConcurrentMap.super.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        ConcurrentMap.super.forEach(action);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return null;
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return false;
    }

    @Override
    public V replace(K key, V value) {
        return null;
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        ConcurrentMap.super.replaceAll(function);
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return ConcurrentMap.super.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return ConcurrentMap.super.computeIfPresent(key, remappingFunction);
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return ConcurrentMap.super.compute(key, remappingFunction);
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return ConcurrentMap.super.merge(key, value, remappingFunction);
    }
}
