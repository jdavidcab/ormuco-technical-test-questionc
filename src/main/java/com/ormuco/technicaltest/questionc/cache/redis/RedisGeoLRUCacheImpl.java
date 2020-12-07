package com.ormuco.technicaltest.questionc.cache.redis;

import com.ormuco.technicaltest.questionc.cache.ICache;
import com.ormuco.technicaltest.questionc.repository.ICacheRepository;

public class RedisGeoLRUCacheImpl implements ICache<String, String>{

    private static RedisGeoLRUCacheImpl INSTANCE = null;
    private ICacheRepository<String, String> cache;

    private RedisGeoLRUCacheImpl( final ICacheRepository<String, String> cache ) {
        this.cache = cache;
    }

    private synchronized static void createInstance( final ICacheRepository<String, String> cacheRepository ) {
        if ( INSTANCE == null ) {
            INSTANCE = new RedisGeoLRUCacheImpl( cacheRepository );
        }
    }
  
    public static RedisGeoLRUCacheImpl getInstance(
    final ICacheRepository<String, String> cacheRepository ) {
        if ( INSTANCE == null ) {
           createInstance( cacheRepository );
        }
        return INSTANCE;
    }

    @Override
    public String get(String key) {
        return cache.get( key );
    }

    @Override
    public void add(String key, String value) {
        cache.put( key, value );
    }

}
