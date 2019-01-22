package com.david.training.service;

public interface MailService {
	
	public  boolean sendEmail(String mensajeHTML,String asunto, String URL, String... para) 
		throws Exception;

}
