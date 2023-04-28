package com.vinothit.utils;

import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	// Send Email
	public boolean sendEmail(String subject, String body, String to) {
		
		boolean isSent = false;

		try {
			MimeMessage mimeMsg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true);
			
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);
			
			javaMailSender.send(mimeMsg);
			
			isSent = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSent;
	}

}
