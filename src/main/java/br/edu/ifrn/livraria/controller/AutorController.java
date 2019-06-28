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

import br.edu.ifrn.livraria.model.Autor;
import br.edu.ifrn.livraria.model.Editora;
import br.edu.ifrn.livraria.service.AutorService;

@Controller
@RequestMapping("/autor")
public class AutorController {
	
	@Autowired
	private AutorService autorService;

	
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar(Autor autor) {
		ModelAndView mv = new ModelAndView("autor/cadastro");
		mv.addObject("autor", autor);
		return mv;
	}
		
	@GetMapping("/editar/{id}")
	private ModelAndView editar( @PathVariable("id") Long id) {
		Autor autor = autorService.findOne(id);
		return cadastrar(autor);
	}
	
	@GetMapping("/excluir/{id}")
	private ModelAndView excluir( @PathVariable("id") Long id) {
		autorService.delete(id);
		return findAll();
	}
	
	@PostMapping("/salvar")
    public ModelAndView salvar(@Valid Autor autor, BindingResult result) {
		 
		if(result.hasErrors()) {
			//return cadastrar(autor);
	    }

		autorService.cadastrar(autor);
		ModelAndView rec = findAll(); 
		return rec;
    }

	@GetMapping("/lista")
	private ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("autor/lista");
        mv.addObject("autor", autorService.listaAll());
        return mv;
	}
	
	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("autor", autorService.buscarPorNome(nome));
		return "/autor/lista";
	}

}
