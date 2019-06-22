package br.edu.ifrn.livraria.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.livraria.model.Endereco;
import br.edu.ifrn.livraria.model.Frete;
import br.edu.ifrn.livraria.model.ItemPedido;
import br.edu.ifrn.livraria.model.Livro;
import br.edu.ifrn.livraria.service.FreteService;
import br.edu.ifrn.livraria.service.ItemPedidoService;
import br.edu.ifrn.livraria.service.LivroService;

@Controller
@RequestMapping("/itemPedido")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoService service;
	
	@Autowired
	private LivroService serviceLivro;
	
	@Autowired
	private FreteService servicefrete;
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(ItemPedido itemPedido) {
		
		ModelAndView mv = new ModelAndView("itemPedido/cadastro");
		mv.addObject("livros", serviceLivro.listaAll());
		mv.addObject("itemPedido", itemPedido);
		
		return mv;
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("/save")
	public ModelAndView save(@Valid ItemPedido itemPedido, BindingResult result) {
		
		if(result.hasErrors()) {
			return cadastrar(itemPedido);
		}
		
		service.save(itemPedido);
		
		Frete frete = new Frete();
		String cep = itemPedido.getCep();
		
		double valor = 0.0;
		int i = 0;
		double pesolivros = 0.0;
		
		List<Livro> livros = itemPedido.getLivro();
		for(Livro l : livros){
			valor += Double.parseDouble(l.getPreco());
			pesolivros += l.getPeso();
			i++;
		}
		
	//	String peso = Double.toString(pesolivros);
		
		RestTemplate template = new RestTemplate();
		
		Endereco endereco = template.getForObject("https://viacep.com.br/ws/"+cep+"/json",Endereco.class);
		
		if(endereco != null){			
			frete.setUf(endereco.getUf());
			frete.setCidade(endereco.getLocalidade());
			frete.setBairro(endereco.getBairro());
			frete.setCepOrigem(cep);
			frete.setRua(endereco.getLogradouro());
		}
		
		String pre = itemPedido.getPreco();
		String dias = itemPedido.getPrazo();
		
		pre = pre.replaceAll( "," , "." );
		
		
		double preco = Double.parseDouble(pre);
		int prazodias = Integer.parseInt(dias);
			frete.setCepOrigem("59900000");
			frete.setPeso(pesolivros);
			frete.setValor(preco);
			frete.setTotDias(prazodias);
		
		Date a = new Date();        
		a.setDate(a.getDate() + prazodias);        
		 
		String formatoData = "dd/MM/yyyy";
		SimpleDateFormat dataFormatada = new SimpleDateFormat(formatoData); 
		
		frete.setDataEntregaCliente(dataFormatada.format(a));
		
		servicefrete.save(frete);
		
		itemPedido.setFrete(frete);
		
		valor += preco;
		itemPedido.setValorTotal(valor);
		itemPedido.setQuantidade(i);
		
		service.save(itemPedido);
		
		return detalhes(itemPedido.getId());
	}
	
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("itemPedido/listar");
		mv.addObject("itemPedidos", service.findAll());
		
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return cadastrar(service.findOne(id));
	}
	
	@GetMapping("/detalhes/{id}")
	public ModelAndView detalhes(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("itemPedido/detalhes");
		mv.addObject("itemPedido", service.findOne(id));
		
		return mv;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll();
	}

}
