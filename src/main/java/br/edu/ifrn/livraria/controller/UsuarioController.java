package br.edu.ifrn.livraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.livraria.model.Usuario;
import br.edu.ifrn.livraria.service.RoleService;
import br.edu.ifrn.livraria.service.UsuarioService;

@Controller 
@RequestMapping("/usuario")
public class UsuarioController {
	
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

		service.add(usuario);
		ModelAndView rec = findAll();
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
	
	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("usuarios", service.buscarPorNome(nome));
		return "/usuario/lista";
	}

}
