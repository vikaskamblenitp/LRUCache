package com.vikas.cache.factory;

import com.vikas.cache.Cache;
import com.vikas.cache.LRUCache;
import com.vikas.cache.MultiKeyLRUCache;
import com.vikas.cache.storage.HashMapStorage;

public class CacheFactory<Key, Value> {
    public Cache<Key, Value> getCache(String cacheType, int capacity) {
        switch (cacheType) {
            case "LRU": return new LRUCache<Key, Value>(new HashMapStorage<Key, Value>(capacity));
            case "MultiKeyLRU": return new MultiKeyLRUCache<Key, Value>(capacity);
            case "LFU":
                System.out.println("Yet to implement");
                return null;
            default:
                System.out.println("Unknown cache type");
                return null;
        }
    }
}
