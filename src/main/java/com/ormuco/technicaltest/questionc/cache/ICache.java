package com.ormuco.technicaltest.questionc.cache;

public interface ICache<K,V> {
    
    V get( K key );
    void add( K key, V value );

}
