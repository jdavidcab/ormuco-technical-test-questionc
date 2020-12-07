package com.ormuco.technicaltest.questionc.repository;

import java.util.Hashtable;

import com.ormuco.technicaltest.questionc.cache.lru.GeoLRUNode;

public class MemoryCacheRepositoryImpl implements ICacheRepository<String, GeoLRUNode<String, String>> {

    private final static int CAPACITY = 5;

    private Hashtable<String, GeoLRUNode<String, String>>
            cache = new Hashtable<String, GeoLRUNode<String, String>>();

    @Override
    public GeoLRUNode<String, String> get(String key) {
        return cache.get(key);
    }

    @Override
    public void put(String key, GeoLRUNode<String, String> value) {
        this.cache.put(key, value);
    }

    @Override
    public void remove(String key) {
        this.cache.remove( key );
    }

    @Override
    public int getCapacity() {
        return CAPACITY;
    }
    
}
