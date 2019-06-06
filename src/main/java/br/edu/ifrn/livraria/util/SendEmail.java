package br.edu.ifrn.livraria.util;

import br.edu.ifrn.livraria.model.Email;

public interface SendEmail {
	public void sendEmailText(Email email, String text);
}
