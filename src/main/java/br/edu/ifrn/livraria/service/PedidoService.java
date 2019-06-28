package br.edu.ifrn.livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.livraria.model.Pedido;
import br.edu.ifrn.livraria.model.Usuario;
import br.edu.ifrn.livraria.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	public Pedido cadastrar(Pedido pedido) {
		return repository.saveAndFlush(pedido);
	}

	public List<Pedido> listaAll() {
		return repository.findAll();
	}
	
	public Pedido findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Pedido> findByUsuario(Usuario usuario) {
		return repository.findByUsuario(usuario);
	}

	public List<Pedido> buscarPorData(String compra){
		return repository.findByData(compra);
	}
	
	public Double totalPorData(String compra){
		Double valorTot = 0.0;
		List<Pedido> pedido = repository.findByData(compra);
		
		for(Pedido p : pedido){
			valorTot += p.getValorTotal();	
		}
		return valorTot;
	}
	
	
	public List<Pedido> buscarPorEmail(String compra){
		return repository.findByEmail(compra);
	}
	
	public Double totalPorEmail(String compra){
		Double valorTot = 0.0;
		List<Pedido> pedido = repository.findByEmail(compra);
		
		for(Pedido p : pedido){
			valorTot += p.getValorTotal();	
		}
		return valorTot;
	}

}
