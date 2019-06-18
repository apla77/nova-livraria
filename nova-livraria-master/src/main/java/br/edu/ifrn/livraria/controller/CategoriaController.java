package br.edu.ifrn.livraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.livraria.model.Categoria;
import br.edu.ifrn.livraria.service.CategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
			
	@RequestMapping(method=RequestMethod.GET, path ={"/cadastrar"})
	public ModelAndView cadastrar(Categoria categoria) {
		ModelAndView mv = new ModelAndView("categoria/cadastro");
		mv.addObject("categoria", categoria);
		return mv;
	}
		
	@GetMapping("/editar/{id}")
	private ModelAndView editar( @PathVariable("id") Long id) {
		Categoria categoria = categoriaService.findOne(id);
		return cadastrar(categoria);
	}
	

	@GetMapping("/excluir/{id}")
	private ModelAndView excluir( @PathVariable("id") Long id) {
		categoriaService.delete(id); 
		return findAll();
	}

	
	@PostMapping("/salvar")
    public ModelAndView salvar(@Valid Categoria categoria, BindingResult result) {
		 
		if(result.hasErrors()) {
			return cadastrar(categoria);
	    }

		categoriaService.cadastrar(categoria);
		ModelAndView rec = findAll(); 
		return rec;
    }

	@GetMapping("/lista")
	private ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("categoria/lista");
        mv.addObject("categoria", categoriaService.listaAll());
        return mv;
	}

}
