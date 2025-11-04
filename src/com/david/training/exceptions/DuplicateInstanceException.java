package com.david.training.exceptions;



public class DuplicateInstanceException extends InstanceException {
	
	 private static final long serialVersionUID = 1L;
	
	public DuplicateInstanceException(Object key, String className) {
        super("Duplicate instance", key, className);
    }    


}
