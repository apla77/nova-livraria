package br.edu.ifrn.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.livraria.model.Frete;
import br.edu.ifrn.livraria.model.Pedido;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long>{
	
	@Query("select f from Frete f where f.pedido = ?")
	public Frete findByPedido(Pedido pedido);

}
