package com.david.training.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.dao.impl.ContenidoDAOImpl;
import com.david.training.service.MailService;




public class MailServiceImpl implements MailService{
	public static Logger logger = LogManager.getLogger(ContenidoDAOImpl.class);
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
			logger.error(e.getMessage(),e);
			
		}
			
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static final String PASSWORD="ruataboadan1";
	
}
