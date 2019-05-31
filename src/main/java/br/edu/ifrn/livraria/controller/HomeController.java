package br.edu.ifrn.livraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String login() {
		return "login";
	} 
	@PostMapping("/home")
	public String home() {
		return "home";
	}
}
