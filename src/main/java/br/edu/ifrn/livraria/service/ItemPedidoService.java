package br.edu.ifrn.livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.livraria.model.ItemPedido;
import br.edu.ifrn.livraria.model.Pedido;
import br.edu.ifrn.livraria.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository repository;
	
	public ItemPedido save(ItemPedido itemPedido) {
		return repository.saveAndFlush(itemPedido);
	}

	public List<ItemPedido> findAll() {
		return repository.findAll();
	}
	
	public ItemPedido findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<ItemPedido> findByListPedido(List<Pedido> pedidos) {
		return repository.findByListPedido(pedidos);
	}

}
