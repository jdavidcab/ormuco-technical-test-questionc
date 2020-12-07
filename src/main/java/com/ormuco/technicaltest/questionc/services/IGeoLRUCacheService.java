package com.ormuco.technicaltest.questionc.services;

public interface IGeoLRUCacheService {
    
    String getGeoLRUCache( String origin, String key );

    String addGeoLRUCache( String origin, String key, String value );

    String getRedisGeoLRUCache( String origin, String key );

    String addRedisGeoLRUCache( String origin, String key, String value );


}
