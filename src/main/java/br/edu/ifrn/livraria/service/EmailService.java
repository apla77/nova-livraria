package br.edu.ifrn.livraria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.edu.ifrn.livraria.model.Email;
import br.edu.ifrn.livraria.model.Usuario;

@Service
public class EmailService { 
	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private UsuarioService serviceUsuario;
	
		
	public void sendEmailBemVindo(Email email) {
		try {
			System.out.println("entro no senEmail");
			Usuario usuario = serviceUsuario.getEmail(email.getTo());
			email.setFrom("gestaoescolaronline1.0@gmail.com");
			email.getMap().put("name", usuario.getNome());
			email.setSubject("Bem-vindo ao site Livraria!");
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email.getTo());
			message.setSubject(email.getSubject());
			message.setText("Seja bem vindo ao site da Livraria");
			message.setFrom(email.getFrom());
			
			emailSender.send(message);
			System.out.println("saiu no senEmail");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
