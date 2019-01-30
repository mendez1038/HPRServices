package com.david.training.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;




public class MailServiceImpl implements MailService{
	
	public MailServiceImpl() {
		
	}
	
		
	
	@Override
	public void sendEmail(String mensajeHTML, String asunto, String... para) throws Exception {

		
		try {
			  HtmlEmail email = new HtmlEmail(); 
			  email.setHostName("smtp.googlemail.com");
			  email.setSmtpPort(465);
			  email.setAuthenticator(new DefaultAuthenticator("dmendez1038@gmail.com", PASSWORD));
			  email.setSSLOnConnect(true);
			  email.addTo(para);
			  email.setFrom("dmendez1038@gmail.com");
			  email.setSubject(asunto);
			  // set the html message
			  email.setHtmlMsg(mensajeHTML);

			  // send the email
			  email.send();
			  
		} catch (Exception e) {
			e.printStackTrace();
			
		}
			
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static final String PASSWORD="ruataboadan1";
	
}
