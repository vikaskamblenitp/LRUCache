package com.vikas.cache.evictionpolicy;

public interface EvictionPolicy<Key> {
    void keyAccessed(Key key);
    Key evictKey();
    void removeKey(Key k);
}
