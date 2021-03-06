package br.edu.ifrn.livraria.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sun.xml.messaging.saaj.packaging.mime.internet.ParseException;

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
	private LivroService livroService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private SessionService<Usuario> serviceSession;
	
	
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar(Pedido pedido) {
		ModelAndView mv = new ModelAndView("pedido/cadastro");
		mv.addObject("logado", serviceSession.getSession("usuario"));
		mv.addObject("pedido", pedido);
		return mv;
	}
	
	@GetMapping("/addPedido/{id}")
	public ModelAndView addPedido(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
		
		String user = userDetails.getUsername();
		
		Usuario usuario = serviceSession.getSession("usuario");
		
		//System.out.println(" Chama função sendMail *********************** " + usuario.getEmail());
		//emailService.sendMail("Livraria Nova - Compra Realizada", usuario.getEmail());
		//System.out.println(" depois da função sendMail *****************************************");
		
		Pedido pedido = new Pedido();
		pedido.setNomeusuario(usuario.getNome());
		pedido.setEmailusuario(usuario.getEmail());
		
		pedidoService.cadastrar(pedido);
		
		ItemPedido itempedido = itemPedidoService.findOne(id);
		
		itempedido.setPedido(pedido);
		
		itemPedidoService.save(itempedido);
		Frete frete = itempedido.getFrete();
		
		pedido.setValorTotal(itempedido.getValorTotal());
		pedido.setDatapedido(new Date());
		pedido.setQuantidade(itempedido.getQuantidade());
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		pedido.setCompra(format.format(pedido.getDatapedido()));
		pedido.setStatusPedido(StatusPedido.ANDAMENTO);
	
		
		pedido.setUsuario(usuario);
		
		pedidoService.cadastrar(pedido);
		
		System.out.println(" Chama função sendMail *********************** " + usuario.getEmail());
		emailService.sendMail("Livraria Nova - Compra Realizada \nData da compra:" + pedido.getCompra()
							+ "\nNúmero pedido: " + pedido.getId() + "\nUsuário: " + pedido.getNomeusuario()
							+ "\nItens: " + itempedido.toString(), usuario.getEmail());
		System.out.println(" depois da função sendMail *****************************************");
		
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
	
	@GetMapping("/buscar/compra")
	public ModelAndView getPorData(@RequestParam("compra") String compra, ModelMap model) {
		model.addAttribute("pedido", pedidoService.buscarPorData(compra));
		ModelAndView mv = new ModelAndView("/pedido/lista");
		mv.addObject("pedido", pedidoService.buscarPorData(compra));
		mv.addObject("valorTot", pedidoService.totalPorData(compra));
		return mv;
	}
	
	@GetMapping("/buscar2/emailusuario")
	public ModelAndView getPorNome(@RequestParam("emailusuario") String emailusuario, ModelMap model) {
		model.addAttribute("pedido", pedidoService.buscarPorEmail(emailusuario));
		ModelAndView mv = new ModelAndView("/pedido/lista");
		mv.addObject("pedido", pedidoService.buscarPorEmail(emailusuario));
		return mv;
	}
	
	@GetMapping("/listar2")
	public ModelAndView getPorNome(ModelMap model) {
		Usuario usuario = serviceSession.getSession("usuario");
		
		String emailusuario = usuario.getEmail();
		
		ModelAndView mv = new ModelAndView("/pedido/lista");
		List<Pedido> lista2 = pedidoService.buscarPorEmail(emailusuario);
		if(!lista2.isEmpty()) {
			mv.addObject("pedido", lista2);
		}else {
			mv.addObject("error", "Não exitem pedidos para o e-mail informada!");
		}
		return mv;
	}
	
	@PostMapping("/buscarDatas")
    public ModelAndView getPorDatas(@RequestParam("dataInicial") String dataInicial,
    		                       @RequestParam("dataFinal") String dataFinal,
    		                       ModelMap model) throws java.text.ParseException, ParseException{

		ModelAndView mv = new ModelAndView("/pedido/lista");
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");	
		Date dtinicial = null,dtfinal = null;
		
		dtinicial = new Date( formatter.parse(dataInicial).getTime());
		dtfinal = new Date( formatter.parse(dataFinal).getTime());
		List<Pedido> lista = pedidoService.buscarPorDatas((dtinicial.getMonth()+1), (dtfinal.getMonth()+1));
		if(!lista.isEmpty()) {
			mv.addObject("pedido", lista);
		}else {
			mv.addObject("error", "Não exitem pedidos para data informada!");
		}
		return mv;
	}

}
