package br.edu.ifrn.livraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.livraria.model.Email;
import br.edu.ifrn.livraria.model.Role;
import br.edu.ifrn.livraria.model.Usuario;
import br.edu.ifrn.livraria.service.EmailService;
import br.edu.ifrn.livraria.service.RoleService;
import br.edu.ifrn.livraria.service.UsuarioService;

@Controller 
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private EmailService sendEmail;
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private RoleService serviceRole;
	
	
	@GetMapping("/cadastrar") 
	public ModelAndView cadastrar(Usuario usuario) {
		
		ModelAndView mv = new ModelAndView("usuario/cadastro");
		mv.addObject("usuario", usuario); 
		mv.addObject("roles", serviceRole.buscarTodos());
		return mv;
	} 
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result) {
		
		if (result.hasErrors()) {
			return cadastrar(usuario);
		}
		System.out.println("Metodo sauvar email ************************");
		service.add(usuario);
		ModelAndView rec = findAll();
		Email email = new Email();
		email.setTo(usuario.getEmail());
		sendEmail.sendEmailBemVindo(email);
		System.out.println("Saindo do Metodo sauvar email ************************");
		return rec;
	}
	 
	
	@GetMapping("/lista")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("usuario/lista"); 
		mv.addObject("usuarios", service.findAll());
		
		return mv;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		
		return cadastrar(service.getOne(id));
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll();
	}

}
