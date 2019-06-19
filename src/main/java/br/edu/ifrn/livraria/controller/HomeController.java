package br.edu.ifrn.livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifrn.livraria.model.Usuario;
import br.edu.ifrn.livraria.service.SessionService;
import br.edu.ifrn.livraria.service.UsuarioService;

@Controller
public class HomeController {
	
	@Autowired
	private UsuarioService serviceUsuario;
	@Autowired
	private SessionService<Usuario> serviceSession;

	@PostMapping("/home") 
	public String home() { 
		
		Usuario usuarioByEmail = serviceUsuario.getEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		
		serviceSession.criarSession("usuario", usuarioByEmail);
		
		System.out.println("TELA HOME");
		return "home";
	} 
	 
	@GetMapping("/")
	public String index() {
		System.out.println("TELA LOGIN");
		return "login";
	}
	
	@GetMapping("/entrar")
	public String entrar() {
		return "login";
	}
}
