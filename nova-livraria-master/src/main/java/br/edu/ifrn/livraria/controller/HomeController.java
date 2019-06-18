package br.edu.ifrn.livraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

	@PostMapping("/home") 
	public String home() { 
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
