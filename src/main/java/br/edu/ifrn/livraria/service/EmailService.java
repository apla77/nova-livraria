package br.edu.ifrn.livraria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.edu.ifrn.livraria.model.Email;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender emailSender;
	
	public void sendEmailText(Email email, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email.getTo());
		message.setSubject(email.getSubject());
		message.setText(text);
		message.setFrom(email.getFrom());
		
		emailSender.send(message);
	}
}
