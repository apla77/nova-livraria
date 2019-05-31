package br.edu.ifrn.livraria.controller;

import java.util.Date;
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

import br.edu.ifrn.livraria.model.Livro;
import br.edu.ifrn.livraria.model.Pedido;
import br.edu.ifrn.livraria.service.LivroService;
import br.edu.ifrn.livraria.service.PedidoService;
@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private LivroService livroService;
	
	
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar(Pedido pedido) {
		ModelAndView mv = new ModelAndView("pedido/cadastro");
		mv.addObject("livros", livroService.listaAll());
		mv.addObject("pedido", pedido);
		return mv;
	}
	
	@GetMapping("/lista")
	private ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("pedido/lista");
        mv.addObject("pedido", pedidoService.listaAll());
        return mv;
	}
	
	
	@PostMapping("/salvar")
    public ModelAndView salvar(@Valid Pedido pedido, BindingResult result) {
		 
		if(result.hasErrors()) {
		//	return cadastrar(pedido);
	    }

		pedidoService.cadastrar(pedido);
		ModelAndView rec = findAll(); 
		return rec;
    }
	
	@GetMapping("/editar/{id}")
	private ModelAndView editar( @PathVariable("id") Long id) {
		Pedido pedido = pedidoService.findOne(id);
		return cadastrar(pedido);
	}
	
	@GetMapping("/excluir/{id}")
	private ModelAndView excluir( @PathVariable("id") Long id) {
		pedidoService.delete(id);
		return findAll();
	}
	
	@ModelAttribute("livros")
	public List<Livro> listaDeLivros() {
		return livroService.listaAll();
	}	

}
