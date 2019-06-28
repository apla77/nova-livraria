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

import br.edu.ifrn.livraria.model.Livro;
import br.edu.ifrn.livraria.service.AutorService;
import br.edu.ifrn.livraria.service.CategoriaService;
import br.edu.ifrn.livraria.service.EditoraService;
import br.edu.ifrn.livraria.service.LivroService;

@Controller
@RequestMapping("/livro")
public class LivroController {
	
	@Autowired
	private LivroService service;
	
	@Autowired
	private AutorService autorService;

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private EditoraService editoraService;
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Livro livro) {
		
		ModelAndView mv = new ModelAndView("livro/cadastro");
		mv.addObject("autores", autorService.listaAll());
		mv.addObject("categorias", categoriaService.listaAll());
		mv.addObject("editoras", editoraService.listaAll());
		mv.addObject("livro", livro);
		
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Livro livro, BindingResult result	) {
		
		if(result.hasErrors()) {
			return cadastrar(livro);
		}
		
		service.save(livro);
		
		return findAll();
	}
	
	@GetMapping("/lista")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("livro/lista");
		mv.addObject("livro", service.listaAll());
		
		return mv;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		
		return cadastrar(service.findOne(id));
	}
	
	/*
	@GetMapping("/details/{id}")
	public ModelAndView details(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("livro/detalhes");
		mv.addObject("livro", service.findOne(id));
		
		return mv;
	}
	*/
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll();
	}
	
	@GetMapping("/buscar/titulo")
	public ModelAndView getPorTitulo(@RequestParam("titulo") String titulo, ModelMap model) {
		model.addAttribute("livro", service.buscarPorTitulo(titulo));
		ModelAndView mv = new ModelAndView("/livro/lista");
		mv.addObject("livro", service.buscarPorTitulo(titulo));
		return mv;
	}

}
