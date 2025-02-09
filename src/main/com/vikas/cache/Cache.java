package com.vikas.cache;

public interface Cache<Key, Value> {
    void set(Key key, Value value);
    Value remove(Key key);
    Value get(Key key);
    void printCache();
    void evictionHandler(Key key, Value value);
}
