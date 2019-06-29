package br.edu.ifrn.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.livraria.model.ItemPedido;
import br.edu.ifrn.livraria.model.Pedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> { 
	
	@Query("select it_p from ItemPedido it_p where it_p.pedido = ?")
	public List<ItemPedido> findByListPedido(List<Pedido> pedidos);
}
