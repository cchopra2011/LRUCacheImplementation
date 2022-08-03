package com.leastrecentlyusedcache.Cache.exceptions;

public class StorageFullException extends RuntimeException{

    public StorageFullException(String excpetionMessage) {
        super(excpetionMessage);
    }

}
