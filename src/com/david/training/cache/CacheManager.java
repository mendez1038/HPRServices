package com.david.training.cache;

import com.david.training.cache.impl.CacheImpl;

public class CacheManager<K,V> {
	

	public static <K,V> Cache<K,V>  getCache(String nombre,
											Class<K> keyClass, Class<V> valueCLass) {
		Cache<K,V> cache = new CacheImpl<K,V>();
		return cache;
		
	}

}
