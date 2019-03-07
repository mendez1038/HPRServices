package com.david.training.cache;

public interface Cache<K, V> {
	
	public void put(K k, V v);
		
	public V get(K k);
	
	

}
