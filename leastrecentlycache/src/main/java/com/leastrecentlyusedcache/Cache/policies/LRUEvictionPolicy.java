package com.leastrecentlyusedcache.Cache.policies;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import com.leastrecentlyusedcache.Algorithm.DoublyLinkedList;
import com.leastrecentlyusedcache.Algorithm.DoublyLinkedListNode;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key>{

    private DoublyLinkedList<Key> dll;
    private Map<Key,DoublyLinkedListNode<Key>> mapper;

    public LRUEvictionPolicy(){
        this.dll = new DoublyLinkedList<>();
        this.mapper = new HashMap<>();
    }


    @Override
    public void keyAccessed(Key key) {

        if(mapper.containsKey(key)){
            dll.detachNode(mapper.get(key));
            dll.addNodeAtLast(mapper.get(key));
        }else{
            DoublyLinkedListNode<Key> newNode = dll.addElementAtLast(key);
            mapper.put(key, newNode);
        }
    }

    @Override
    public Key evictKey() {
        DoublyLinkedListNode<Key> firstNode = dll.getFirstNode();
        if(firstNode == null){
            return null;
        }
        dll.detachNode(firstNode);
        return firstNode.getElement();
    }
    
}
