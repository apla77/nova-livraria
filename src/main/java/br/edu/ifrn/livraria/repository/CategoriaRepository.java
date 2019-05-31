package br.edu.ifrn.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.livraria.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
public List<Categoria> findByNomeLike(String name);
	
	@Query("select c from Categoria c where c.nome = ?1")
	public List<Categoria> findByName(String nome);

}
