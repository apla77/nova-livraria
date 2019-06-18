package br.edu.ifrn.livraria.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.edu.ifrn.livraria.model.Frete;
import br.edu.ifrn.livraria.service.FreteService;

@Controller
@RequestMapping("/frete")
public class FreteController {
	
	@Autowired
	private FreteService freteService;
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Frete frete) {
		Double valor = 2.0;
		frete.setValor(valor);
		
		ModelAndView mv = new ModelAndView("/frete/cadastro");
		mv.addObject("frete", frete);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Frete frete, BindingResult result) throws JsonProcessingException {
		
		if(result.hasErrors()) {
			return cadastrar(frete);
		}
		
		frete.setDataEntregaCorreios(new Date());
		
		freteService.save(frete);
		
		return details(frete.getId());
	}
	
	@GetMapping("/lista")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("frete/lista");
		mv.addObject("fretes", freteService.findAll());
		
		return mv;
	}
	
	
	@GetMapping("/details/{id}")
	public ModelAndView details(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("frete/listar");
		mv.addObject("frete", freteService.findOne(id));
		
		return mv;
	}
	
	@GetMapping("/alldetails/{id}")
	public ModelAndView alldetails(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("frete/detalhes");
		mv.addObject("frete", freteService.findOne(id));
		
		return mv;
	}
	
	@GetMapping("/detailsByPedido/{id}")
	public ModelAndView detailsByPedido(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("frete/detailsByPedido");
		mv.addObject("frete", freteService.findOne(id));
		
		return mv;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		
		return cadastrar(freteService.findOne(id));
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		freteService.delete(id);
		
		return findAll();
	}

}
