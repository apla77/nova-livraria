package br.edu.ifrn.livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.livraria.model.Email;
import br.edu.ifrn.livraria.service.EmailService;
import br.edu.ifrn.livraria.service.UsuarioService;

@Controller
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	private EmailService emailServicee;
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/sendEmail")
	public ModelAndView enviarEmail(Email email) {
		emailServicee.sendEmailText(email, email.getTexto());
		return success();
	}
	
	public ModelAndView success() {
		ModelAndView view = new ModelAndView("/usuario/portal-user");
		view.addObject("success", "Email Enviado com Sucesso!");
		return view;
}

}
