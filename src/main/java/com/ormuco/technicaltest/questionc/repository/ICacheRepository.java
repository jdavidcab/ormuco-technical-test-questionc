package com.ormuco.technicaltest.questionc.repository;

public interface ICacheRepository<K,V> {

    V get( final K key );
    void put( final K key, final V value );
    void remove( final K key );
    int getCapacity();
}
