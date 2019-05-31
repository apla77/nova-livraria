package br.edu.ifrn.livraria.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.livraria.model.Cidade;
import br.edu.ifrn.livraria.model.UF;
import br.edu.ifrn.livraria.service.CidadeService;

@Controller
@RequestMapping("/cidade")
public class CidadeController {
	
	@Autowired
	private CidadeService cidadeService;
			
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar(Cidade cidade) {
		ModelAndView mv = new ModelAndView("cidade/cadastro");
		mv.addObject("cidade", cidade);
		return mv;
	}
		
	@GetMapping("/editar/{id}")
	private ModelAndView editar( @PathVariable("id") Long id) {
		Cidade cidade = cidadeService.findOne(id);
		return cadastrar(cidade);
	}
	

	@GetMapping("/excluir/{id}")
	private ModelAndView excluir( @PathVariable("id") Long id) {
		cidadeService.delete(id);
		return findAll();
	}

	
	@PostMapping("/salvar")
    public ModelAndView salvar(@Valid Cidade cidade, BindingResult result) {
		 
		if(result.hasErrors()) {
			return cadastrar(cidade);
	    }

		cidadeService.cadastrar(cidade);
		ModelAndView rec = findAll(); 
		rec.addObject("mensagem", "Cidade salva com sucesso!");
		return rec;
    }

	@GetMapping("/lista")
	private ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("cidade/lista");
        mv.addObject("cidade", cidadeService.listaAll());
        return mv;
	}
	
	@ModelAttribute("cidades")
	public List<Cidade> listaDeCidades() {
		return cidadeService.listaAll();			
	}	
	
	@ModelAttribute("ufs")
	public UF[] getUFs() {
		return UF.values();
	}
}
