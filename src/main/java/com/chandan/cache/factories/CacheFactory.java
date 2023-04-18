package com.chandan.cache.factories;

import com.chandan.Cache;
import com.chandan.cache.policies.LRUEvictionPolicy;
import com.chandan.cache.storage.HashMapBasedStorage;

public class CacheFactory<Key, Value> {
    public Cache<Key, Value> defaultCache(final int capacity) {
        return new Cache<Key, Value>(new LRUEvictionPolicy<Key>(), new HashMapBasedStorage<Key, Value>(capacity));
    }
}
