package com.ormuco.technicaltest.questionc.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CacheRepositoryImpl<K,V> implements ICacheRepository<K,V> {

    public static final String CACHE_KEY = "__CACHE__";

    private HashOperations hashOperations;

    private RedisTemplate<K,V> redisTemplate;

    public CacheRepositoryImpl( final RedisTemplate<K,V> redisTemplate ){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    @Override
    public V get( K key ) {
        return (V) hashOperations.get( CACHE_KEY, key );
    }

    @Override
    public void put( K key, V value ) {
        hashOperations.put( CACHE_KEY, key, value );
    }
    
    @Override
    public void remove( final K key ) {
        hashOperations.delete( CACHE_KEY, key );
    }

    @Override
    public int getCapacity() {
        return 0;
    }
}
