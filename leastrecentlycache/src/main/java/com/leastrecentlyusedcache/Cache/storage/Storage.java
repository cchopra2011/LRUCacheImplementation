package com.leastrecentlyusedcache.Cache.storage;

import com.leastrecentlyusedcache.Cache.exceptions.NotFoundException;
import com.leastrecentlyusedcache.Cache.exceptions.StorageFullException;

public interface Storage<Key,Value> {
    
    /*
     * If we add the public keyword to a class, method or property then 
     * we're making it available to the whole world,
     *  i.e. all other classes in all packages will be able to use it. 
     * This is the least restrictive access modifier:
     */
    public void add(Key key,Value value) throws StorageFullException;

    /*default : (or package-private) modifier which means that all members are visible
     within the same package but aren't accessible from other packages: */

    /*
     * Any method, property or constructor with the private keyword is 
     * accessible from the same class only. 
     */

    /*
     * Between public and private access levels, there's the protected access modifier.
       If we declare a method, property or constructor with the protected keyword,
        we can access the member from the same package (as with package-private access level) 
        and in addition from all subclasses of its class, even if they lie in other packages:
     */
    void remove(Key key) throws NotFoundException;

    Value get(Key key) throws NotFoundException;

   /* 
   Modifier	Class	Package	  Subclass	World
    public   Y	        Y	      Y	      Y
 protected   Y	        Y	      Y	      N
   default   Y	        Y	      N	      N
   private   Y	        N	      N	      N
   */ 

}
