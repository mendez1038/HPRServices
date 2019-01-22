package com.david.training.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

public class MailServiceImpl implements MailService{
	
	public MailServiceImpl() {
		
	}
	
	public boolean sendMail(String mensajeHTML,String asunto, String URL, String... para)
		throws Exception{
		
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

			  // set the alternative message
			  email.setTextMsg("Hola");

			  // send the email
			  email.send();
			  return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
			
		
		
		
		
		
		
		
		
		
		
		

}
	public static final String PASSWORD="ruataboadan1";
	@Override
	public boolean sendEmail(String mensajeHTML, String asunto, String URL, String... para) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
