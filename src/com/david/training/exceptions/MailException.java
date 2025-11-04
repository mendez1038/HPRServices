package com.david.training.exceptions;

public class MailException extends ServiceException {
	
	 private static final long serialVersionUID = 1L;
	
	public MailException() {
		super();
	}
	
	public MailException(String message) {
		this(message,null);		
	}
	

	public MailException(Throwable cause) {
		this(null,cause);		
	}
	
	public MailException(String message, Throwable cause) {
		super(message,cause);		
	}

}
