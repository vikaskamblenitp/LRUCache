package com.vikas.cache;

import com.vikas.cache.evictionpolicy.EvictionPolicy;
import com.vikas.cache.evictionpolicy.LRUEvictionPolicy;
import com.vikas.cache.storage.Storage;
import com.vikas.cache.storage.StorageFullException;

public class LRUCache <Key, Value> implements Cache<Key, Value> {
    final EvictionPolicy<Key> evictionPolicy;
    final Storage<Key, Value> storage;

    public LRUCache(Storage<Key, Value> storage) {
        this.evictionPolicy = new LRUEvictionPolicy<>();
        this.storage = storage;
    }

    @Override
    public void set(Key key, Value val) {
        try {
            this.storage.add(key, val);
            evictionPolicy.keyAccessed(key);
        } catch (Exception ex) {
            if (ex instanceof StorageFullException) {
                this.evictionHandler(key, val);
            } else throw ex;
        }
    }

    @Override
    public void evictionHandler(Key key, Value val) {
        Key k = evictionPolicy.evictKey();
        if (k == null) {
            throw new RuntimeException("null key returned in eviction");
        }

        Value evictedValue = this.storage.remove(k);
        System.out.println("Evicted Key: " + k + " Evicted Value: "+ evictedValue);

        this.storage.add(key, val);
        this.evictionPolicy.keyAccessed(key);
    }

    @Override
    public Value remove(Key k) {
        this.evictionPolicy.evictKey();
        Value removedValue = this.storage.remove(k);
        System.out.println("Removed value: " + removedValue);
        return removedValue;
    }

    @Override
    public Value get(Key k) {
        evictionPolicy.keyAccessed(k);
        return this.storage.get(k);
    }

    @Override
    public void printCache() {
        this.storage.printStorage();
    }
}
