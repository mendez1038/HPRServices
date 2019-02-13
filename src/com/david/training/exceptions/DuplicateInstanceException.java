package com.david.training.exceptions;



public class DuplicateInstanceException extends InstanceException {
	
	public DuplicateInstanceException(Object key, String className) {
        super("Duplicate instance", key, className);
    }    


}
