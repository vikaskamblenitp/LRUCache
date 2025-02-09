package com.vikas.cache.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MultiKeyHashMapStorage<Key, Value> implements Storage<Key, Value> {
    HashMap<Key, Value> keyValueMapper;
    HashMap<Value, List<Key>> valueKeyMapper;
    private final int capacity;

    public MultiKeyHashMapStorage(int capacity) {
        this.capacity = capacity;
        this.keyValueMapper = new HashMap<>();
        this.valueKeyMapper = new HashMap<>();
    }

    @Override
    public void add(Key k, Value v) {
        if (keyValueMapper.containsKey(k) && keyValueMapper.get(k) == v) {
            return;
        }

        // If storage is full and key is new then throw storage full exception
        if (this.isFull() && (!keyValueMapper.containsKey(k) || (keyValueMapper.containsKey(k) && keyValueMapper.get(k) != v))) {
            System.out.println("Storage full...");
            throw new StorageFullException("Storage full");
        }


        // make entry for the new k in v or add it to existing if it exists
        if (valueKeyMapper.containsKey(v)) {
            valueKeyMapper.getOrDefault(v, new ArrayList<Key>()).add(k);
        } else {
            List<Key> keys = new ArrayList<>();
            keys.add(k);
            valueKeyMapper.put(v, keys);
        }

        // add or update value of new
        keyValueMapper.put(k, v);
    }

    @Override
    public Value remove(Key k) {
        Value val = keyValueMapper.get(k);
        if (val == null) return null;
        List<Key> keys = valueKeyMapper.get(val);

        // If just one key is for the value then remove it from both maps
        if (keys.size() == 1) {
            keyValueMapper.remove(k);
            valueKeyMapper.remove(val);
        } else if (keys.size() > 1) {
            // else just remove it from maps and let key as it is
            valueKeyMapper.get(val).remove(k);
            keyValueMapper.remove(k);
        } else {
            throw new RuntimeException("Storage size mismatched");
        }

        return val;
    }

    @Override
    public Value get(Key k) {
        // O(1) to get value against the key
        return keyValueMapper.get(k);
    }

    @Override
    public void printStorage() {
        System.out.println(keyValueMapper.toString());
    }

    @Override
    public boolean isFull() {
        // as we are maintaing value as the size of the capacity as per the multi key concept
        return valueKeyMapper.size() == capacity;
    }
}
