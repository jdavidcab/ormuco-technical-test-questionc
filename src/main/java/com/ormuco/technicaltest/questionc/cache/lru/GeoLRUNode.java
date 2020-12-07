package com.ormuco.technicaltest.questionc.cache.lru;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class GeoLRUNode<K,V> implements Serializable {
    
    private static final long serialVersionUID = 7009748712070414492L;

    @Getter
    @Setter
    private K key;

    @Getter
    @Setter
    private V value;

    @Getter
    @Setter
    private GeoLRUNode<K,V> preNode = null;

    @Getter
    @Setter
    private GeoLRUNode<K,V> postNode = null;

}