package com.vikas.cache;

import com.vikas.cache.storage.MultiKeyHashMapStorage;

public class MultiKeyLRUCache<Key, Value> extends LRUCache<Key, Value> {
    public MultiKeyLRUCache(int capacity) {
        super(new MultiKeyHashMapStorage<>(capacity));
    }

    @Override
    public void evictionHandler(Key key, Value val) {
        // remove keys and values until there is enough space
        while (this.storage.isFull()) {
            Key k = evictionPolicy.evictKey();
            if (k == null) {
                throw new RuntimeException("null key returned in eviction");
            }

            Value evictedValue = this.storage.remove(k);
            System.out.println("Evicted Key: " + k + " Evicted Value: " + evictedValue);
        }

        // add new key and value once the space is available
        this.storage.add(key, val);
        this.evictionPolicy.keyAccessed(key);
    }

}
