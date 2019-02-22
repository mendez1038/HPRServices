package com.david.training.service;

import com.david.training.exceptions.MailException;

public interface MailService {
	
	public  void  sendEmail(String mensajeHTML,String asunto, String... para) 
		throws MailException;

}
