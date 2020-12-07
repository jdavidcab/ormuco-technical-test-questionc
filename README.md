# GEO LRU Cache

These are a two Java Microservice allow save and read information in a LRU cache system. The first service called "geo-lru-cache" implement owner LRU algoritm in memory. And the second service called "redis-geo-lru-cache" use a client Redis to save and read cache information from Redis Cache.

Redis is one of the most popular in-memory data structure stores. For this reason, it can be used as a database, cache, and message broker.

In terms of performance, it is well known because of its fast response time. As a result, it can serve hundreds of thousands of operations per second and is easily scalable.

With Redis, you can implement Memory, expiration, key eviction and LRU algoritm. [See document here](http://intro2libsys.info/introduction-to-redis/memory-expiration-and-key-eviction).

## Documentation

Javadocs documentation for to do.

## Published "geo-lru-cache"

The service called "geo-lru-cache" was published on AWS EC2 server over docker container. It was implemented with GET and POST method, url below:

POST method:

```bash
http://ec2-54-67-7-50.us-west-1.compute.amazonaws.com:8082/geo-lru-cache
```

Request: 
```bash
{
    "origin" : "customerService",
    "key" : "customer-login",
    "value" : "{\"username\":\"jdavidcab\"}"
}
```

Response:
```bash
{
    "status": "Completed",
    "value": "{\"username\":\"jdavidcab\"}"
}
```

GET method:

```bash
http://ec2-54-67-7-50.us-west-1.compute.amazonaws.com:8082/geo-lru-cache?origin=customerService&key=customer-login
```

Response:
```bash
{
    "status": "Completed",
    "value": "{\"username\":\"jdavidcab\"}"
}
```

## Published "redis-geo-lru-cache"

The service called "redis-geo-lru-cache" was published on AWS EC2 server over docker container. It was implemented with GET and POST method, url below:

POST method:

```bash
http://ec2-54-67-7-50.us-west-1.compute.amazonaws.com:8082/redis-geo-lru-cache
```

Request: 
```bash
{
    "origin" : "customerService",
    "key" : "customer-login",
    "value" : "{\"username\":\"jdavidcab\"}"
}
```

Response:
```bash
{
    "status": "Completed",
    "value": "{\"username\":\"jdavidcab\"}"
}
```

GET method:

```bash
http://ec2-54-67-7-50.us-west-1.compute.amazonaws.com:8082/redis-geo-lru-cache?origin=customerService&key=customer-login
```

Response:
```bash
{
    "status": "Completed",
    "value": "{\"username\":\"jdavidcab\"}"
}
```

## Docker image
The docker image was published in docker hub site. URL below:

```bash
https://hub.docker.com/repository/docker/jdavidcab/ormuco-technicaltest-questionc
```

## Test information
At Ormuco, we want to optimize every bits of software we write. Your goal is to write a new library that can be integrated to the Ormuco stack. Dealing with network issues everyday, latency is our biggest problem. Thus, your challenge is to write a new Geo Distributed LRU (Least Recently Used) cache with time expiration. This library will be used extensively by many of our services so it needs to meet the following criteria:

1 - Simplicity. Integration needs to be dead simple.
2 - Resilient to network failures or crashes.
3 - Near real time replication of data across Geolocation. Writes need to be in real time.
4 - Data consistency across regions
5 - Locality of reference, data should almost always be available from the closest region
6 - Flexible Schema
7 - Cache can expire 
