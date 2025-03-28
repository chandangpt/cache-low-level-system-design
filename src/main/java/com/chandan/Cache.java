package com.chandan;

import com.chandan.cache.exceptions.NotFoundException;
import com.chandan.cache.exceptions.StorageFullException;
import com.chandan.cache.policies.EvictionPolicy;
import com.chandan.cache.storage.Storage;

public class Cache<Key, Value> {
    private final EvictionPolicy<Key> evictionPolicy;
    private final Storage<Key, Value> storage;

    public Cache(EvictionPolicy<Key> evictionPolicy, Storage<Key, Value> storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    public void put(Key key, Value value) {
        try {
            this.storage.add(key, value);
            this.evictionPolicy.keyAccessed(key);
        } catch(StorageFullException ex) {
            System.out.println("Got storage full. Will try to evict");
            Key keyToRemove = evictionPolicy.evictKey();
            if(keyToRemove == null) {
                throw new RuntimeException("Unexpected state. Storage full and no key to evict");
            }
            this.storage.remove(keyToRemove);
            System.out.println("Creating space by evicting item..." + keyToRemove);
            put(key, value);
        }
    }

    public Value get(Key key) {
        try {
            Value value = this.storage.get(key);
            this.evictionPolicy.keyAccessed(key);
            return value;
        } catch(NotFoundException notFoundException) {
            System.out.println("Tried to access non-existing key.");
            return null;
        }
    }
}
