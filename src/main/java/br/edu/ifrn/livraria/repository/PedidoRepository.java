package br.edu.ifrn.livraria.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.livraria.model.Pedido;
import br.edu.ifrn.livraria.model.Usuario; 
 
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	@Query("select p from Pedido p where p.usuario = ?1")
	public List<Pedido> findByUsuario(Usuario usuario);
	
	@Query("select p from Pedido p where p.compra = ?1")
	public List<Pedido> findByData(String compra);
	
	@Query("select p from Pedido p where p.emailusuario = ?1") 
	public List<Pedido> findByEmail(String compra);
	
	@Query(value="select * from pedido p where month(p.data_pedido) >= ? and month(p.data_pedido) <= ?", nativeQuery = true)
	public List<Pedido> findByDataPedido(int dtinicial, int dtfinal);
	
}
