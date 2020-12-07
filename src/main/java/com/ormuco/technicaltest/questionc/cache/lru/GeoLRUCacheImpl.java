package com.ormuco.technicaltest.questionc.cache.lru;

import com.ormuco.technicaltest.questionc.cache.ICache;
import com.ormuco.technicaltest.questionc.repository.ICacheRepository;

public class GeoLRUCacheImpl<K,V> implements ICache<K,V>{
    
    private ICacheRepository<K, GeoLRUNode<K,V>> cache;

    private int count;
    private int capacity;
    private GeoLRUNode<K,V> head, tail;

    protected GeoLRUCacheImpl( final ICacheRepository<K, GeoLRUNode<K,V>> cache ) {
        this.cache = cache;
        this.count = 0;
        this.capacity = cache.getCapacity();

        head = new GeoLRUNode<K,V>();
        tail = new GeoLRUNode<K,V>();

        head.setPostNode( tail );
        tail.setPreNode( head );
    }

    @Override
    public V get( K key ) {

        GeoLRUNode<K,V> node = cache.get(key);
        if( node == null ){
            return null; 
        }

        this.moveToHead( node );

        return node.getValue();

    }

    @Override
    public void add( K key, V value ) {
        GeoLRUNode<K,V> node = cache.get( key );

        if( node == null ){

            GeoLRUNode<K,V> newNode = new GeoLRUNode<K,V>();
            newNode.setKey( key );
            newNode.setValue( value );

            this.cache.put( key, newNode );
            this.addNode( newNode );

            ++count;

            if( count > capacity ){
                GeoLRUNode<K,V> tail = this.popTail();
                this.cache.remove( tail.getKey() );
                --count;
            }
        } else {
            node.setValue( value );
            this.moveToHead( node );
        }

    }

    private void addNode( GeoLRUNode<K,V> node ){
        node.setPreNode( head );
        node.setPostNode( head.getPostNode() );

        head.getPostNode().setPreNode( node );
        head.setPostNode( node );
    }

    private void removeNode( GeoLRUNode<K,V> node ){
        GeoLRUNode<K,V> pre = node.getPreNode();
        GeoLRUNode<K,V> post = node.getPostNode();

        pre.setPostNode( post );
        post.setPreNode( pre );
    }

    private void moveToHead( GeoLRUNode<K,V> node ){
        this.removeNode( node );
        this.addNode( node );
    }

    private GeoLRUNode<K,V> popTail(){
        GeoLRUNode<K,V> res = tail.getPreNode();
        this.removeNode( res );
        return res;
    }

}

