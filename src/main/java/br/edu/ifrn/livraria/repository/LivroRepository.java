package br.edu.ifrn.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.livraria.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

	
	@Query(value = "select l.* from livro l inner join "
			+ "item_pedido_livro ipl on(l.id = ipl.livro_id) "
			+ "inner join item_pedido ip on(ip.id = ipl.item_pedido_id) "
			+ "where ip.pedido_id = ?1", nativeQuery = true)
	
	public List<Livro> findByPedido(Long id);
	
	@Query("select l from Livro l where l.titulo = ?1")
	public List<Livro> findByTitulo(String titulo);
	
	@Query("select l from Livro l where l.promocao = ?1")
	public List<Livro> findAllPro(boolean procura);
}
