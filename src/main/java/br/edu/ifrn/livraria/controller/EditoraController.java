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

import br.edu.ifrn.livraria.model.Editora;
import br.edu.ifrn.livraria.service.EditoraService;

@Controller
@RequestMapping("/editora")
public class EditoraController {
	
	@Autowired
	private EditoraService editoraService; 
	
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar(Editora editora) {
		ModelAndView mv = new ModelAndView("editora/cadastro");
		mv.addObject("editora", editora);
		return mv;
	}
		
	@GetMapping("/editar/{id}")
	private ModelAndView editar( @PathVariable("id") Long id) {
		Editora editora = editoraService.findOne(id);
		return cadastrar(editora);
	}
	

	@GetMapping("/excluir/{id}")
	private ModelAndView excluir( @PathVariable("id") Long id) {
		editoraService.delete(id);
		return findAll();
	}

	
	@PostMapping("/salvar")
    public ModelAndView salvar(@Valid Editora editora, BindingResult result) {
		 
		if(result.hasErrors()) {
			return cadastrar(editora);
	    }

		editoraService.cadastrar(editora);
		ModelAndView rec = findAll(); 
		return rec;
    }

	@GetMapping("/lista")
	private ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("editora/lista");
        mv.addObject("editora", editoraService.listaAll());
        return mv;
	}

}
