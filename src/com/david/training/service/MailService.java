package com.david.training.service;

public interface MailService {
	
	public  void  sendEmail(String mensajeHTML,String asunto, String... para) 
		throws Exception;

}
