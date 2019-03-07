package com.david.training.cache.impl;

import java.util.HashMap;
import java.util.Map;

import com.david.training.cache.Cache;

public class CacheImpl<K,V> implements Cache<K, V>{
	
	Map<K,V> cache = new HashMap<K,V>();
	
	public void put(K k, V v) {
		cache.put(k,v);
	}
	
	public V get(K k) {
		return cache.get(k);
		
	}

	
	

}
