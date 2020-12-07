package com.ormuco.technicaltest.questionc.cache.factory;

import com.ormuco.technicaltest.questionc.cache.ICache;
import com.ormuco.technicaltest.questionc.cache.lru.GeoLRUCacheSingleton;
import com.ormuco.technicaltest.questionc.cache.redis.RedisGeoLRUCacheImpl;
import com.ormuco.technicaltest.questionc.repository.ICacheRepository;

public class CacheFactory {

    public static ICache<String, String> getCache( final CacheTypeEnum type, 
        ICacheRepository cacheRepository ) {

        ICache<String, String> cache = null;
        if( type == CacheTypeEnum.GEO_LRU_CACHE ) {
            cache = GeoLRUCacheSingleton.getInstance( cacheRepository ).getCache();
        } else if( type == CacheTypeEnum.REDIS_GEO_LRU_CACHE ) {
            cache = RedisGeoLRUCacheImpl.getInstance( cacheRepository );
        }

        return cache;
    }

}