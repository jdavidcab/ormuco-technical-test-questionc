package com.ormuco.technicaltest.questionc.controllers;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ormuco.technicaltest.questionc.controllers.dto.GeoLRUCacheRequest;
import com.ormuco.technicaltest.questionc.controllers.dto.GeoLRUCacheResponse;
import com.ormuco.technicaltest.questionc.services.IGeoLRUCacheService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis-geo-lru-cache")
public class RedisGeoLRUCacheController {
    
    /**
     * Spring Dependency injection of service.
     */
    @Autowired
    IGeoLRUCacheService service;

    @GetMapping
    public GeoLRUCacheResponse get( @RequestParam( "origin" ) String origin, 
        @RequestParam( "key" ) @NotBlank @NotNull String key ) {

        final String value = service.getRedisGeoLRUCache(origin, key);
        return buildResponse( value );

    }

    @PostMapping
    public GeoLRUCacheResponse add( @RequestBody GeoLRUCacheRequest request ) {

        final String value = service.addRedisGeoLRUCache( request.getOrigin(), request.getKey(), request.getValue() );
        return buildResponse( value );

    }

    private GeoLRUCacheResponse buildResponse( String value ) {
        final GeoLRUCacheResponse response = new GeoLRUCacheResponse();
        response.setStatus( ( value != null ) ? "Completed" : "Not Found" );
        response.setValue( value );
        return response;
    }

}
