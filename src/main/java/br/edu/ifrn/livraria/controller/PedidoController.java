package br.edu.ifrn.livraria.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.livraria.model.Frete;
import br.edu.ifrn.livraria.model.ItemPedido;
import br.edu.ifrn.livraria.model.Livro;
import br.edu.ifrn.livraria.model.Pedido;
import br.edu.ifrn.livraria.model.StatusPedido;
import br.edu.ifrn.livraria.model.Usuario;
import br.edu.ifrn.livraria.service.EmailService;
import br.edu.ifrn.livraria.service.FreteService;
import br.edu.ifrn.livraria.service.ItemPedidoService;
import br.edu.ifrn.livraria.service.LivroService;
import br.edu.ifrn.livraria.service.PedidoService;
import br.edu.ifrn.livraria.service.SessionService;
import br.edu.ifrn.livraria.service.UsuarioService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ItemPedidoService  itemPedidoService;
	
	@Autowired
	private FreteService freteService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private SessionService<Usuario> serviceSession;
	
	
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar(Pedido pedido) {
		ModelAndView mv = new ModelAndView("pedido/cadastro");
	//	mv.addObject("livros", livroService.listaAll());
		mv.addObject("logado", serviceSession.getSession("usuario"));
		mv.addObject("pedido", pedido);
		return mv;
	}
	
	@GetMapping("/addPedido/{id}")
	public ModelAndView addPedido(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
		
		String user = userDetails.getUsername();
		
		Usuario usuario = serviceSession.getSession("usuario");
		
		System.out.println(" Chama função sendMail *********************** " + usuario.getEmail());
		emailService.sendMail("Livraria Nova - Compra Realizada", usuario.getEmail());
		System.out.println(" depois da função sendMail *****************************************");
		
		Pedido pedido = new Pedido();
		
		pedidoService.cadastrar(pedido);
		
		ItemPedido itempedido = itemPedidoService.findOne(id);
		
		itempedido.setPedido(pedido);
		
		itemPedidoService.save(itempedido);
		Frete frete = itempedido.getFrete();
		
		pedido.setValorTotal(itempedido.getValorTotal());
		pedido.setDataPedido(new Date());
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		pedido.setDataCompra(format.format(pedido.getDataPedido()));
		pedido.setStatusPedido(StatusPedido.ANDAMENTO);
		
		
		pedido.setUsuario(usuario);
		
		pedidoService.cadastrar(pedido);
		
		frete.setPedido(pedido);
		freteService.save(frete);
		
		ModelAndView mv = new ModelAndView("frete/cadastro");
		mv.addObject("pedido", pedido);
		mv.addObject("frete", frete);
		
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
			return cadastrar(pedido);
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
