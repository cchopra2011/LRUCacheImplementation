package com.leastrecentlyusedcache.Cache;

import com.leastrecentlyusedcache.Cache.exceptions.NotFoundException;
import com.leastrecentlyusedcache.Cache.exceptions.StorageFullException;
import com.leastrecentlyusedcache.Cache.policies.EvictionPolicy;
import com.leastrecentlyusedcache.Cache.storage.Storage;

public class Cache<Key,Value> {
    
    private final EvictionPolicy<Key> evictionPolicy;
    private final Storage<Key,Value> storage;

    public Cache(EvictionPolicy<Key> evictionPolicy,Storage<Key,Value> storage){
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }


    public void put(Key key ,Value value){
        try{
            storage.add(key, value);
            evictionPolicy.keyAccessed(key);
        }catch(StorageFullException sfe){
            System.out.println("Got Storage Full, will try to evict .");
            Key keyToRemove = evictionPolicy.evictKey();
            if(keyToRemove == null){
               throw new RuntimeException("Unexpected State, Storage Full and No key found to Evict.");
            }
            storage.remove(keyToRemove);
            System.out.println("Creating space by evicting item..." + keyToRemove);
            put(key, value);
        }
        
    }

    public Value get(Key key){
        try {
            Value value = this.storage.get(key);
            this.evictionPolicy.keyAccessed(key);
            return value;
        } catch (NotFoundException notFoundException) {
            System.out.println("Tried to access non-existing key.");
            return null;
        }
    }
}
