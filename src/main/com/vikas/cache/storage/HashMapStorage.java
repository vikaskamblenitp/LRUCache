package com.vikas.cache.storage;

import java.util.HashMap;

public class HashMapStorage<Key, Value> implements Storage<Key, Value> {
    private final HashMap<Key, Value> map;
    private final int capacity;

    public HashMapStorage(int capacity) {
        this.map = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    @Override
    public void add(Key k, Value v) {
        if (!map.containsKey(k) && this.isFull()) {
            throw new StorageFullException("Storage is full please evict the key");
        }
        map.put(k, v);
    }

    @Override
    public Value remove(Key k) {
        if (map.containsKey(k)) {
            return map.remove(k);
        }
        return null;
    }

    @Override
    public Value get(Key k) {
        if (map.containsKey(k)) {
            return map.get(k);
        }
        return null;
    }

    @Override
    public void printStorage() {
        System.out.println(map.toString());
    }

    @Override
    public boolean isFull() {
        return map.size() == capacity;
    }
}
