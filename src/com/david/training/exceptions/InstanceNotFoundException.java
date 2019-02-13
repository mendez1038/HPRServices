package com.david.training.exceptions;


public class InstanceNotFoundException extends InstanceException {
	

	public InstanceNotFoundException(Object key, String className) {
        super("Instance not found", key, className);
    }
   

}
