package com.vikas.cache.storage;

public interface Storage<Key, Value> {
    void add(Key k, Value v);
    Value remove(Key k);
    Value get(Key k);
    void printStorage();
    boolean isFull();
}
