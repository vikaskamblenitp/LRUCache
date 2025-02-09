package com.vikas.cache.evictionpolicy;

import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.LinkedBlockingDeque;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key> {
    private Deque<Key> queue;
    private HashSet<Key> keySet;

    public LRUEvictionPolicy() {
        this.queue = new LinkedBlockingDeque<>();
        this.keySet = new HashSet<>();
    }

    @Override
    public void keyAccessed(Key key) {
        if (keySet.contains(key)) {
            queue.remove(key);
            keySet.remove(key);
        }
        queue.add(key);
        keySet.add(key);
    }

    @Override
    public Key evictKey() {
        if(keySet.isEmpty()) return null;


        Key k = queue.pop();
        keySet.remove(k);

        return k;
    }

    @Override
    public void removeKey(Key k) {
        keySet.remove(k);
        queue.remove(k);
    }
}
