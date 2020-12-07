package com.ormuco.technicaltest.questionc.services;

import com.ormuco.technicaltest.questionc.cache.ICache;
import com.ormuco.technicaltest.questionc.cache.factory.CacheFactory;
import com.ormuco.technicaltest.questionc.cache.factory.CacheTypeEnum;
import com.ormuco.technicaltest.questionc.cache.lru.GeoLRUNode;
import com.ormuco.technicaltest.questionc.repository.ICacheRepository;
import com.ormuco.technicaltest.questionc.repository.MemoryCacheRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoLRUCacheServiceImpl implements IGeoLRUCacheService {

    private static final String ORIGIN_KEY_SEPARATOR = "::";

    @Autowired
    private ICacheRepository<String, String> redisCacheRepository;

    private ICacheRepository<String, GeoLRUNode<String, String>> ownCacheRepository = 
        new MemoryCacheRepositoryImpl();

    @Override
    public String getGeoLRUCache( String origin, String key ) {

        final String originAndKey = origin + ORIGIN_KEY_SEPARATOR + key;
        final ICache<String, String> cache = 
            CacheFactory.getCache( CacheTypeEnum.GEO_LRU_CACHE, ownCacheRepository );
        return cache.get( originAndKey );
    }

    @Override
    public String addGeoLRUCache( String origin, String key, String value ) {
        final String originAndKey = origin + ORIGIN_KEY_SEPARATOR + key;
        final ICache<String, String> cache = 
            CacheFactory.getCache( CacheTypeEnum.GEO_LRU_CACHE, ownCacheRepository );
        cache.add( originAndKey, value );
        return value;
    }

    @Override
    public String getRedisGeoLRUCache(String origin, String key) {
        final String originAndKey = origin + ORIGIN_KEY_SEPARATOR + key;
        final ICache<String, String> cache = 
            CacheFactory.getCache( CacheTypeEnum.REDIS_GEO_LRU_CACHE, redisCacheRepository );
        return cache.get( originAndKey );
    }

    @Override
    public String addRedisGeoLRUCache(String origin, String key, String value) {
        final String originAndKey = origin + ORIGIN_KEY_SEPARATOR + key;
        final ICache<String, String> cache = 
            CacheFactory.getCache( CacheTypeEnum.REDIS_GEO_LRU_CACHE, redisCacheRepository );
        cache.add( originAndKey, value );
        return value;
    }
}
