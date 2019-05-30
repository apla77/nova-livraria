package br.edu.ifrn.livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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

}
