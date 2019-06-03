package br.edu.ifrn.livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.livraria.model.Role;
import br.edu.ifrn.livraria.model.Usuario;
import br.edu.ifrn.livraria.service.RoleService;
import br.edu.ifrn.livraria.service.UsuarioService;

@Controller 
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private RoleService serviceRole;
	
	
	@GetMapping("/cadastrar") 
	public ModelAndView cadastrar(Usuario usuario) {
		ModelAndView mv = new ModelAndView("user/cadastro");
		mv.addObject("usuario", usuario);
		return mv;
	} 
	
	@PostMapping("/salvar")
	public ModelAndView salvar(Usuario usuario) {
		
		Role role = serviceRole.getNome("CLIENTE");
		if(role == null) {
			role = new Role();
			role.setNome("CLIENTE");
			serviceRole.add(role);
		}
		Usuario usuario2 = service.getEmail(usuario.getEmail());
		ModelAndView view = new ModelAndView("login");
		if(usuario2 != null) {
			if(!usuario.getEmail().equals(usuario2.getEmail())) {
				System.out.println("OK");
				service.add(usuario);
				view.addObject("mensagem", "Usuário cadastrado com sucesso!");
			}else {
				System.out.println("ERRO");
				view.addObject("error", "Email já está cadastrado no sistema!");
			}
		}else {
			usuario.getRole().add(role);
			service.add(usuario);
			view.addObject("mensagem", "Usuário cadastrado com sucesso!");
		}
		return view;		
	}
}
