package com.ormuco.technicaltest.questionc.cache.lru;

import com.ormuco.technicaltest.questionc.cache.ICache;
import com.ormuco.technicaltest.questionc.repository.ICacheRepository;

public class GeoLRUCacheSingleton {

    private static GeoLRUCacheSingleton INSTANCE = null;
    private static ICache<String, String> lruCache;

    private GeoLRUCacheSingleton( final ICacheRepository<String, GeoLRUNode<String,String>> cacheRepository ) {
        lruCache = new GeoLRUCacheImpl<String, String>( cacheRepository );
    }
  
    private synchronized static void createInstance( final ICacheRepository<String, GeoLRUNode<String,String>> cacheRepository ) {
        if ( INSTANCE == null ) {
            INSTANCE = new GeoLRUCacheSingleton( cacheRepository );
        }
    }
  
    public static GeoLRUCacheSingleton getInstance(
    final ICacheRepository<String, GeoLRUNode<String,String>> cacheRepository ) {
        if ( INSTANCE == null ) {
           createInstance( cacheRepository );
        }
        return INSTANCE;
    }

    public ICache<String, String> getCache() {
        return lruCache;
    }

}
