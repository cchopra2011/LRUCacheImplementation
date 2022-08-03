package com.leastrecentlyusedcache.leastrecentlycache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.leastrecentlyusedcache.Cache.Cache;
import com.leastrecentlyusedcache.Cache.policies.EvictionPolicy;
import com.leastrecentlyusedcache.Cache.policies.LRUEvictionPolicy;
import com.leastrecentlyusedcache.Cache.storage.HashMapBasedStorage;
import com.leastrecentlyusedcache.Cache.storage.Storage;

@SpringBootApplication
public class LeastrecentlycacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeastrecentlycacheApplication.class, args);

		Storage<String,String> hmStorage = new HashMapBasedStorage<>(5);
		EvictionPolicy<String> lruEvictionPolicy = new LRUEvictionPolicy<>();
		Cache<String, String> cache = new Cache<>(lruEvictionPolicy, hmStorage);

		cache.put("India", "135");

		cache.put("China", "140");

		
		cache.put("Australia", "30");

		cache.put("USA", "70");

		cache.put("UK", "50");

		System.out.println("DLList == India -> China -> Australia -> USA -> UK");

		cache.get("India"); /** this will put the Key:India at the last of DLL,
		so that this becomes recently used/accessed key */

		System.out.println("DLList == China -> Australia -> USA -> UK -> India");

		cache.get("China");/** this will put the Key:China at the last of DLL,
		and India becomes 2ndLast Accessed so that this becomes recently used/accessed key */

		System.out.println("DLList == Australia -> USA -> UK -> India -> China");

		cache.put("CANADA", "65"); /*As the Storage is full, it will try to evict
		the key which is at the head of DLL */

		System.out.println("DLList == USA -> UK -> India -> China -> CANADA");

		cache.get("USA"); /** this will mark the ,Key :USA as access and put it
		at the last of DLL, thus UK becomes the head of the DLL */

		System.out.println("DLList == UK -> India -> China -> CANADA -> USA");


		cache.put("France", "34"); /*As the Storage is full, it will try to evict
		the key which is at the head of DLL */

		System.out.println("DLList == India -> China -> CANADA -> USA -> France");

	}

}
