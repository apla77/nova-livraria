package br.edu.ifrn.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.livraria.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{
	
	public List<Autor> findByNomeLike(String name);
	
	@Query("select a from Autor a where a.nome = ?")
	public List<Autor> findByName(String nome);

}
