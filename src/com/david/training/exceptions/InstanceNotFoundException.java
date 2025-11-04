package com.david.training.exceptions;


public class InstanceNotFoundException extends InstanceException {
	
	 private static final long serialVersionUID = 1L;
	

	public InstanceNotFoundException(Object key, String className) {
        super("Instance not found", key, className);
    }
   

}
